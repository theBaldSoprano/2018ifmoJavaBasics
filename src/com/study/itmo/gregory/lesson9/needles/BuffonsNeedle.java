package com.study.itmo.gregory.lesson9.needles;

import java.util.Random;

public class BuffonsNeedle {
    /*
     *suppose that needle length equals 4cm
     *and length from stripe to stripe is 5cm
     *
     */
    public static boolean throwNeedle(Random r) {
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

    public static double getPi(int tries){
        int crosses = 0;
        int throwsAmount = tries;
        Random random = new Random();
        for (int i = 0; i < throwsAmount; i++) {
            if (throwNeedle(random)) crosses++;
        }
        System.out.println(
                String.format("throws: %d crosses: %d", throwsAmount, crosses)
        );
        return (double)(throwsAmount * 2) / (double)crosses;
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

