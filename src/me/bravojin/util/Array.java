package me.bravojin.util;

/**
 * Created by tyrionlanister on 15-5-28.
 */
public class Array {
    public static int maxInt(int [] A) {
        if(A.length == 0) {
            return -1;
        }
        else {
            int max = A[0];
            for(int i = 0 ; i < A.length ; i++) {
                max = max > A[i]? max : A[i];
            }
            return max;
        }
    }

    public static int secondInt(int [] A) {
        if(A.length < 2) {
            return -1;
        }
        else {
            int max = A[0];
            int second = A[0];
            for(int i = 0 ; i < A.length ; i++) {
                if(max < A[i]) {
                    second = max;
                    max = A[i];
                }
            }
            return second;
        }
    }

    public static double mean(int [] A) {
        if(A.length == 0) {
            return -1.0;
        }
        int sum = 0;
        for(int i = 0 ; i < A.length; i++) {
            sum += A[i];
        }
        return ((double)sum)/A.length;
    }

    public static double mean(int [] A, double paramSum) {
        if(Math.abs(paramSum - 0.0) < 1e-5) {
            return -1;
        }
        int sum = 0;
        for(int i = 0 ; i < A.length; i++) {
            sum += A[i];
        }
        return ((double)sum)/paramSum;
    }

    public static double mean(double [] A, double paramSum) {
        if(Math.abs(paramSum - 0.0) < 1e-5) {
            return -1;
        }
        double sum = 0;
        for(int i = 0 ; i < A.length; i++) {
            sum += A[i];
        }
        return sum/paramSum;
    }

    public static int sum(int [] A) {
        int sum = 0;
        for(int i = 0 ; i < A.length; i++) {
            sum += A[i];
        }
        return sum;
    }

    public static double sum(double [] A) {
        double sum = 0;
        for(int i = 0 ; i < A.length; i++) {
            sum += A[i];
        }
        return sum;
    }
}
