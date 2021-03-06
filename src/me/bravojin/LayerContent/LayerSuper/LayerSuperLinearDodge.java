package me.bravojin.LayerContent.LayerSuper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-14.
 */
public class LayerSuperLinearDodge implements LayerPixelCalInterface {
    public int RGBPixel(int rgbDown, int rgbUp) {
        Color down = new Color(rgbDown);
        Color up = new Color(rgbUp);
        return (new Color(linearDodge(down.getRed(), up.getRed()),
                linearDodge(down.getGreen(), up.getGreen()),
                linearDodge(down.getBlue(), up.getBlue()))).getRGB();
    }

    private int linearDodge(int rgbdown, int rgbup) {
        return min(255, rgbdown + rgbup);
    }

    private int min(int a, int b){
        return a < b? a : b;
    }

    public static BufferedImage generate(BufferedImage originDown, BufferedImage originUp) {
        return BasicLayerPixelCal.pixelCal(originDown, originUp, new LayerSuperLinearDodge());
    }
}
