package me.bravojin.LayerContent.LayerSuper;

import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-10.
 */
public class BasicLayerPixelCal {
    public static BufferedImage pixelCal(BufferedImage originDown, BufferedImage originUp, LayerPixelCalInterface layerPixelCalInterface) {
        int width = originUp.getWidth();
        int height = originUp.getHeight();
        BufferedImage resultImg = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                resultImg.setRGB(x, y, layerPixelCalInterface.RGBPixel(originDown.getRGB(x, y), originUp.getRGB(x, y)));
            }
        }
        return resultImg;
    }
}
