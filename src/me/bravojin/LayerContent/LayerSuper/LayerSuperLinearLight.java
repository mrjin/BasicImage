package me.bravojin.LayerContent.LayerSuper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-14.
 */
public class LayerSuperLinearLight implements LayerPixelCalInterface {
    public int RGBPixel(int rgbDown, int rgbUp) {
        Color down = new Color(rgbDown);
        Color up = new Color(rgbUp);
        return (new Color(linearLight(down.getRed(), up.getRed()),
                linearLight(down.getGreen(), up.getGreen()),
                linearLight(down.getBlue(), up.getBlue()))).getRGB();
    }

    private int linearLight(int rgbdown, int rgbup) {
        return min(255, max(0, (rgbdown + 2 * rgbup) - 255));
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

    public static BufferedImage generate(BufferedImage originDown, BufferedImage originUp) {
        return BasicLayerPixelCal.pixelCal(originDown, originUp, new LayerSuperLinearLight());
    }
}
