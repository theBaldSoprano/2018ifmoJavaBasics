package com.study.itmo.gregory.finalTasks.numbers.idxutils;

import java.util.*;

//import static com.study.itmo.gregory.finalTasks.numbers.idxutils.IDXutils.totalAmount;

public class UtilThreadProcessBlock extends Thread {

    private static final Object monitor = new Object();

    private int start;
    private int end;
    private int[] trainLabels;
    private ArrayList<int[]> trainImages;
    private ArrayList<int[]> testImages;
    private int[] testLabels;
    private Integer match;
    private Integer neighborsAmount;
    private Double middleBlock;
    private Double middleLength;

    public UtilThreadProcessBlock(int start, int end,
                                  int[] trainLabels,
                                  ArrayList<int[]> trainImages,
                                  ArrayList<int[]> testImages,
                                  int[] testLabels,
                                  Integer match,
                                  Integer neighborsAmount,
                                  Double middleBlock,
                                  Double middleLength) {
        this.start = start;
        this.end = end;
        this.trainLabels = trainLabels;
        this.trainImages = trainImages;
        this.testImages = testImages;
        this.testLabels = testLabels;
        this.match = match;
        this.neighborsAmount = neighborsAmount;
        this.middleBlock = middleBlock;
        this.middleLength = middleLength;
    }

    @Override
    public void run() {
        synchronized (monitor) {
            for (int i = start; i < end; i++){
                try {
                    monitor.notify();
                    //totalAmount++;
                    Date startTime = new Date();
                    Neighbor[] neighbors = new Neighbor[trainLabels.length];//60000 images for training
                    int[] testImage = testImages.get(i);
                    int testLabel = testLabels[i];
                    int actualLabel = -1;

                    Date beforeLengthProcessing = new Date();

                    UtilThreadProcessLength first = new UtilThreadProcessLength(0, 30000, testImage, trainImages, trainLabels, neighbors);
                    UtilThreadProcessLength second = new UtilThreadProcessLength(30000, 60000, testImage, trainImages, trainLabels, neighbors);
                    first.start();
                    second.start();

                    first.join();
                    second.join();

                    Date afterLengthProcessing = new Date();
                    middleLength = middleLength + (afterLengthProcessing.getTime() - beforeLengthProcessing.getTime());
                    //сортируем соседей от меньшей дистанции к большей
                    Date beforeSort = new Date();
                    Arrays.sort(neighbors);
                    Date afterSort = new Date();
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

                    //System.out.print(String.format("\r%d of %d images done || error is %f percent",
                            //totalAmount,
                            //testLabels.length,
                            //(100 - ((double) match / (totalAmount)) * 100)));

                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            monitor.notify();
        }
    }
}
