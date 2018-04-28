package com.study.itmo.gregory.finalTasks.numbers.idxutils;


import java.util.*;

import static com.study.itmo.gregory.finalTasks.numbers.idxutils.IDXutils.*;

//import static com.study.itmo.gregory.finalTasks.numbers.idxutils.IDXutils.totalAmount;

/**
 * this thread processes all training images the way
 * that after compare iteration youll have 60000 objects(neighbors)
 * each with its own length from test image.
 * thus while having need in only 5-100 neighbors
 * creating 60k objects is arguable
 */
public class UtilThreadProcessBlockNeededNeighbors extends Thread {
    private static final Object monitor2 = new Object();
    private static final Object monitor3 = new Object();

    /**
     * start and end points in training array to process
     */
    private int start;
    private int end;

    public UtilThreadProcessBlockNeededNeighbors(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            LinkedList<Neighbor> neighbors = new LinkedList<>();

            int[] testImage = testImages.get(i);
            int testLabel = testLabels[i];
            int actualLabel = -1;

            for (int j = 0; j < 60000; j++) {
                double length = getLengthBetween(testImage, trainImages.get(j));
                if (neighbors.size() < neighborsAmount) {
                    neighbors.add(new Neighbor(trainLabels[j], length));
                    Collections.sort(neighbors);
                } else {
                    if (neighbors.getLast().getLengthBetween() > length) {
                        neighbors.removeLast();
                        neighbors.add(new Neighbor(trainLabels[j], length));
                        Collections.sort(neighbors);
                    }
                }
            }
            HashMap<Integer, Integer> bestMatch = new HashMap<>();
            for (Neighbor n : neighbors) {
                if (!bestMatch.containsKey(n.getActualValue())) {
                    bestMatch.put(n.getActualValue(), 1);
                } else {
                    bestMatch.put(n.getActualValue(), bestMatch.get(n.getActualValue()) + 1);
                }
            }
            int maxOccurrences = 0;
            for (Map.Entry<Integer, Integer> pair : bestMatch.entrySet()){
                if (pair.getValue() > maxOccurrences){
                    maxOccurrences = pair.getValue();
                    actualLabel = pair.getKey();
                }
            }
            //ну и сравнить полученный с реальным тестовым
            if (actualLabel == testLabel) {
                synchronized (monitor2) {
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


