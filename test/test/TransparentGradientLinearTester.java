package test;

import me.bravojin.layer.gradient.transparentGradient.TransparentGradientDirection;
import me.bravojin.layer.gradient.transparentGradient.TransparentGradientLinear;
import me.bravojin.pixelPosition.PixelPosition;
import me.bravojin.zone.ZoneRectangle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by tyrionlanister on 15-5-26.
 */
public class TransparentGradientLinearTester {
    public static void main(String [] args) {
        long startTime = System.currentTimeMillis();
        TransparentGradientLinear test = new TransparentGradientLinear();
        test.setDirectionConfig(TransparentGradientDirection.FromOpaqueToTransparent);
        test.setStartPixel(new PixelPosition(500, 0));
        test.setEndPixel(new PixelPosition(0, 500));
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
                    "/home/tyrionlanister/图片/Java/Gradient/Transparent/gradient_transparent_light_500_0_0_500_2.png"));
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
