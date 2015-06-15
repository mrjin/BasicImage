package me.bravojin.LayerContent.LayerSuper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-15.
 */
public class LayerSuperDifference implements LayerPixelCalInterface{
    public int RGBPixel(int rgbDown, int rgbUp) {
        Color down = new Color(rgbDown);
        Color up = new Color(rgbUp);
        return (new Color(difference(down.getRed(), up.getRed()),
                difference(down.getGreen(), up.getGreen()),
                difference(down.getBlue(), up.getBlue()))).getRGB();
    }

    private int difference(int rgbdown, int rgbup) {
        return Math.abs(rgbdown - rgbup);
    }

    public static BufferedImage generate(BufferedImage originDown, BufferedImage originUp) {
        return BasicLayerPixelCal.pixelCal(originDown, originUp, new LayerSuperDifference());
    }
}
