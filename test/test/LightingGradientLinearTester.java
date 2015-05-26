package test;

import me.bravojin.layer.gradient.lightingGradient.LightingGradientLinear;
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
public class LightingGradientLinearTester {
    public static void main(String [] args) {
        long startTime = System.currentTimeMillis();
        LightingGradientLinear test = new LightingGradientLinear();
        test.setDirectionConfig(LightingGradientDirection.FromLightToDark);
        test.setStartPixel(new PixelPosition(0, 500));
        test.setEndPixel(new PixelPosition(500, 0));
        test.setZone(new ZoneRectangle(new PixelPosition(0,0), new PixelPosition(600, 600)));
        BufferedImage testImg = null;
        try {
            testImg = ImageIO.read(new FileInputStream("/home/tyrionlanister/图片/Java/test.jpg"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        long readImgTime = System.currentTimeMillis();
        test.setLevel(2.0);
        BufferedImage resultImg = test.generate(testImg);
        long generateTime = System.currentTimeMillis();
        try {
            ImageIO.write(resultImg, "png", new FileOutputStream(
                    "/home/tyrionlanister/图片/Java/Gradient/Lighting/gradient_lighting_linear_0_500_500_0_1_2.png"));
        }catch(Exception e) {
            e.printStackTrace();
        }
        long finalTime = System.currentTimeMillis();
        System.out.println(readImgTime - startTime);
        System.out.println(generateTime - readImgTime);
        System.out.println(finalTime - generateTime);
//        System.out.println(testImg.getRGB(100,100));
//        System.out.println(test.colorCalculate(testImg.getRGB(100,100),1,1,1,false));
    }
}
