package me.bravojin.LayerContent.LayerSuper;

import me.bravojin.util.RGB;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-10.
 */
public class LayerSuperMultiply implements LayerPixelCalInterface {
    public int RGBPixel(int rgbDown, int rgbUP) {
        Color colorDown = new Color(rgbDown);
        Color colorUp = new Color(rgbUP);
        return (new Color(RGB.check(colorDown.getRed()*colorUp.getRed()/255),
                RGB.check(colorDown.getGreen()*colorUp.getGreen()/255),
                RGB.check(colorDown.getBlue()*colorUp.getBlue()/255),
                colorUp.getAlpha())).getRGB();
    }

    public static BufferedImage generate(BufferedImage originDown, BufferedImage originUp) {
        return BasicLayerPixelCal.pixelCal(originDown, originUp, new LayerSuperMultiply());
    }
}
