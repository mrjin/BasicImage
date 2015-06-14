package test;

import me.bravojin.filter.basic.FilterInverseColor;
import me.bravojin.filter.linear.FilterBoxSmooth;
import me.bravojin.filter.linear.FilterGuassSmooth;
import me.bravojin.layer.Layer;
import me.bravojin.layer.gradient.GradientInterface;
import me.bravojin.layer.gradient.lightingGradient.LightingGradientDirection;
import me.bravojin.layer.gradient.lightingGradient.LightingGradientLinear;
import me.bravojin.pixelPosition.PixelPosition;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by tyrionlanister on 15-6-10.
 */
public class LayerTester {
    public static void main(String [] args) {
        long startTime = System.currentTimeMillis();
        BufferedImage testImg = null;
        try {
            testImg = ImageIO.read(new FileInputStream("/home/tyrionlanister/图片/Java/test3.jpg"));
//            testImg = ImageIO.read(new FileInputStream("/home/tyrionlanister/图片/Java/Filter/Convolution_Vertical_3_21_10_.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long readImgTime = System.currentTimeMillis();
        Layer layer = new Layer(0,testImg);
        LightingGradientLinear gradientLinear = new LightingGradientLinear();
        gradientLinear.setStartPixel(new PixelPosition(0,0));
        gradientLinear.setEndPixel(new PixelPosition(500, 500));
        gradientLinear.setDirectionConfig(LightingGradientDirection.FromLightToDark);
        layer.add(gradientLinear);
        layer.testHistories(1);
        long generateTime1 = System.currentTimeMillis();
        try {
            ImageIO.write(layer.getTopLayerImg(), "png", new FileOutputStream(
                    "/home/tyrionlanister/图片/Java/Histogram/1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long finalTime1 = System.currentTimeMillis();
        LightingGradientLinear gradientLinear2 = new LightingGradientLinear();
        gradientLinear2.setStartPixel(new PixelPosition(0,500));
        gradientLinear2.setEndPixel(new PixelPosition(500, 0));
        gradientLinear2.setDirectionConfig(LightingGradientDirection.FromDarkToLight);
        layer.add(gradientLinear2);
        layer.testHistories(2);
        long generateTime2 = System.currentTimeMillis();
        try {
            ImageIO.write(layer.getTopLayerImg(), "png", new FileOutputStream(
                    "/home/tyrionlanister/图片/Java/Histogram/2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long finalTime2 = System.currentTimeMillis();
        layer.add(new FilterBoxSmooth(3,3));
        layer.testHistories(3);
        long generateTime3 = System.currentTimeMillis();
        try {
            ImageIO.write(layer.getTopLayerImg(), "png", new FileOutputStream(
                    "/home/tyrionlanister/图片/Java/Histogram/3.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long finalTime3 = System.currentTimeMillis();
        layer.add(new FilterGuassSmooth(5));
        layer.testHistories(4);
        long generateTime4 = System.currentTimeMillis();
        try {
            ImageIO.write(layer.getTopLayerImg(), "png", new FileOutputStream(
                    "/home/tyrionlanister/图片/Java/Histogram/4.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long finalTime4 = System.currentTimeMillis();
        layer.add(new FilterInverseColor());
        layer.testHistories(5);
        long generateTime5 = System.currentTimeMillis();
        try {
            ImageIO.write(layer.getTopLayerImg(), "png", new FileOutputStream(
                    "/home/tyrionlanister/图片/Java/Histogram/5.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long finalTime5 = System.currentTimeMillis();
        layer.cancelLastAdd();
        layer.testHistories(6);
        long generateTime6 = System.currentTimeMillis();
        System.out.println(readImgTime - startTime);
        System.out.println(generateTime1 - readImgTime);
        System.out.println(finalTime1 - generateTime1);
        System.out.println(generateTime2 - finalTime1);
        System.out.println(finalTime2 - generateTime2);
        System.out.println(generateTime3 - finalTime2);
        System.out.println(finalTime3 - generateTime3);
        System.out.println(generateTime4 - finalTime3);
        System.out.println(finalTime4 - generateTime4);
        System.out.println(generateTime5 - finalTime4);
        System.out.println(finalTime5 - generateTime5);
    }
}
