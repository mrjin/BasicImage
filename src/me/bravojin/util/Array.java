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
        if(A.length == 0) {
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
        int sum = 0;
        for(int i = 0 ; i < A.length; i++) {
            sum += A[i];
        }
        return ((double)sum)/A.length;
    }
}
