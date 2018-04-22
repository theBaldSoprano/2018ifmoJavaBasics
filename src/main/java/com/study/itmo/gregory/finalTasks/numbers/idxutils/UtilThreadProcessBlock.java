package com.study.itmo.gregory.finalTasks.numbers.idxutils;

import java.util.*;

import static com.study.itmo.gregory.finalTasks.numbers.idxutils.IDXutils.*;

//import static com.study.itmo.gregory.finalTasks.numbers.idxutils.IDXutils.totalAmount;

public class UtilThreadProcessBlock extends Thread {

    private static final Object monitor1 = new Object();
    private static final Object monitor2 = new Object();
    private static final Object monitor3 = new Object();

    private int start;
    private int end;

    public UtilThreadProcessBlock(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {

        for (int i = start; i < end; i++) {
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

            try {
                first.join();
                second.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            Date afterLengthProcessing = new Date();
            synchronized (monitor1) {
                try {
                    monitor1.notifyAll();
                    //totalDone++;
                    middleLength = middleLength + (afterLengthProcessing.getTime() - beforeLengthProcessing.getTime());
                    monitor1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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

            synchronized (monitor2) {
                try {
                    monitor2.notifyAll();
                    middleLength = middleLength + (afterLengthProcessing.getTime() - beforeLengthProcessing.getTime());
                    monitor2.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (monitor3) {
                try {
                    monitor3.notifyAll();
                    System.out.print(String.format("\r%d of %d images done || error is %f percent",
                            testImage.length,
                            testLabels.length,
                            (100 - ((double) match / (250)) * 100)));
                    monitor3.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

