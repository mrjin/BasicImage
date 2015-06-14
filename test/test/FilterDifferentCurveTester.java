package test;

import me.bravojin.filter.linear.FilterDifferentCurve;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by tyrionlanister on 15-6-2.
 */
public class FilterDifferentCurveTester {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        BufferedImage testImg = null;
        try {
            testImg = ImageIO.read(new FileInputStream("/home/tyrionlanister/图片/Java/test4.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long readImgTime = System.currentTimeMillis();
        FilterDifferentCurve filterDifferentCurve = new FilterDifferentCurve(3);
        BufferedImage resultImg = filterDifferentCurve.generate(testImg);
       // System.out.println(filterDifferentCurve.getKernel()[0]);
        long generateTime = System.currentTimeMillis();
        try {
            ImageIO.write(resultImg, "png", new FileOutputStream(
                    "/home/tyrionlanister/图片/Java/Filter/DifferentCurve_4_.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long finalTime = System.currentTimeMillis();
        System.out.println(readImgTime - startTime);
        System.out.println(generateTime - readImgTime);
        System.out.println(finalTime - generateTime);
    }
}
