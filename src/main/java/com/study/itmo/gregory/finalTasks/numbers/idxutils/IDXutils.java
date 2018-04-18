package com.study.itmo.gregory.finalTasks.numbers.idxutils;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashSet;

import static com.study.itmo.gregory.finalTasks.numbers.idxutils.FilePaths.*;
import static java.lang.String.format;

public class IDXutils {
    /**
     * parser and utils for IDX files
     * containing handwritten digits images(MNIST)
     * taken from http://yann.lecun.com/exdb/mnist/
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {

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
        ArrayList<Integer> foo = getLabels(TEST_LABELS);
        System.out.println(foo.size());
        System.out.println(foo);


        ArrayList<Integer> foo1 = getLabels(TRAINING_LABELS);
        System.out.println(foo1.size());


    }

    public static ArrayList<Integer> getLabels(String filename) throws IOException {
        ArrayList<Integer> result = new ArrayList<>();
        ByteBuffer bb = ByteBuffer.wrap(IOUtils.toByteArray(new FileInputStream(new File(filename))));
        bb.order(ByteOrder.BIG_ENDIAN);

        if (bb.getInt() != LABELS_MAGIC_NUMBER) throw new IllegalArgumentException();

        int amountOfData = bb.getInt(); //todo waaat without var
        for (int i = 0; i < amountOfData; i++)
            result.add((int)bb.get());
        return result;
    }

    public static ArrayList<ArrayList<Integer>> getImages(String filename) throws IOException {
        ByteBuffer bb = ByteBuffer.wrap(IOUtils.toByteArray(new FileInputStream(new File(filename))));
        bb.order(ByteOrder.BIG_ENDIAN);

        System.out.println(format("the magic number is %d", bb.getInt()));
        int imagesAmount = bb.getInt();
        int pixelPerBlock = bb.getInt();
        for (int i = 0; i < imagesAmount; i++) {

        }
        return null;
    }
}
