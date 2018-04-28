package com.study.itmo.gregory.finalTasks.numbers.idxutils;

import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static com.study.itmo.gregory.finalTasks.numbers.idxutils.FilePaths.*;

public class IDXutils {
    /**
     * parser and utils for IDX files
     * containing handwritten digits images(MNIST)
     * taken from http://yann.lecun.com/exdb/mnist/
     * <p>
     * todo оптимизировать
     * todo другим способом не соседями
     * todo сделать вычисляя интеграл
     * то есть ось икс - это количество пикселей
     * а значение пикселя это удаление от оси икс
     * <p>
     * не сортировка а поиск минимума
     * <p>
     * у бинарного дерева - двоичный логорифм сложности
     * <p>
     * todo оптимизировать: если 5 соседей - создавать только 5 лучших объектов
     *
     * @param args
     */
    //upload and import all labels and images files
    public static int[] trainLabels;//60 000
    public static ArrayList<int[]> trainImages;//4 ляма
    public static int[] testLabels;
    public static ArrayList<int[]> testImages;

    static {
        try {
            trainLabels = getLabels(TRAINING_LABELS);
            trainImages = getImages(TRAINING_IMAGES);
            testLabels = getLabels(TEST_LABELS);
            testImages = getImages(TEST_IMAGES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int match = 0;
    public static int total = 0;

    public static int neighborsAmount = 5;//on 27 == 5; on 5 == 3.2%; on 10 == 4%

    //public static double middleBlock = 0;
    //public static double middleLength = 0;


    public static void main(String[] args) throws IOException, InterruptedException {
        Date init = new Date();
        System.out.println("process started at " + init.toString());

       // UtilThreadProcessBlockAllNeighbors processBlock1 = new UtilThreadProcessBlockAllNeighbors(0, 250);
       // UtilThreadProcessBlockAllNeighbors processBlock2 = new UtilThreadProcessBlockAllNeighbors(250, 500);
        //UtilThreadProcessBlockAllNeighbors processBlock3 = new UtilThreadProcessBlockAllNeighbors(5000, 7500);
        //UtilThreadProcessBlockAllNeighbors processBlock4 = new UtilThreadProcessBlockAllNeighbors(7500, 10000);
        UtilThreadProcessBlockNeededNeighbors processBlock1 = new UtilThreadProcessBlockNeededNeighbors(0, 125);
        UtilThreadProcessBlockNeededNeighbors processBlock2 = new UtilThreadProcessBlockNeededNeighbors(125, 250);
        UtilThreadProcessBlockNeededNeighbors processBlock3 = new UtilThreadProcessBlockNeededNeighbors(250, 375);
        UtilThreadProcessBlockNeededNeighbors processBlock4 = new UtilThreadProcessBlockNeededNeighbors(375, 500);

        processBlock1.start();
        processBlock2.start();
        processBlock3.start();
        processBlock4.start();

        processBlock1.join();
        processBlock2.join();
        processBlock3.join();
        processBlock4.join();

        Date initEnd = new Date();
        System.out.println();
        System.out.println("process ended at " + initEnd.toString());
        Date all = new Date(initEnd.getTime() - init.getTime());
        System.out.println(String.format(
                "process took %d minutes %d seconds", all.getMinutes(), all.getSeconds())
        );
        System.out.println("total is " + total);
        System.out.println("and matches is " + match);

        Double matchd = Double.valueOf(match);
        Double totald = Double.valueOf(total);
        double error = matchd / totald;
        error = error * 100.0;
        error = 100.0 - error;

        System.out.println(String.format("so error rate is %f", error));
        System.out.println(String.format("with %d neighbors", neighborsAmount));
    }

    public static int[] getLabels(String filename) throws IOException {
        int[] result;
        ByteBuffer bb = ByteBuffer.wrap(IOUtils.toByteArray(new FileInputStream(new File(filename))));
        bb.order(ByteOrder.BIG_ENDIAN);

        if (bb.getInt() != LABELS_MAGIC_NUMBER) throw new IllegalArgumentException();

        int amountOfData = bb.getInt(); //todo waaat without var
        result = new int[amountOfData];
        for (int i = 0; i < amountOfData; i++)
            result[i] = (int) bb.get();
        //bb.get(); //true ^^ test of the end
        return result;
    }

    public static ArrayList<int[]> getImages(String filename) throws IOException {
        ArrayList<int[]> result = new ArrayList<>();
        ByteBuffer bb = ByteBuffer.wrap(IOUtils.toByteArray(new FileInputStream(new File(filename))));
        bb.order(ByteOrder.BIG_ENDIAN);

        if (bb.getInt() != IMAGES_MAGIC_NUMBER) throw new IllegalArgumentException();

        int imagesTotalAmount = bb.getInt();
        int pixelPerImage = bb.getInt() * bb.getInt();

        for (int i = 0; i < imagesTotalAmount; i++) {
            result.add(new int[pixelPerImage]);
            for (int j = 0; j < pixelPerImage; j++) {
                int nextPixel = bb.get() & 0xff;
                result.get(i)[j] = nextPixel;
            }
        }
        return result;
    }

    public static double getLengthBetween(int[] v1, int[] v2) {

        Double length = 0.0d;

        /*for (int i = 0; i < v1.length / 2; i++)
            //gives 1/5 less time for block
            //but 2x bigger error rate
            length += ((v1[i] - v2[i]) * (v1[i] - v2[i])) +
                        ((v1[i + 1] - v2[i + 1]) * (v1[i + 1] - v2[i + 1]));*/

        for (int i = 0; i < v1.length; i++)
            length += (v1[i] - v2[i]) * (v1[i] - v2[i]);

        //return Math.sqrt(length);
        return Double.longBitsToDouble(((Double.doubleToLongBits(length) - (1l << 52)) >> 1) + (1l << 61));
    }


}

class Neighbor implements Comparable<Neighbor> {
    int actualValue;
    double lengthBetween;

    public Neighbor(int actualValue, double lengthBetween) {
        this.actualValue = actualValue;
        this.lengthBetween = lengthBetween;
    }

    public int getActualValue() {
        return actualValue;
    }

    public void setActualValue(int actualValue) {
        this.actualValue = actualValue;
    }

    public double getLengthBetween() {
        return lengthBetween;
    }

    public void setLengthBetween(double lengthBetween) {
        this.lengthBetween = lengthBetween;
    }

    @Override
    public int compareTo(@NotNull Neighbor o) {
        if (this.getLengthBetween() < o.getLengthBetween()) return -1;
        else if (this.getLengthBetween() == o.getLengthBetween()) return 0;
        else return 1;
    }

    @Override
    public String toString() {
        return String.format("it is %d and diff is %f", this.getActualValue(), this.getLengthBetween());
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbor neighbor = (Neighbor) o;
        return actualValue == neighbor.actualValue &&
                Double.compare(neighbor.lengthBetween, lengthBetween) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(actualValue, lengthBetween);
    }*/
}
