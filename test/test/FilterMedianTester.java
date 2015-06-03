package test;

import me.bravojin.filter.linear.FilterMedian;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by tyrionlanister on 15-6-2.
 */
public class FilterMedianTester {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        BufferedImage testImg = null;
        try {
            testImg = ImageIO.read(new FileInputStream("/home/tyrionlanister/图片/Java/test.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long readImgTime = System.currentTimeMillis();
        FilterMedian filterMedian = new FilterMedian(3,3);
        BufferedImage resultImg = filterMedian.generate(testImg);

        long generateTime = System.currentTimeMillis();
        try {
            ImageIO.write(resultImg, "png", new FileOutputStream(
                    "/home/tyrionlanister/图片/Java/Filter/Median_3_3_.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long finalTime = System.currentTimeMillis();
        System.out.println(readImgTime - startTime);
        System.out.println(generateTime - readImgTime);
        System.out.println(finalTime - generateTime);
    }
}
