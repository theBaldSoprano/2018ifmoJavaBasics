package com.study.itmo.gregory.lesson2UnitTests;

import com.study.itmo.gregory.lesson2.Vector;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VectorTests {

    @Test
    public void GetVectorLength_EqualsExpectedValue_ReturnsTrue(){

        double EXPECTED = Math.sqrt(17);
        int X = 1;
        int Y = 0;
        int Z = -4;

        double actual = new Vector(X, Y, Z).getVectorLength();

        assertEquals(actual, EXPECTED);

    }

    @Test
    public void GetDotProduct_EqualsExpectedValue_ReturnsTrue(){

        double EXPECTED = 15;
        Vector v1 = new Vector(1, 2, -5);
        Vector v2 = new Vector(4, 8, 1);

        double actual1 = v1.getDotProduct(v2);
        double actual2 = v2.getDotProduct(v1);

        assertEquals(actual1, EXPECTED);
        assertEquals(actual2, EXPECTED);



    }

    @Test
    public void GetCrossProduct_EqualsExpectedValue_ReturnsTrue(){

        Vector v1 = new Vector(6, 7, 10);
        Vector v2 = new Vector(8, 5, 9);
        Vector EXPECTED = new Vector(13, 26, -26);

        Vector actual1 = v1.getCrossProduct(v2);

        assertTrue(actual1.equals(EXPECTED));

    }

    @Test
    public void GetCosBetween_EqualsExpectedValue_ReturnsTrue(){

        Vector v1 = new Vector(1, 44, 3);
        Vector v2 = new Vector(-44, 3, 1);
        double expected = 13.0 / 278.0;

        double actual = v1.getCosBetween(v2);

        assertEquals(actual, expected);


    }

    @Test
    public void GetVectorSum_EqualsExpectedValue_ReturnsTrue(){

        Vector v1 = new Vector(4, 8, -2);
        Vector v2 = new Vector(55, 3, 1);
        Vector EXPECTED = new Vector(59, 11, -1);

        Vector actual = v1.getVectorSum(v2);

        assertTrue(actual.equals(EXPECTED));
    }

    @Test
    public void GetVectorDiff_EqualsExpectedValue_ReturnsTrue(){

        Vector v1 = new Vector(4, 8, -2);
        Vector v2 = new Vector(55, 3, 1);
        Vector EXPECTED = new Vector(-51, 5, -3);

        Vector actual = v1.getVectorDiff(v2);

        assertTrue(actual.equals(EXPECTED));
    }

    @Test
    public void GetRandVectorArray_GoodInputSize_ReturnsTrue(){

        int size = 55;
        ArrayList<Vector> actual;

        actual = Vector.getRandVectorArray(size);

        assertTrue(actual.size() == size);

    }

}
