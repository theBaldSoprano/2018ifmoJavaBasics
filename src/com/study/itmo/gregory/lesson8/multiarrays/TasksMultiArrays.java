package com.study.itmo.gregory.lesson8.multiarrays;

import java.util.Random;

public class TasksMultiArrays {

    public static int getMaxElement(int[][] array) {

        int max = Integer.MIN_VALUE;
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    if (max < array[i][j]) max = array[i][j];
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
        return max;
    }

    public static boolean isSquare(int[][] array) {

        int lines = array.length;

        for (int i = 0; i < array.length; i++) {
            if (array[i].length != lines)
                return false;
        }
        return true;
    }

    public static int[] maxString(int[][] array) {

        int string = 0;
        int stringSup = 0;
        for (int i = 0; i < array.length; i++) {
            int res = 0;
            for (int j = 0; j < array[i].length; j++) {
                res += array[i][j];
            }
            if (stringSup < res) {
                stringSup = res;
                string = i;
            }
        }
        return array[string];
    }

    public static int positiveDiagElemAmount(int[][] array) {
        int amount = 0;
        if (!isSquare(array)) throw new IllegalArgumentException();

        for (int i = 0; i < array.length; i++) {
            if (array[i][i] > 0) amount++;
        }
        return amount;
    }

    public static void fullRandom(int m, int n) {

        char[][] array = new char[m][n];
        Random ran = new Random();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = (char) ran.nextInt();
            }
        }
    }

    public static char findMostFreq(char[] a) {
        char[][] alphabet = {{'а', 0}, {'б', 0}, {'в', 0}, {'г', 0}, {'д', 0}, {'е', 0}, {'ж', 0}, {'з', 0}, {'и', 0}, {'к', 0}, {'л', 0},
                {'м', 0}, {'н', 0}, {'о', 0}, {'п', 0}, {'р', 0}, {'с', 0}, {'т', 0}, {'у', 0}, {'ф', 0}, {'х', 0}, {'ц', 0}, {'ч', 0}, {'щ', 0},
                {'ш', 0}, {'ъ', 0}, {'ь', 0}, {'э', 0}, {'ю', 0}, {'я', 0}, {'й', 0}, {'ц', 0}, {'ё', 0}};
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (alphabet[j][0] == a[i]) {
                    alphabet[j][1]++;
                }
            }
        }
        char mostFreq = alphabet[0][0];
        char max = 0;
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i][1] > max) {
                mostFreq = alphabet[i][0];
                max = alphabet[i][1];
            }
        }
        return mostFreq;
    }

    public static char[] symbols = {'a', 'b', 'c', 'd', 'e'};
    public static int[] weight = {80, 40, 5, 11, 50};

    public static char getCharRandomlyByWeight(char[] symbols, int[] weight) {

        int[] weightMap = new int[weight.length];
        for (int i = 0; i < weightMap.length; i++) {
            for (int j = 0; j < i + 1; j++) {
                weightMap[i] += weight[j];
            }
        }
        int ran = new Random().nextInt(weightMap[weightMap.length - 1]);

        for (int i = 0; i < weightMap.length; i++) {
            if (ran <= weightMap[i]) return symbols[i];
        }
        throw new IllegalArgumentException();
    }


    //доделать мультимассивы - символы и их вес и на рандом выпадают они по весам
    //todo поворот на 90

    //todo NOTE ** почитать ИГЛЫ бУФФОНА
    //реализовать вычисленние (поиск) числа ПИ
    //гугл полином и ряды тейлора
    //как хранить число пи формулой

}
