package me.bravojin.LayerContent.LayerSuper;

import me.bravojin.util.RGB;

import java.awt.image.BufferedImage;
import java.awt.*;
/**
 * Created by tyrionlanister on 15-6-10.
 */
public class LayerSuperDarken implements LayerPixelCalInterface {
    public int RGBPixel(int rgbDown, int rgbUP) {
        Color colorDown = new Color(rgbDown);
        Color colorUp = new Color(rgbUP);
        return (new Color(RGB.check(colorDown.getRed()>colorUp.getRed()?colorUp.getRed():colorDown.getRed()),
                            RGB.check(colorDown.getGreen()>colorUp.getGreen()?colorUp.getGreen():colorDown.getGreen()),
                            RGB.check(colorDown.getBlue()>colorUp.getBlue()?colorUp.getBlue():colorDown.getBlue()),
                            colorUp.getAlpha())).getRGB();
    }

    public static BufferedImage generate(BufferedImage originDown, BufferedImage originUp) {
        return BasicLayerPixelCal.pixelCal(originDown,originUp,new LayerSuperDarken());
    }
}
