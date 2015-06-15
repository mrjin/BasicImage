package me.bravojin.LayerContent.LayerSuper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-14.
 */
public class LayerSuperVividLight implements LayerPixelCalInterface{
    public int RGBPixel(int rgbDown, int rgbUp) {
        Color down = new Color(rgbDown);
        Color up = new Color(rgbUp);
        return (new Color(vividLight(down.getRed(), up.getRed()),
                vividLight(down.getGreen(), up.getGreen()),
                vividLight(down.getBlue(), up.getBlue()))).getRGB();
    }

    private int vividLight(int rgbdown, int rgbup) {
        return rgbdown < 128 ?
                (
                        rgbdown == 0 ? 2 * rgbdown : max(0, (255 - ((255 - rgbup) << 8 ) / (2 * rgbdown)))
                ) :
                (
                        (2 * (rgbdown - 128)) == 255 ? (2 * (rgbdown - 128)) : min(255, ((rgbup << 8 ) / (255 - (2 * (rgbdown - 128)) )))
                ) ;
    }

    private int min(int a, int b){
        return a < b? a : b;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    public static BufferedImage generate(BufferedImage originDown, BufferedImage originUp) {
        return BasicLayerPixelCal.pixelCal(originDown, originUp, new LayerSuperVividLight());
    }
}
