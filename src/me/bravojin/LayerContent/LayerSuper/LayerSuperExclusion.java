package me.bravojin.LayerContent.LayerSuper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-15.
 */
public class LayerSuperExclusion implements LayerPixelCalInterface {
    public int RGBPixel(int rgbDown, int rgbUp) {
        Color down = new Color(rgbDown);
        Color up = new Color(rgbUp);
        return (new Color(exclusion(down.getRed(), up.getRed()),
                exclusion(down.getGreen(), up.getGreen()),
                exclusion(down.getBlue(), up.getBlue()))).getRGB();
    }

    private int exclusion(int rgbdown, int rgbup) {
        return rgbup + rgbdown - 2 * rgbup * rgbdown / 255;
    }

    public static BufferedImage generate(BufferedImage originDown, BufferedImage originUp) {
        return BasicLayerPixelCal.pixelCal(originDown, originUp, new LayerSuperExclusion());
    }
}
