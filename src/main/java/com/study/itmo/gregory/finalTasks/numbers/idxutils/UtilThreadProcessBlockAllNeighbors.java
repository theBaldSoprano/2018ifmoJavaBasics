package com.study.itmo.gregory.finalTasks.numbers.idxutils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.study.itmo.gregory.finalTasks.numbers.idxutils.IDXutils.*;

//import static com.study.itmo.gregory.finalTasks.numbers.idxutils.IDXutils.totalAmount;

/**
 * this thread processes all training images the way
 * that after compare iteration youll have 60000 objects(neighbors)
 * each with its own length from test image.
 * thus while having need in only 5-100 neighbors
 * creating 60k objects is arguable
 */
public class UtilThreadProcessBlockAllNeighbors extends Thread {
    private static final Object monitor2 = new Object();
    private static final Object monitor3 = new Object();

    private int start;
    private int end;

    public UtilThreadProcessBlockAllNeighbors(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {

            Neighbor[] neighbors = new Neighbor[trainLabels.length];//60000 images for training
            int[] testImage = testImages.get(i);
            int testLabel = testLabels[i];
            int actualLabel = -1;

            for (int j = 0; j < 60000; j++) {
                double length = getLengthBetween(testImage, trainImages.get(j));
                neighbors[j] = new Neighbor(trainLabels[j], length);
            }

            Arrays.sort(neighbors);

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
                synchronized (monitor2){
                    match++;
                    //пока занят монитор в блоке синхронайзд можент быть только один тред!!
                    //команды wait и notify нужны для того, чтобы внутри синхронайзд блока можно было менять логику
                    //например один поток ждет и при этом освобождает монитор
                    //а второй заходит что-то делает и делает нитифай и пред. поток размораживается
                }
            }
            synchronized (monitor3) {
                    total++;
                    System.out.print(String.format("\r%d of %d images done || good match is %d",
                            total,
                            testLabels.length, match));

            }
        }
    }
}

