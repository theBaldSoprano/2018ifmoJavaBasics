package com.study.itmo.gregory.lesson9.needles;

import java.util.Random;

public class BuffonsNeedle {
    /*
     *suppose that needle length equals 4cm
     *and length from stripe to stripe is 5cm
     *
     */
    public static boolean throwNeedleOneDimension(Random r) {
        /*
         *in this case we assume that
         * needle lands with X always equal 0
         */
        double needleLength = 3.819d;
        Coordinate A = new Coordinate(
                (double) r.nextInt(6) + r.nextDouble(),
                0.0d
        );
        Coordinate B = new Coordinate(
                A.y + needleLength,
                0.0d
        );
        if (B.y == 10 || A.y == 0) return true;
        else if (B.y >= 5 && A.y <= 5) return true;
        else return false;
    }

    public static boolean throwNeedleTwoDimensions(Random r) {

        Coordinate A = getRandomCordinate(r);
        Coordinate B = getRandomCordinate(r);
        double minLength = 4.4d;
        double maxLength = 4.45d;
        double length = 0;
        while (!(length >= minLength && length <= maxLength)) {
            A = getRandomCordinate(r);
            B = getRandomCordinate(r);
            length = getLength(A, B);
        }
        if (A.y <= 14 && B.y >= 14) return true;
        else if (B.y <= 14 && A.y >= 14) return true;
        else if (A.y <= 9 && B.y >= 9) return true;
        else if (B.y <= 9 && A.y >= 9) return true;
        else if (A.y <= 4 && B.y >= 4) return true;
        else if (B.y <= 4 && A.y >= 4) return true;
        else return false;

    }

    public static double getPi(int tries) {
        int crosses = 0;
        int throwsAmount = tries;
        Random random = new Random();
        for (int i = 0; i < throwsAmount; i++) {
            if (throwNeedleTwoDimensions(random)) crosses++;
        }
        System.out.println(
                String.format("throws: %d crosses: %d", throwsAmount, crosses)
        );
        return (double) (throwsAmount * 2) / (double) crosses;
    }

    public static Coordinate getRandomCordinate(Random r) {
        return new Coordinate(
                (double) r.nextInt(18) + r.nextDouble(),
                (double) r.nextInt(7) + r.nextDouble()
        );
    }

    public static double getLength(Coordinate A, Coordinate B) {
        return Math.sqrt(
                Math.pow((B.x - A.x), 2) + Math.pow((B.y - A.y), 2)
        );
    }

}

class Coordinate {
    double y;
    double x;

    public Coordinate(double y, double x) {
        this.y = y;
        this.x = x;
    }
}

