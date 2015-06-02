package me.bravojin.filter.linear;

import me.bravojin.filter.FilterInterface;
import me.bravojin.filter.type.FilterType;
import me.bravojin.util.Convolution;
import me.bravojin.zone.ZoneInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-2.
 */
public class FilterGuassSmooth implements FilterInterface {
    private double [] kernel;
    private int defaultParam;
    private double sigma;
    private int center;
    private ZoneInterface zone;

    public FilterGuassSmooth(int sigma){
        this.sigma = sigma;
        this.defaultParam = 3;
        this.update();
    }

    public FilterGuassSmooth(int sigma, int defaultParam){
        this.sigma = sigma;
        this.defaultParam = defaultParam;
        this.update();
    }

    public FilterGuassSmooth(int sigma, ZoneInterface zone) {
        this.zone = zone;
        this.sigma = sigma;
        this.defaultParam = 3;
        this.update();
    }

    public FilterGuassSmooth(int sigma, int defaultParam, ZoneInterface zone) {
        this.zone = zone;
        this.sigma = sigma;
        this.defaultParam = defaultParam;
        this.update();
    }

    public FilterType getFilterType() {
        return FilterType.GuassSmooth;
    }

    public double getSigma() {
        return this.sigma;
    }

    public int getCenter() {
        return this.center;
    }

    public int getDefaultParam() {
        return this.defaultParam;
    }

    public double [] getKernel() {
        return this.kernel;
    }

    public FilterGuassSmooth setDefaultParam(int defaultParam) {
        this.defaultParam = defaultParam;
        this.update();
        return this;
    }

    public FilterGuassSmooth setSigma(double sigma) {
        this.sigma = sigma;
        this.update();
        return this;
    }

    private void update() {
        double sigma2 = sigma*sigma;
        this.center = (int)(defaultParam*sigma);
        this.kernel = new double [2*center+1];
        for(int i = 0 ; i < kernel.length ; i++) {
            double r = center - 1;
            this.kernel[i] = Math.exp(-0.5*(r*r/sigma2));
        }
    }

    public BufferedImage generate(BufferedImage originImg) {
        BufferedImage resultImg = Convolution.verticalConvolution(
                Convolution.horizontalConvolution(originImg, kernel, center, new Color(255, 255, 255)),
                kernel, center, new Color(255, 255, 255));
        if(this.zone == null) {
            return resultImg;
        }
        else {
            return this.zone.filter(resultImg);
        }
    }
}
