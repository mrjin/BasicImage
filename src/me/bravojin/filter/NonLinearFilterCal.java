package me.bravojin.filter;

/**
 * Created by tyrionlanister on 15-6-2.
 */
public interface NonLinearFilterCal {
    int getCenterValue(int [][] rgb);
    int getWidth();
    int getHeight();
}
