package me.bravojin.LayerContent.LayerSuper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-14.
 */
public class LayerSuperSoftLight implements LayerPixelCalInterface {
    public int RGBPixel(int rgbDown, int rgbUp) {
        Color down = new Color(rgbDown);
        Color up = new Color(rgbUp);
        return (new Color(softLight(down.getRed(), up.getRed()),
                softLight(down.getGreen(), up.getGreen()),
                softLight(down.getBlue(), up.getBlue()))).getRGB();
    }

    private int softLight(int rgbdown, int rgbup) {
        return rgbdown < 128 ?
                (2 * (( rgbup >> 1) + 64)) * (rgbdown / 255) :
                (255 - ( 2 * (255 - ( (rgbup >> 1) + 64 ) )  *  ( 255 - rgbdown ) / 255 ));
    }

    public static BufferedImage generate(BufferedImage originDown, BufferedImage originUp) {
        return BasicLayerPixelCal.pixelCal(originDown, originUp, new LayerSuperSoftLight());
    }
}
