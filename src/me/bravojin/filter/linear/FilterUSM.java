package me.bravojin.filter.linear;

import me.bravojin.filter.FilterInterface;
import me.bravojin.filter.type.FilterType;
import me.bravojin.zone.ZoneInterface;

import java.awt.image.BufferedImage;
import java.awt.*;

/**
 * Created by tyrionlanister on 15-6-14.
 */
public class FilterUSM implements FilterInterface {
    private ZoneInterface zoneInterface;
    private double alpha;
    private double sigma;
    private double threshold;

    public FilterUSM(){
        this.threshold = 0;
    }

    public FilterUSM setAlpha(double alpha) {
        this.alpha = alpha;
        return this;
    }

    public FilterUSM setSigma(double sigma) {
        this.sigma = sigma;
        return this;
    }

    public FilterUSM setThreshold(double threshold) {
        this.threshold = threshold;
        return this;
    }

    public FilterUSM setZoneInterface(ZoneInterface zoneInterface) {
        this.zoneInterface = zoneInterface;
        return this;
    }

    public BufferedImage generate(BufferedImage originImg) {
        BufferedImage resultImg = new BufferedImage(originImg.getWidth(), originImg.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        BufferedImage guassImg = (new FilterGuassSmooth(sigma)).generate(originImg);
        int width = originImg.getWidth();
        int height = originImg.getHeight();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
//                maskImg.setRGB(x, y, originImg.getRGB(x, y) - guassImg.getRGB(x, y));
                Color color = new Color(originImg.getRGB(x, y));
                Color colorGuass = new Color(guassImg.getRGB(x, y));
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                if(Math.abs(color.getRed() - colorGuass.getRed()) > threshold) {
                    r += alpha*(color.getRed() - colorGuass.getRed());
                }
                if(Math.abs(color.getGreen() - colorGuass.getGreen()) > threshold) {
                    g += alpha*(color.getGreen() - colorGuass.getGreen());
                }
                if(Math.abs(color.getBlue() - colorGuass.getBlue()) > threshold) {
                    b += alpha*(color.getBlue() - colorGuass.getBlue());
                }
                resultImg.setRGB(x, y, (new Color(r,g,b)).getRGB());
            }
        }
        if(zoneInterface == null) {
            return resultImg;
        }
        else {
            return zoneInterface.filter(resultImg);
        }
    }
    public FilterType getFilterType() {
        return FilterType.USM;
    }
}
