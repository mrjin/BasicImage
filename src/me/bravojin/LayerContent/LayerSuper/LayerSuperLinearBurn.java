package me.bravojin.LayerContent.LayerSuper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-14.
 */
public class LayerSuperLinearBurn implements LayerPixelCalInterface{
    public int RGBPixel(int rgbDown, int rgbUp) {
        Color down = new Color(rgbDown);
        Color up = new Color(rgbUp);
        return (new Color(linearBurn(down.getRed(), up.getRed()),
                linearBurn(down.getGreen(), up.getGreen()),
                linearBurn(down.getBlue(), up.getBlue()))).getRGB();
    }

    private int linearBurn(int rgbdown, int rgbup) {
        return (rgbup + rgbdown < 255) ? 0 : (rgbdown + rgbup - 255);
    }

    public static BufferedImage generate(BufferedImage originDown, BufferedImage originUp) {
        return BasicLayerPixelCal.pixelCal(originDown, originUp, new LayerSuperLinearBurn());
    }
}
