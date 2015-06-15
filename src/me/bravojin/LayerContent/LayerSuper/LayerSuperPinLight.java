package me.bravojin.LayerContent.LayerSuper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-14.
 */
public class LayerSuperPinLight implements LayerPixelCalInterface{
    public int RGBPixel(int rgbDown, int rgbUp) {
        Color down = new Color(rgbDown);
        Color up = new Color(rgbUp);
        return (new Color(pinLight(down.getRed(), up.getRed()),
                pinLight(down.getGreen(), up.getGreen()),
                pinLight(down.getBlue(), up.getBlue()))).getRGB();
    }

    private int pinLight(int rgbdown, int rgbup) {
        return max(0, max(2 * rgbup - 255, min(rgbdown, 2 * rgbup)));
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

    public static BufferedImage generate(BufferedImage originDown, BufferedImage originUp) {
        return BasicLayerPixelCal.pixelCal(originDown, originUp, new LayerSuperPinLight());
    }
}
