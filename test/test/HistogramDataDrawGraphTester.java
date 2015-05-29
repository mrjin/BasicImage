package test;

import me.bravojin.histogram.createHistogram.Data.HistogramDataLuma;
import me.bravojin.histogram.createHistogram.Data.HistogramDataRGB;
import me.bravojin.histogram.createHistogram.DrawGraph.HistogramDataDrawGraph;
import me.bravojin.histogram.createHistogram.Generate.HistogramGenerate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by tyrionlanister on 15-5-28.
 */
public class HistogramDataDrawGraphTester {
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
//        HistogramDataLuma histogramDataLuma = (HistogramDataLuma) HistogramGenerate.generateHistogramDataLuma(8,testImg);
        HistogramDataRGB histogramDataRGB = (HistogramDataRGB) HistogramGenerate.generateHistogramDataRGB(8, testImg);
        BufferedImage resultImg = HistogramDataDrawGraph.drawRGBWithShade(histogramDataRGB.getR(),
                histogramDataRGB.getG(),histogramDataRGB.getB(),256,new Color(255,255,255),
                2,2,2);

//        BufferedImage resultImg = HistogramDataDrawGraph.draw(histogramDataLuma.getLuma(),256, new Color(0,0,0)
//        ,new Color(255, 255, 255),2);
        long generateTime = System.currentTimeMillis();
        try {
            ImageIO.write(resultImg, "png", new FileOutputStream(
                    "/home/tyrionlanister/图片/Java/Histogram/Histogram_7_RGB_testForFilter_3_256_256.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        for(int i = 0 ; i < 256 ; i++) {
//            System.out.println(i + " : " + histogramDataRGB.getBFrequent(i));
//        }
//        System.out.println(Array.secondInt(histogramDataRGB.getB()));
        long finalTime = System.currentTimeMillis();
        System.out.println(readImgTime - startTime);
        System.out.println(generateTime - readImgTime);
        System.out.println(finalTime - generateTime);
    }
}
