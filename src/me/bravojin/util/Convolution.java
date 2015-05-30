package me.bravojin.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by tyrionlanister on 15-5-29.
 */
public class Convolution {
    public static BufferedImage verticalConvolution(BufferedImage originImg,
                                                    int [] kernel, int destination,
                                                    Color barginConvColor) {
        if(destination >= kernel.length) {
            return null;
        }
        else {
            BufferedImage resultImg = originImg;
            int imgWidth = originImg.getWidth();
            int imgHeight = originImg.getHeight();
            for(int y = 0; y < imgHeight; y++) {
                for (int x = 0 ; x < imgWidth ; x++) {
                    if(y < destination) {
                        int [] R = new int [kernel.length];
                        int [] G = new int [kernel.length];
                        int [] B = new int [kernel.length];
                        int flag = destination - y;
                        for(int i = 0; i < kernel.length - destination + y; i++) {
                            Color color = new Color(originImg.getRGB(x, i));
                            R[flag+i] = color.getRed() * kernel[flag+i];
                            G[flag+i] = color.getGreen() * kernel[flag+i];
                            B[flag+i] = color.getBlue() * kernel[flag+i];
                        }
                        for(int i = 0; i < flag ; i ++) {
                            R[i] = barginConvColor.getRed() * kernel[i];
                            G[i] = barginConvColor.getGreen() * kernel[i];
                            B[i] = barginConvColor.getBlue() * kernel[i];
                        }
                        int meanR = (int)Array.mean(R, Array.sum(kernel));
                        int meanG = (int)Array.mean(G, Array.sum(kernel));
                        int meanB = (int)Array.mean(B, Array.sum(kernel));
                        resultImg.setRGB(x, y,
                                new Color(meanR, meanG, meanB).getRGB());
                    }
                    else if(imgHeight - 1 - y < kernel.length - destination) {
                        int [] R = new int [kernel.length];
                        int [] G = new int [kernel.length];
                        int [] B = new int [kernel.length];
                        int flag = 0;
                        for(int i = y-destination ; i < imgHeight; i++) {
                            flag++;
                            Color color = new Color(originImg.getRGB(x, i));
                            R[destination+i-y] = color.getRed() * kernel[destination+i-y];
                            G[destination+i-y] = color.getGreen() * kernel[destination+i-y];
                            B[destination+i-y] = color.getBlue() * kernel[destination+i-y];
                        }
                        for(int i = 0 ; i < kernel.length - flag; i++) {
                            R[flag+i] = barginConvColor.getRed() * kernel[flag+i];
                            G[flag+i] = barginConvColor.getGreen() * kernel[flag+i];
                            B[flag+i] = barginConvColor.getBlue() * kernel[flag+i];
                        }
                        int meanR = (int)Array.mean(R, Array.sum(kernel));
                        int meanG = (int)Array.mean(G, Array.sum(kernel));
                        int meanB = (int)Array.mean(B, Array.sum(kernel));
                        resultImg.setRGB(x, y,
                                new Color(meanR, meanG, meanB).getRGB());
                    }
                    else {
                        int [] R = new int [kernel.length];
                        int [] G = new int [kernel.length];
                        int [] B = new int [kernel.length];
                        for(int i = -destination; i < kernel.length - destination; i++) {
                            Color color = new Color(originImg.getRGB(x, y+i));
                            R[destination+i] = color.getRed() * kernel[destination+i];
                            G[destination+i] = color.getGreen() * kernel[destination+i];
                            B[destination+i] = color.getBlue() * kernel[destination+i];
                        }
                        int meanR = (int)Array.mean(R, Array.sum(kernel));
                        int meanG = (int)Array.mean(G, Array.sum(kernel));
                        int meanB = (int)Array.mean(B, Array.sum(kernel));
                        resultImg.setRGB(x, y,
                                new Color(meanR, meanG, meanB).getRGB());
                    }
                }
            }
            return resultImg;
        }
    }
    public static BufferedImage horizontalConvolution(BufferedImage originImg,
                                                      int [] kernel, int destination,
                                                      Color barginConvColor) {
        if(destination > kernel.length - 1 || destination < 0) {
            return null;
        }
        else {
            BufferedImage resultImg = originImg;
            int imgWidth = originImg.getWidth();
            int imgHeight = originImg.getHeight();
            for(int y = 0 ; y < imgHeight ; y++ ) {
                for(int x = 0 ; x < imgWidth ; x++) {
                    if(x < destination) {
                        int [] R = new int [kernel.length];
                        int [] G = new int [kernel.length];
                        int [] B = new int [kernel.length];
                        int flag = destination - x;
                        for(int i = 0; i < kernel.length - destination + x; i++) {
                            Color color = new Color(originImg.getRGB(i, y));
                            R[flag+i] = color.getRed() * kernel[flag+i];
                            G[flag+i] = color.getGreen() * kernel[flag+i];
                            B[flag+i] = color.getBlue() * kernel[flag+i];
                        }
                        for(int i = 0; i < flag ; i ++) {
                            R[i] = barginConvColor.getRed() * kernel[i];
                            G[i] = barginConvColor.getGreen() * kernel[i];
                            B[i] = barginConvColor.getBlue() * kernel[i];
                        }
                        int meanR = (int)Array.mean(R, Array.sum(kernel));
                        int meanG = (int)Array.mean(G, Array.sum(kernel));
                        int meanB = (int)Array.mean(B, Array.sum(kernel));
                        resultImg.setRGB(x, y,
                                new Color(meanR, meanG, meanB).getRGB());
                    }
                    else if(imgWidth - 1 - x < kernel.length - destination) {
                        int [] R = new int [kernel.length];
                        int [] G = new int [kernel.length];
                        int [] B = new int [kernel.length];
                        int flag = 0;
                        for(int i = x-destination ; i < imgWidth; i++) {
                            flag++;
                            Color color = new Color(originImg.getRGB(i, y));
                            R[destination+i-x] = color.getRed() * kernel[destination+i-x];
                            G[destination+i-x] = color.getGreen() * kernel[destination+i-x];
                            B[destination+i-x] = color.getBlue() * kernel[destination+i-x];
                        }
                        for(int i = 0 ; i < kernel.length - flag; i++) {
                            R[flag+i] = barginConvColor.getRed() * kernel[flag+i];
                            G[flag+i] = barginConvColor.getGreen() * kernel[flag+i];
                            B[flag+i] = barginConvColor.getBlue() * kernel[flag+i];
                        }
                        int meanR = (int)Array.mean(R, Array.sum(kernel));
                        int meanG = (int)Array.mean(G, Array.sum(kernel));
                        int meanB = (int)Array.mean(B, Array.sum(kernel));
                        resultImg.setRGB(x, y,
                                new Color(meanR, meanG, meanB).getRGB());
                    }
                    else {
                        int [] R = new int [kernel.length];
                        int [] G = new int [kernel.length];
                        int [] B = new int [kernel.length];
                        for(int i = -destination; i < kernel.length - destination; i++) {
                            Color color = new Color(originImg.getRGB(x+i, y));
                            R[destination+i] = color.getRed() * kernel[destination+i];
                            G[destination+i] = color.getGreen() * kernel[destination+i];
                            B[destination+i] = color.getBlue() * kernel[destination+i];
                        }
                        int meanR = (int)Array.mean(R, Array.sum(kernel));
                        int meanG = (int)Array.mean(G, Array.sum(kernel));
                        int meanB = (int)Array.mean(B, Array.sum(kernel));
                        resultImg.setRGB(x, y,
                                new Color(meanR, meanG, meanB).getRGB());
                    }
                }
            }
            return resultImg;
        }
    }
    public static BufferedImage verticalConvolution(BufferedImage originImg,
                                                    double [] kernel, int destination,
                                                    Color barginConvColor) {
        if(destination >= kernel.length) {
            return null;
        }
        else {
            BufferedImage resultImg = originImg;
            int imgWidth = originImg.getWidth();
            int imgHeight = originImg.getHeight();
            for(int y = 0; y < imgHeight; y++) {
                for (int x = 0 ; x < imgWidth ; x++) {
                    if(y < destination) {
                        double [] R = new double [kernel.length];
                        double [] G = new double [kernel.length];
                        double [] B = new double [kernel.length];
                        int flag = destination - y;
                        for(int i = 0; i < kernel.length - destination + y; i++) {
                            Color color = new Color(originImg.getRGB(x, i));
                            R[flag+i] = color.getRed() * kernel[flag+i];
                            G[flag+i] = color.getGreen() * kernel[flag+i];
                            B[flag+i] = color.getBlue() * kernel[flag+i];
                        }
                        for(int i = 0; i < flag ; i ++) {
                            R[i] = barginConvColor.getRed() * kernel[i];
                            G[i] = barginConvColor.getGreen() * kernel[i];
                            B[i] = barginConvColor.getBlue() * kernel[i];
                        }
                        int meanR = (int)Array.mean(R, Array.sum(kernel));
                        int meanG = (int)Array.mean(G, Array.sum(kernel));
                        int meanB = (int)Array.mean(B, Array.sum(kernel));
                        resultImg.setRGB(x, y,
                                new Color(meanR, meanG, meanB).getRGB());
                    }
                    else if(imgHeight - 1 - y < kernel.length - destination) {
                        double [] R = new double [kernel.length];
                        double [] G = new double [kernel.length];
                        double [] B = new double [kernel.length];
                        int flag = 0;
                        for(int i = y-destination ; i < imgHeight; i++) {
                            flag++;
                            Color color = new Color(originImg.getRGB(x, i));
                            R[destination+i-y] = color.getRed() * kernel[destination+i-y];
                            G[destination+i-y] = color.getGreen() * kernel[destination+i-y];
                            B[destination+i-y] = color.getBlue() * kernel[destination+i-y];
                        }
                        for(int i = 0 ; i < kernel.length - flag; i++) {
                            R[flag+i] = barginConvColor.getRed() * kernel[flag+i];
                            G[flag+i] = barginConvColor.getGreen() * kernel[flag+i];
                            B[flag+i] = barginConvColor.getBlue() * kernel[flag+i];
                        }
                        int meanR = (int)Array.mean(R, Array.sum(kernel));
                        int meanG = (int)Array.mean(G, Array.sum(kernel));
                        int meanB = (int)Array.mean(B, Array.sum(kernel));
                        resultImg.setRGB(x, y,
                                new Color(meanR, meanG, meanB).getRGB());
                    }
                    else {
                        double [] R = new double [kernel.length];
                        double [] G = new double [kernel.length];
                        double [] B = new double [kernel.length];
                        for(int i = -destination; i < kernel.length - destination; i++) {
                            Color color = new Color(originImg.getRGB(x, y+i));
                            R[destination+i] = color.getRed() * kernel[destination+i];
                            G[destination+i] = color.getGreen() * kernel[destination+i];
                            B[destination+i] = color.getBlue() * kernel[destination+i];
                        }
                        int meanR = (int)Array.mean(R, Array.sum(kernel));
                        int meanG = (int)Array.mean(G, Array.sum(kernel));
                        int meanB = (int)Array.mean(B, Array.sum(kernel));
                        resultImg.setRGB(x, y,
                                new Color(meanR, meanG, meanB).getRGB());
                    }
                }
            }
            return resultImg;
        }
    }
    public static BufferedImage horizontalConvolution(BufferedImage originImg,
                                                      double [] kernel, int destination,
                                                      Color barginConvColor) {
        if(destination > kernel.length - 1 || destination < 0) {
            return null;
        }
        else {
            BufferedImage resultImg = originImg;
            int imgWidth = originImg.getWidth();
            int imgHeight = originImg.getHeight();
            for(int y = 0 ; y < imgHeight ; y++ ) {
                for(int x = 0 ; x < imgWidth ; x++) {
                    if(x < destination) {
                        double [] R = new double [kernel.length];
                        double [] G = new double [kernel.length];
                        double [] B = new double [kernel.length];
                        int flag = destination - x;
                        for(int i = 0; i < kernel.length - destination + x; i++) {
                            Color color = new Color(originImg.getRGB(i, y));
                            R[flag+i] = color.getRed() * kernel[flag+i];
                            G[flag+i] = color.getGreen() * kernel[flag+i];
                            B[flag+i] = color.getBlue() * kernel[flag+i];
                        }
                        for(int i = 0; i < flag ; i ++) {
                            R[i] = barginConvColor.getRed() * kernel[i];
                            G[i] = barginConvColor.getGreen() * kernel[i];
                            B[i] = barginConvColor.getBlue() * kernel[i];
                        }
                        int meanR = (int)Array.mean(R, Array.sum(kernel));
                        int meanG = (int)Array.mean(G, Array.sum(kernel));
                        int meanB = (int)Array.mean(B, Array.sum(kernel));
                        resultImg.setRGB(x, y,
                                new Color(meanR, meanG, meanB).getRGB());
                    }
                    else if(imgWidth - 1 - x < kernel.length - destination) {
                        double [] R = new double [kernel.length];
                        double [] G = new double [kernel.length];
                        double [] B = new double [kernel.length];
                        int flag = 0;
                        for(int i = x-destination ; i < imgWidth; i++) {
                            flag++;
                            Color color = new Color(originImg.getRGB(i, y));
                            R[destination+i-x] = color.getRed() * kernel[destination+i-x];
                            G[destination+i-x] = color.getGreen() * kernel[destination+i-x];
                            B[destination+i-x] = color.getBlue() * kernel[destination+i-x];
                        }
                        for(int i = 0 ; i < kernel.length - flag; i++) {
                            R[flag+i] = barginConvColor.getRed() * kernel[flag+i];
                            G[flag+i] = barginConvColor.getGreen() * kernel[flag+i];
                            B[flag+i] = barginConvColor.getBlue() * kernel[flag+i];
                        }
                        int meanR = (int)Array.mean(R, Array.sum(kernel));
                        int meanG = (int)Array.mean(G, Array.sum(kernel));
                        int meanB = (int)Array.mean(B, Array.sum(kernel));
                        resultImg.setRGB(x, y,
                                new Color(meanR, meanG, meanB).getRGB());
                    }
                    else {
                        double [] R = new double [kernel.length];
                        double [] G = new double [kernel.length];
                        double [] B = new double [kernel.length];
                        for(int i = -destination; i < kernel.length - destination; i++) {
                            Color color = new Color(originImg.getRGB(x+i, y));
                            R[destination+i] = color.getRed() * kernel[destination+i];
                            G[destination+i] = color.getGreen() * kernel[destination+i];
                            B[destination+i] = color.getBlue() * kernel[destination+i];
                        }
                        int meanR = (int)Array.mean(R,Array.sum(kernel));
                        int meanG = (int)Array.mean(G,Array.sum(kernel));
                        int meanB = (int)Array.mean(B,Array.sum(kernel));
                        resultImg.setRGB(x, y,
                                new Color(meanR, meanG, meanB).getRGB());
                    }
                }
            }
            return resultImg;
        }
    }
}
