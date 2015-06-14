package test;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.LayerContent.LayerContentKind;
import me.bravojin.layer.Layer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.awt.*;
import java.io.FileOutputStream;

/**
 * Created by tyrionlanister on 15-6-10.
 */
public class LayerContentTester {
    public static void main(String []args) {
        long startTime = System.currentTimeMillis();
        BufferedImage testImg1 = null;
        BufferedImage testImg2 = null;
        try {
            testImg2 = ImageIO.read(new FileInputStream("/home/tyrionlanister/图片/Java/test10.png"));
            testImg1 = ImageIO.read(new FileInputStream("/home/tyrionlanister/图片/Java/test9.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long readImgTime = System.currentTimeMillis();
        LayerContent layerContent = new LayerContent();
        layerContent.add(new Layer(0, testImg2));
        layerContent.add(new Layer(1, testImg1), LayerContentKind.Darken);
        long generateTime = System.currentTimeMillis();
        try {
            ImageIO.write(layerContent.getTop(), "png", new FileOutputStream(
                    "/home/tyrionlanister/图片/Java/LayerContent/Darken_1_.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long finalTime = System.currentTimeMillis();
        layerContent.updateSuperPositionKind(LayerContentKind.Multiply,1);
        try {
            ImageIO.write(layerContent.getTop(), "png", new FileOutputStream(
                    "/home/tyrionlanister/图片/Java/LayerContent/Darken_2_.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(readImgTime - startTime);
        System.out.println(generateTime - readImgTime);
        System.out.println(finalTime - generateTime);
    }
}
