package me.bravojin.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayTest {

    @Test
    public void testMaxInt() throws Exception {
        int [] TestArray1 = {1,2,5,20};
        assertEquals(20, Array.maxInt(TestArray1));
        int [] TestArray2 = {1,-10,20,25};
        assertEquals(25, Array.maxInt(TestArray2));
        int [] TestArray3 = new int [0];
        assertEquals(-1, Array.maxInt(TestArray3));
    }

    @Test
    public void testSecondInt() throws Exception {
        int [] TestArray1 = {1,-2,20,30};
        assertEquals(20,Array.secondInt(TestArray1));
        int [] TestArray2 = {1};
        assertEquals(-1, Array.secondInt(TestArray2));
        int [] TestArray3 = new int [0];
        assertEquals(-1, Array.secondInt(TestArray3));
    }

    @Test
    public void testMean() throws Exception {
        int [] TestArray1 = new int [0];
        assertEquals(-1.0, Array.mean(TestArray1), 1e-5);
        int [] TestArray2 = {-1,-2,2,1};
        assertEquals(0.0, Array.mean(TestArray2), 1e-5);
        int [] TestArray3 = {1,2,3,4,5};
        assertEquals(3.0, Array.mean(TestArray3), 1e-5);
    }

    @Test
    public void testMean1() throws Exception {
        int [] TestArray1 = new int [0];
        assertEquals(-1.0, Array.mean(TestArray1,0), 1e-5);
        int [] TestArray2 = {-1,-2,2,1};
        assertEquals(0.0, Array.mean(TestArray2,4.0), 1e-5);
        int [] TestArray3 = {1,2,3,4,5};
        assertEquals(3.0/7.0*5, Array.mean(TestArray3, 7.0), 1e-5);
    }

    @Test
    public void testMean2() throws Exception {
        double [] TestArray1 = new double [0];
        assertEquals(-1.0, Array.mean(TestArray1, 0.0), 1e-5);
        double [] TestArray2 = {-1.0, -2.0, 2.0, 1.0};
        assertEquals(0.0, Array.mean(TestArray2, 6.0), 1e-5);
        double [] TestArray3 = {1.1,2.2,3.3,4.4,5.5};
        assertEquals(3.3/7.0*5, Array.mean(TestArray3, 7.0), 1e-5);
    }

    @Test
    public void testSum() throws Exception {
        int [] TestArray1 = {1,2,3,4,5};
        assertEquals(15, Array.sum(TestArray1));
        int [] TestArray2 = {-1,-2,2,1};
        assertEquals(0, Array.sum(TestArray2));
        int [] TestArray3 = new int [0];
        assertEquals(0, Array.sum(TestArray3));
    }

    @Test
    public void testSum1() throws Exception {
        double [] TestArray1 = {0.1,0.2,0.3,0.4};
        assertEquals(1.0, Array.sum(TestArray1),1e-5);
        double [] TestArray2 = {0.1, 0.2, -0.1, -0.2};
        assertEquals(0.0, Array.sum(TestArray2),1e-5);
        double [] TestArray3 = new double [0];
        assertEquals(0.0, Array.sum(TestArray3),1e-5);
    }
}