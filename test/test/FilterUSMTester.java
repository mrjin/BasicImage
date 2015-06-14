package test;

import me.bravojin.filter.linear.FilterUSM;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by tyrionlanister on 15-6-14.
 */
public class FilterUSMTester {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        BufferedImage testImg = null;
        try {
            testImg = ImageIO.read(new FileInputStream("/home/tyrionlanister/图片/Java/test10.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long readImgTime = System.currentTimeMillis();
        FilterUSM filterUSM = new FilterUSM();
        BufferedImage resultImg = filterUSM.setAlpha(0.5).setSigma(5).generate(testImg);

        long generateTime = System.currentTimeMillis();
        try {
            ImageIO.write(resultImg, "png", new FileOutputStream(
                    "/home/tyrionlanister/图片/Java/Filter/USM_10_5_0.5_.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long finalTime = System.currentTimeMillis();
        System.out.println(readImgTime - startTime);
        System.out.println(generateTime - readImgTime);
        System.out.println(finalTime - generateTime);
    }
}
