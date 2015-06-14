package me.bravojin.LayerContent.LayerSuper;

import java.awt.image.BufferedImage;
import java.awt.*;
/**
 * Created by tyrionlanister on 15-6-14.
 */
public class LayerSuperSubtract implements LayerPixelCalInterface{
    public int RGBPixel(int rgbDown, int rgbUp) {
        Color down = new Color(rgbDown);
        Color up = new Color(rgbUp);
        return (new Color(subtract(down.getRed(), up.getRed()),
                        subtract(down.getGreen(), up.getGreen()),
                        subtract(down.getBlue(), up.getBlue()))).getRGB();
    }

    private int subtract(int rgbdown, int rgbup) {
        if(rgbup - rgbdown < 0) {
            return 255;
        }
        else {
            return  rgbup - rgbdown;
        }
    }

    public static BufferedImage generate(BufferedImage originDown, BufferedImage originUp) {
        return BasicLayerPixelCal.pixelCal(originDown, originUp, new LayerSuperSubtract());
    }
}
