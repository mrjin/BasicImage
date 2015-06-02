package me.bravojin.util;

import me.bravojin.filter.NonLinearFilterCal;

import java.awt.image.BufferedImage;
import java.awt.*;
/**
 * Created by tyrionlanister on 15-6-2.
 */
public class Convolution2D {
    public static BufferedImage Convolution(BufferedImage originImg,
                                            Object getCenterValue, int centerVel, int centerHor,
                                            Color background) {
        NonLinearFilterCal nonCal = (NonLinearFilterCal)getCenterValue;
        int conWidth = nonCal.getWidth();
        int conHeight = nonCal.getHeight();
        if(centerVel >= nonCal.getHeight()) {
            return null;
        }
        if(centerHor >= nonCal.getWidth()) {
            return null;
        }
        else {
            BufferedImage resultImg = originImg;
            int imgWidth = originImg.getWidth();
            int imgHeight = originImg.getHeight();
            for(int y = 0; y < imgHeight; y++) {
                for (int x = 0 ; x < imgWidth ; x++) {
                    if(y < centerVel || x < centerHor) {
                        resultImg.setRGB(x, y, originImg.getRGB(x, y));
                    }
                    else {
                        int [][] rgb = new int [conHeight][conWidth];
                        for(int i = -centerVel ; i < conHeight - centerVel ; i++) {
                            for(int j = -centerHor ; j < conWidth - centerHor ; j++) {
                                rgb[j][i] = originImg.getRGB(x+j, y+i);
                            }
                        }
                        resultImg.setRGB(x, y, nonCal.getCenterValue(rgb));
                    }
                }
            }
            return resultImg;
        }
    }
}
