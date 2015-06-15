package me.bravojin.LayerContent.LayerSuper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-14.
 */
public class LayerSuperColorDodge implements LayerPixelCalInterface {
    public int RGBPixel(int rgbDown, int rgbUp) {
        Color down = new Color(rgbDown);
        Color up = new Color(rgbUp);
        return (new Color(colorDodge(down.getRed(), up.getRed()),
                colorDodge(down.getGreen(), up.getGreen()),
                colorDodge(down.getBlue(), up.getBlue()))).getRGB();
    }

    private int colorDodge(int rgbdown, int rgbup) {
        return rgbup == 255 ? rgbup : min(255, ((rgbdown << 8 ) / (255 - rgbup)));
    }

    private int min(int a, int b){
        return a < b ? a : b;
    }

    public static BufferedImage generate(BufferedImage originDown, BufferedImage originUp) {
        return BasicLayerPixelCal.pixelCal(originDown, originUp, new LayerSuperColorDodge());
    }
}
