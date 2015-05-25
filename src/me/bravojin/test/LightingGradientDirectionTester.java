package me.bravojin.test;

import me.bravojin.layer.gradient.lightingGradient.LightingGradient;
import me.bravojin.layer.gradient.lightingGradient.LightingGradientDirection;
import me.bravojin.pixelPosition.PixelPosition;
import me.bravojin.zone.ZoneRectangle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by tyrionlanister on 15-5-26.
 */
public class LightingGradientDirectionTester {
    public static void main(String [] args) {
        LightingGradient test = new LightingGradient();
        test.setDirectionConfig(LightingGradientDirection.FromLightToDark);
        test.setStartPixel(new PixelPosition(0, 0));
        test.setEndPixel(new PixelPosition(500, 500));
        test.setZone(new ZoneRectangle(new PixelPosition(0,0), new PixelPosition(550, 550)));
        BufferedImage testImg = null;
        try {
            testImg = ImageIO.read(new FileInputStream("/home/tyrionlanister/图片/test.jpg"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        BufferedImage resultImg = test.generate(testImg);
        try {
            ImageIO.write(resultImg, "png", new FileOutputStream("/home/tyrionlanister/图片/result.png"));
        }catch(Exception e) {
            e.printStackTrace();
        }
//        System.out.println(testImg.getRGB(100,100));
//        System.out.println(test.colorCalculate(testImg.getRGB(100,100),1,1,1,false));
    }
}
