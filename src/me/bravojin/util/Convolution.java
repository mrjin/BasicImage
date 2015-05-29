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
                            R[flag+i] = color.getRed();
                            G[flag+i] = color.getGreen();
                            B[flag+i] = color.getBlue();
                        }
                        for(int i = 0; i < flag ; i ++) {
                            R[i] = barginConvColor.getRed();
                            G[i] = barginConvColor.getGreen();
                            B[i] = barginConvColor.getBlue();
                        }
                        int meanR = (int)Array.mean(R);
                        int meanG = (int)Array.mean(G);
                        int meanB = (int)Array.mean(B);
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
                            R[destination+i-y] = color.getRed();
                            G[destination+i-y] = color.getGreen();
                            B[destination+i-y] = color.getBlue();
                        }
                        for(int i = 0 ; i < kernel.length - flag; i++) {
                            R[flag+i] = barginConvColor.getRed();
                            G[flag+i] = barginConvColor.getGreen();
                            B[flag+i] = barginConvColor.getBlue();
                        }
                        int meanR = (int)Array.mean(R);
                        int meanG = (int)Array.mean(G);
                        int meanB = (int)Array.mean(B);
                        resultImg.setRGB(x, y,
                                new Color(meanR, meanG, meanB).getRGB());
                    }
                    else {
//                        int [] colorRGB = new int [kernel.length];
//                        for(int i = -destination ; i < kernel.length - destination; i++) {
//                            colorRGB[destination+i] = originImg.getRGB(x, y+i);
//                        }
//                        int meanColorRGB = (int)Array.mean(colorRGB);
//                        resultImg.setRGB(x, y, meanColorRGB);
                        int [] R = new int [kernel.length];
                        int [] G = new int [kernel.length];
                        int [] B = new int [kernel.length];
                        for(int i = -destination; i < kernel.length - destination; i++) {
                            Color color = new Color(originImg.getRGB(x, y+i));
                            R[destination+i] = color.getRed();
                            G[destination+i] = color.getGreen();
                            B[destination+i] = color.getBlue();
                        }
                        int meanR = (int)Array.mean(R);
                        int meanG = (int)Array.mean(G);
                        int meanB = (int)Array.mean(B);
                        resultImg.setRGB(x, y,
                                new Color(meanR, meanG, meanB).getRGB());
                    }
                }
            }
            return resultImg;
        }
    }
}
