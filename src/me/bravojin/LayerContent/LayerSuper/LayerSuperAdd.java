package me.bravojin.LayerContent.LayerSuper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-14.
 */
public class LayerSuperAdd implements LayerPixelCalInterface{
    public int RGBPixel(int rgbDown, int rgbUp) {
        Color down = new Color(rgbDown);
        Color up = new Color(rgbUp);
        return (new Color(add(down.getRed(), up.getRed()),
                add(down.getGreen(), up.getGreen()),
                add(down.getBlue(), up.getBlue()))).getRGB();
    }

    private int add(int rgbdown, int rgbup) {
        if(rgbup + rgbdown > 255) {
            return 255;
        }
        else {
            return  rgbup + rgbdown;
        }
    }

    public static BufferedImage generate(BufferedImage originDown, BufferedImage originUp) {
        return BasicLayerPixelCal.pixelCal(originDown, originUp, new LayerSuperAdd());
    }
}
