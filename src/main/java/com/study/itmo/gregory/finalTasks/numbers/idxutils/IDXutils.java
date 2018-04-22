package com.study.itmo.gregory.finalTasks.numbers.idxutils;

import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

import static com.study.itmo.gregory.finalTasks.numbers.idxutils.FilePaths.*;
import static java.lang.String.format;

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




    public static void main(String[] args) throws IOException, InterruptedException {

        Date init = new Date();
        System.out.println("process started at " + init.toString());

        /*byte [] trainLabels = IOUtils.toByteArray(new FileInputStream(new File(TRAINING_IMAGES)));
        System.out.println(format("train trainLabels file is %d bytes long", trainLabels.length));

        ByteBuffer bb = ByteBuffer.wrap(trainLabels);
        bb.order(ByteOrder.BIG_ENDIAN);

        System.out.println(format("the magic number is %d", bb.getInt()));
        System.out.println(format("total number of labels is %d", bb.getInt()));
        System.out.println(format("number of rows is %d", bb.getInt()));
        System.out.println(format("number col is %d", bb.getInt()));

        for (int i = 0; i < 1000; i++) {
            byte bar = bb.get();
            if (bar != 0) {
                int foo = bar & 0xff;
                System.out.println(format("pixel in byte is %d but in int is %d", bar, foo));
            }
        }*/
        /*ArrayList<Integer> foo = getLabels(TEST_LABELS);
        System.out.println(foo.size());
        System.out.println(foo);
        ArrayList<Integer> foo1 = getLabels(TRAINING_LABELS);
        System.out.println(foo1.size());*/
        /*ArrayList<ArrayList<Integer>> images = getImages(TRAINING_IMAGES);
        System.out.println(images.size());
        for (ArrayList<Integer> arr : images) {
            System.out.println("****************************SIZE IS " + arr.size());
            for (Integer i : arr) {
                System.out.println(i);
            }
        }*/

        int match = 0;
        int neighborsAmount = 5;//on 27 == 5; on 5 == 3.2%

        double middleBlock = 0;
        double middleLength = 0;

        /*System.out.println("********************8888");
        System.out.println("here is first 20 and last 20 ");
        System.out.println("********************8888");*/
        for (int i = 0; i < 500; i++) {
            //for (int i = 0; i < testLabels.size(); i++) {

            //System.out.println("********************************************************");
            Date startTime = new Date();
            //System.out.println("start is " + (startTime.getTime() - startTime.getTime()));

            //for (int i = 0; i < 110; i++) {
            //массив чтобы хранить соседей 60 тыщ
            //массив хранящий Энный сет изображения который будем сравнивать
            //реальное число этой картинки
            //реально полученно число будет в конце переписано полученным
            Neighbor[] neighbors = new Neighbor[trainLabels.length];//60000 images for training
            //System.out.println("neigbors array length is " + neighbors.length);
            int[] testImage = testImages.get(i);
            int testLabel = testLabels[i];
            //System.out.println(String.format("waiting for %d", testLabel));
            int actualLabel = -1;
            //последовательно заполняем соседей
            //лэйблом и соотв дальностью от тестового образца

            Date beforeLengthProcessing = new Date();
            //System.out.println("timestamp before length processing is " + (beforeLengthProcessing.getTime() - startTime.getTime()));

            /*for (int j = 0; j < trainLabels.length; j++) {
                double length = getLengthBetween(testImage, trainImages.get(j));
                *//*if (length == 0.0){
                    System.out.println("******************************************************");
                    System.out.println(String.format("its %d", testLabel));
                    System.out.println(testImage);
                    System.out.println(String.format("and this is supposed to be %d", trainLabels.get(j)));
                    System.out.println(trainImages.get(j));
                    System.out.println("******************************************************");
                }*//*
                neighbors[j] = new Neighbor(trainLabels[j], length);
            }*/

            UtilThreadProcessLength first = new UtilThreadProcessLength(0, 15000, testImage, trainImages, trainLabels, neighbors);
            UtilThreadProcessLength second = new UtilThreadProcessLength(15000, 30000, testImage, trainImages, trainLabels, neighbors);
            UtilThreadProcessLength third = new UtilThreadProcessLength(30000, 45000, testImage, trainImages, trainLabels, neighbors);
            UtilThreadProcessLength fourth = new UtilThreadProcessLength(45000, 60000, testImage, trainImages, trainLabels, neighbors);
            first.start();
            second.start();
            third.start();
            fourth.start();

            first.join();
            second.join();
            third.join();
            fourth.join();

            Date afterLengthProcessing = new Date();
            //System.out.println("timestamp after length processing is " + (afterLengthProcessing.getTime() - startTime.getTime()));
            //System.out.println(String.format("length processing took %d milliseconds", afterLengthProcessing.getTime() - beforeLengthProcessing.getTime()));
            middleLength = middleLength + (afterLengthProcessing.getTime() - beforeLengthProcessing.getTime());

            /*System.out.println("BEFORE sort*************************************");
            for (int j = 0; j < 200; j++) {
                System.out.println(neighbors[j]);
            }*/
            //сортируем соседей от меньшей дистанции к большей

            Date beforeSort = new Date();
            //System.out.println("timestamp before sort is " + (beforeSort.getTime() - startTime.getTime()));

            Arrays.sort(neighbors);

            Date afterSort = new Date();
            //System.out.println("timestamp after sort is " + (afterSort.getTime() - startTime.getTime()));
            //System.out.println(String.format("sort took %d milliseconds", afterSort.getTime() - beforeSort.getTime()));

            //System.out.println("AFTER sort*************************************");
            /*for (int j = 0; j < 200; j++) {
                System.out.println(neighbors[j]);
            }*/
            //get array with N best matches
            Neighbor[] bestMatch = new Neighbor[neighborsAmount];
            for (int j = 0; j < neighborsAmount; j++) {
                bestMatch[j] = neighbors[j];
            }
            //засунуть в мапу лэйблы и количества их попаданий
            HashMap<Integer, Integer> tmp = new HashMap<>();
            for (Neighbor foo : bestMatch) {
                int key = foo.getActualValue();
                if (!tmp.containsKey(key)) {
                    tmp.put(key, 1);
                } else {
                    int resultValue = tmp.get(key) + 1;
                    tmp.put(key, resultValue);
                }
            }
            //найти самый упоминаемый лэйбл
            int mostFrequentMatch = 1;
            for (Map.Entry<Integer, Integer> pair : tmp.entrySet()) {
                if (pair.getValue() > mostFrequentMatch) {
                    mostFrequentMatch = pair.getValue();
                }
            }
            //вытащить его
            for (Map.Entry<Integer, Integer> pair : tmp.entrySet()) {
                if (pair.getValue() == mostFrequentMatch) {
                    actualLabel = pair.getKey();
                }
            }
            //ну и сравнить полученный с реальным тестовым
            if (actualLabel == testLabel) {
                match++;
            }
            Date endTime = new Date();
            middleBlock = middleBlock + (endTime.getTime() - startTime.getTime());
            //System.out.println(String.format("all block took %d milliseconds", endTime.getTime() - startTime.getTime()));
            //System.out.println("********************************************************");

            System.out.print(String.format("\r%d of %d images done || error is %f percent",
                    i + 1,
                    testLabels.length,
                    (100 - ((double) match / (i + 1)) * 100)));
        }
        middleBlock = middleBlock / 500.0;
        System.out.println();
        System.out.println("middle time for block was: " + middleBlock);
        middleLength = middleLength / 500.0;
        System.out.println("middle time for length was: " + middleLength);


        /*Neighbor [] neighbors = new Neighbor[trainLabels.size()];
        ArrayList<Integer> testImage = testImages.get(444);
        int testLabel = testLabels.get(444);
        for (int i = 0; i < trainLabels.size(); i++) {
            double length = getLengthBetween(testImage, trainImages.get(i));
            neighbors[i] = new Neighbor(trainLabels.get(i), length);
        }
        System.out.println("ACTUAL VALUE IS: " + testLabel);
        Arrays.sort(neighbors);
        System.out.println("THESE ARE FIVE CLOSEST NEIGHBORS");
        System.out.println("-------------------------");
        for (int i = 0; i < 5; i++) {
            System.out.print("value is " + neighbors[i].actualValue);
            System.out.println("  difference is " + neighbors[i].getLengthBetween());
            System.out.println("-------------------------");
        }*/
        Date initEnd = new Date();
        System.out.println("process ended at " + initEnd.toString());
        System.out.println(String.format(
                "process took %d minutes", new Date(initEnd.getTime() - init.getTime()).getMinutes())
        );
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
        return Double.longBitsToDouble( ( ( Double.doubleToLongBits( length )-(1l<<52) )>>1 ) + ( 1l<<61 ) );
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
}
