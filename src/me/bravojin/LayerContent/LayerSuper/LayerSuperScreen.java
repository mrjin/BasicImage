package me.bravojin.LayerContent.LayerSuper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-14.
 */
public class LayerSuperScreen implements LayerPixelCalInterface {
    public int RGBPixel(int rgbDown, int rgbUp) {
        Color down = new Color(rgbDown);
        Color up = new Color(rgbUp);
        return (new Color(screen(down.getRed(), up.getRed()),
                screen(down.getGreen(), up.getGreen()),
                screen(down.getBlue(), up.getBlue()))).getRGB();
    }

    private int screen(int rgbdown, int rgbup) {
        return 255 - ( ((255 - rgbdown) * (255 - rgbup)) >> 8);
    }

    public static BufferedImage generate(BufferedImage originDown, BufferedImage originUp) {
        return BasicLayerPixelCal.pixelCal(originDown, originUp, new LayerSuperScreen());
    }
}
