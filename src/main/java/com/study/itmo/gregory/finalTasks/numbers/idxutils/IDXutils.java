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
     *
     * todo оптимизировать
     * todo другим способом не соседями
     * todo
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {

        System.out.println("process started at " + new Date().toString());

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
        //upload and import all labels and images files
        ArrayList<Integer> trainLabels = getLabels(TRAINING_LABELS);//60 000
        ArrayList<ArrayList<Integer>> trainImages = getImages(TRAINING_IMAGES);//4 ляма

        ArrayList<Integer> testLabels = getLabels(TEST_LABELS);
        //ArrayList<ArrayList<Integer>> testImages = getImages(TRAINING_IMAGES);
        ArrayList<ArrayList<Integer>> testImages = getImages(TEST_IMAGES);
        int match = 0;
        int neighborsAmount = 27;

        /*System.out.println("********************8888");
        System.out.println("here is first 20 and last 20 ");
        System.out.println("********************8888");*/

        for (int i = 0; i < testLabels.size(); i++) {
        //for (int i = 0; i < 110; i++) {
            //массив чтобы хранить соседей 60 тыщ
            //массив хранящий Энный сет изображения который будем сравнивать
            //реальное число этой картинки
            //реально полученно число будет в конце переписано полученным
            Neighbor[] neighbors = new Neighbor[trainLabels.size()];//60000 images for training
            //System.out.println("neigbors array length is " + neighbors.length);
            ArrayList<Integer> testImage = testImages.get(i);
            int testLabel = testLabels.get(i);
            //System.out.println(String.format("waiting for %d", testLabel));
            int actualLabel = -1;
            //последовательно заполняем соседей
            //лэйблом и соотв дальностью от тестового образца
            for (int j = 0; j < trainLabels.size(); j++) {
                double length = getLengthBetween(testImage, trainImages.get(j));
                /*if (length == 0.0){
                    System.out.println("******************************************************");
                    System.out.println(String.format("its %d", testLabel));
                    System.out.println(testImage);
                    System.out.println(String.format("and this is supposed to be %d", trainLabels.get(j)));
                    System.out.println(trainImages.get(j));
                    System.out.println("******************************************************");
                }*/
                neighbors[j] = new Neighbor(trainLabels.get(j), length);
            }
            /*System.out.println("BEFORE sort*************************************");
            for (int j = 0; j < 200; j++) {
                System.out.println(neighbors[j]);
            }*/
            //сортируем соседей от меньшей дистанции к большей
            Arrays.sort(neighbors);
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
            System.out.print(String.format("\rprocessed %d of %d data blocks", i + 1, testLabels.size()));
            System.out.print(String.format(" *** currently got %d matches", match));
            System.out.print(String.format(" *** current error rate is %f percent", (100 -((double)match / (i + 1)) * 100)));

        }

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
    }

    public static ArrayList<Integer> getLabels(String filename) throws IOException {
        ArrayList<Integer> result = new ArrayList<>();
        ByteBuffer bb = ByteBuffer.wrap(IOUtils.toByteArray(new FileInputStream(new File(filename))));
        bb.order(ByteOrder.BIG_ENDIAN);

        if (bb.getInt() != LABELS_MAGIC_NUMBER) throw new IllegalArgumentException();

        int amountOfData = bb.getInt(); //todo waaat without var
        for (int i = 0; i < amountOfData; i++)
            result.add((int) bb.get());
        //bb.get(); //true ^^ test of the end
        return result;
    }

    public static ArrayList<ArrayList<Integer>> getImages(String filename) throws IOException {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ByteBuffer bb = ByteBuffer.wrap(IOUtils.toByteArray(new FileInputStream(new File(filename))));
        bb.order(ByteOrder.BIG_ENDIAN);

        if (bb.getInt() != IMAGES_MAGIC_NUMBER) throw new IllegalArgumentException();

        int imagesTotalAmount = bb.getInt();
        int pixelPerImage = bb.getInt() * bb.getInt();

        for (int i = 0; i < imagesTotalAmount; i++) {
            result.add(new ArrayList<>());
            for (int j = 0; j < pixelPerImage; j++) {
                int nextPixel = bb.get() & 0xff;
                result.get(i).add(nextPixel);
            }
        }
        return result;
    }

    public static double getLengthBetween(ArrayList<Integer> v1, ArrayList<Integer> v2) {
        ArrayList<Integer> diff = new ArrayList<>();
        Double length = 0.0d;

        for (int i = 0; i < v1.size(); i++)
            diff.add(v1.get(i) - v2.get(i));
        for (Integer i : diff)
            length += Math.pow(i, 2);
        return Math.sqrt(Math.abs(length));
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
