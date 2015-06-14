package me.bravojin.views.util;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-13.
 */
public class CutImage {
    public static BufferedImage cutImage(int width, int height, BufferedImage originImg) {
        BufferedImage resultImg = new BufferedImage(width, height, originImg.getType());
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                resultImg.setRGB(x, y, originImg.getRGB(x, y));
            }
        }
        return resultImg;
    }
}
