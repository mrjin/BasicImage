package me.bravojin.LayerContent.LayerSuper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-14.
 */
public class LayerSuperLighten implements LayerPixelCalInterface{
    public int RGBPixel(int rgbDown, int rgbUp) {
        Color down = new Color(rgbDown);
        Color up = new Color(rgbUp);
        return (new Color(lighten(down.getRed(), up.getRed()),
                lighten(down.getGreen(), up.getGreen()),
                lighten(down.getBlue(), up.getBlue()))).getRGB();
    }

    private int lighten(int rgbdown, int rgbup) {
        return rgbdown > rgbup ? rgbdown : rgbup;
    }

    public static BufferedImage generate(BufferedImage originDown, BufferedImage originUp) {
        return BasicLayerPixelCal.pixelCal(originDown, originUp, new LayerSuperLighten());
    }
}
