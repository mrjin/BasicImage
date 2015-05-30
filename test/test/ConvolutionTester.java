package test;

import me.bravojin.util.Convolution;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Kernel;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by tyrionlanister on 15-5-29.
 */
public class ConvolutionTester {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        BufferedImage testImg = null;
        try {
            testImg = ImageIO.read(new FileInputStream("/home/tyrionlanister/图片/Java/test7.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long readImgTime = System.currentTimeMillis();
        int [] A = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int [] B = {1,1,1,1,1,1,1,1,1,1,1};
        int [] C = {1,1,1,1,1};
        double sigma = 1.8;
        double sigma2 = sigma * sigma;
        int center = (int) (3.0*sigma);
        double [] kernel = new double [2*center+1];
        for(int i = 0 ; i < kernel.length ; i++) {
            double r = center - 1;
            kernel[i] = Math.exp(-0.5*(r*r/sigma2));
        }
        BufferedImage resultImg = Convolution.verticalConvolution(
                Convolution.horizontalConvolution(testImg, kernel, center, new Color(255, 255, 255)),
                kernel, center, new Color(255, 255, 255));

        long generateTime = System.currentTimeMillis();
        try {
            ImageIO.write(resultImg, "png", new FileOutputStream(
                    "/home/tyrionlanister/图片/Java/Filter/Convolution_Guass_5_.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long finalTime = System.currentTimeMillis();
        System.out.println(readImgTime - startTime);
        System.out.println(generateTime - readImgTime);
        System.out.println(finalTime - generateTime);
    }
}
