package me.bravojin.LayerContent.LayerSuper;

import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * Created by tyrionlanister on 15-6-14.
 */
public class LayerSuperColorBurn implements LayerPixelCalInterface {
    public int RGBPixel(int rgbDown, int rgbUp) {
        Color down = new Color(rgbDown);
        Color up = new Color(rgbUp);
        return (new Color(colorBurn(down.getRed(), up.getRed()),
                colorBurn(down.getGreen(), up.getGreen()),
                colorBurn(down.getBlue(), up.getBlue()))).getRGB();
    }

    private int colorBurn(int rgbdown, int rgbup) {
        return rgbup == 0 ? rgbup : max(0, (255 - ((255 - rgbdown) << 8 ) / rgbup));
    }

    private int max(int a, int b){
        return a > b ? a : b;
    }

    public static BufferedImage generate(BufferedImage originDown, BufferedImage originUp) {
        return BasicLayerPixelCal.pixelCal(originDown, originUp, new LayerSuperColorBurn());
    }
}
