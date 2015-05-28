package me.bravojin.histogram.createHistogram.Generate;

import me.bravojin.histogram.createHistogram.Data.HistogramDataInterface;
import me.bravojin.histogram.createHistogram.Data.HistogramDataLuma;
import me.bravojin.histogram.createHistogram.Data.HistogramDataRGB;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-5-28.
 */
public class HistogramGenerate {
    public static HistogramDataInterface generateHistogramDataRGB(int bits, BufferedImage img) {
        HistogramDataRGB resultData = new HistogramDataRGB(bits, img.getWidth(), img.getHeight());
        int [] R = new int [(int)Math.pow(2, bits)];
        int [] G = new int [(int)Math.pow(2, bits)];
        int [] B = new int [(int)Math.pow(2, bits)];
        int imgHeight = img.getHeight();
        int imgWidth = img.getWidth();
        Color tempColor;
        for(int y = 0 ; y < imgHeight ; y++) {
            for(int x = 0 ; x < imgWidth ; x++) {
                tempColor = new Color(img.getRGB(x, y));
                R[tempColor.getRed()] += 1;
                G[tempColor.getGreen()] += 1;
                B[tempColor.getBlue()] += 1;
            }
        }
        resultData.set(R, G, B);
        return resultData;
    }

    public static HistogramDataInterface generateHistogramDataLuma(int bits, BufferedImage img) {
        HistogramDataLuma resultData = new HistogramDataLuma(bits, img.getWidth(), img.getHeight());
        int [] luma = new int [(int)Math.pow(2, bits)];
        int imgHeight = img.getHeight();
        int imgWidth = img.getWidth();
        Color tempColor;
        for(int y = 0 ; y < imgHeight ; y++) {
            for(int x = 0 ; x < imgWidth ; x++) {
                tempColor = new Color(img.getRGB(x, y));
                int range = (tempColor.getRed()*30 + tempColor.getGreen()*59 +
                        tempColor.getBlue()*11)/100;
                luma[range] += 1;
            }
        }
        resultData.setLuma(luma);
        return resultData;
    }
}
