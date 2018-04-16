package com.study.itmo.gregory.finalTasks.numbers.idxutils;

import org.apache.commons.io.IOUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static com.study.itmo.gregory.finalTasks.numbers.idxutils.FilePaths.TRAINING_IMAGES;
import static java.lang.String.format;

import static com.study.itmo.gregory.finalTasks.numbers.idxutils.FilePaths.TRAINING_LABELS;

public class IDXutils {
    /**
     * parser and utils for IDX files
     * containing handwritten digits images(MNIST)
     * taken from http://yann.lecun.com/exdb/mnist/
     * @param args
     */
    public static void main(String[] args) throws IOException {

        byte [] trainLabels = IOUtils.toByteArray(new FileInputStream(new File(TRAINING_IMAGES)));
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
        }
    }
}
