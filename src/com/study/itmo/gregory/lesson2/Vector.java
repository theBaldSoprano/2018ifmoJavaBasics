package com.study.itmo.gregory.lesson2;

import java.util.ArrayList;
import java.util.Random;

public class Vector {

    public static ArrayList<Vector> getRandVectorArray(int n) {

        if (n < 0) throw new IllegalArgumentException();

        ArrayList<Vector> array = new ArrayList<>();

        Random ran = new Random();

        for (int i = 0; i < n; i++) {
            array.add(new Vector(
                            ran.nextInt(),
                            ran.nextInt(),
                            ran.nextInt()
                    )
            );
        }

        return array;
    }


    public int x;
    public int y;
    public int z;

    public Vector(int x, int y, int z) {

        this.x = x;
        this.y = y;
        this.z = z;

    }

    @Override
    public boolean equals(Object v) {

        if (v instanceof Vector) {

            if (this.x == ((Vector) v).x &&
                    this.y == ((Vector) v).y &&
                    this.z == ((Vector) v).z) {
                return true;
            } else return false;
        }
        throw new IllegalArgumentException();
    }

    public double getVectorLength() {

        double result = Math.pow(this.x, 2) +
                Math.pow(this.y, 2) +
                Math.pow(this.z, 2);

        result = Math.sqrt(result);

        return Math.abs(result);

    }

    public double getDotProduct(Vector inputVector) {

        return
                this.x * inputVector.x +
                        this.y * inputVector.y +
                        this.z * inputVector.z;

    }

    public Vector getCrossProduct(Vector inputVector) {

        int newX = this.y * inputVector.z - this.z * inputVector.y;
        int newY = this.z * inputVector.x - this.x * inputVector.z;
        int newZ = this.x * inputVector.y - this.y * inputVector.x;

        return new Vector(newX, newY, newZ);

    }

    public double getCosBetween(Vector v) {

        double dotProduct = this.getDotProduct(v);

        double lengthProduct = this.getVectorLength() * v.getVectorLength();

        return dotProduct / lengthProduct;

    }

    public Vector getVectorSum(Vector v) {
        return new Vector(
                this.x + v.x,
                this.y + v.y,
                this.z + v.z);
    }

    public Vector getVectorDiff(Vector v) {
        return new Vector(
                this.x - v.x,
                this.y - v.y,
                this.z - v.z
        );
    }

}
