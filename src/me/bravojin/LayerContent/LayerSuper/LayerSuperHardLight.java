package me.bravojin.LayerContent.LayerSuper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-14.
 */
public class LayerSuperHardLight implements LayerPixelCalInterface{
    public int RGBPixel(int rgbDown, int rgbUp) {
        Color down = new Color(rgbDown);
        Color up = new Color(rgbUp);
        return (new Color(hardlight(down.getRed(), up.getRed()),
                hardlight(down.getGreen(), up.getGreen()),
                hardlight(down.getBlue(), up.getBlue()))).getRGB();
    }

    private int hardlight(int rgbdown, int rgbup) {
        return (rgbup < 128) ? (2 * rgbdown * rgbup / 255) : (255 - 2 * (255 - rgbup) * (255 - rgbdown) / 255);
    }

    public static BufferedImage generate(BufferedImage originDown, BufferedImage originUp) {
        return BasicLayerPixelCal.pixelCal(originDown, originUp, new LayerSuperHardLight());
    }
}
