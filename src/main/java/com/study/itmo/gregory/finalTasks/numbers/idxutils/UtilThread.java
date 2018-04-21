package com.study.itmo.gregory.finalTasks.numbers.idxutils;

import java.util.ArrayList;

import static com.study.itmo.gregory.finalTasks.numbers.idxutils.IDXutils.getLengthBetween;

public class UtilThread extends Thread{

    private int start;
    private int end;
    private int[] testImage;
    private ArrayList<int[]> trainImages;
    private int[] trainLabels;
    private Neighbor[] neighbors;

    public UtilThread(int start, int end, int[] testImage, ArrayList<int[]> trainImages, int[] trainLabels, Neighbor[] neighbors) {
        this.start = start;
        this.end = end;
        this.testImage = testImage;
        this.trainImages = trainImages;
        this.trainLabels = trainLabels;
        this.neighbors = neighbors;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            double length = getLengthBetween(testImage, trainImages.get(i));
            neighbors[i] = new Neighbor(trainLabels[i], length);
        }
    }
}
