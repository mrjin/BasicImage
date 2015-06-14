package me.bravojin.filter.linear;

import me.bravojin.filter.FilterInterface;
import me.bravojin.filter.type.FilterType;
import me.bravojin.layer.api.Zone;
import me.bravojin.util.Convolution;
import me.bravojin.zone.ZoneInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-2.
 */
public class FilterDifferentCurve implements FilterInterface{
    private double [] kernel;
    private int len;
    private int center;
    private ZoneInterface zone;

    private void setKernel() {
        for(int i = 0; i < len; i++) {
            if(i < center) {
                this.kernel[i] = -1.0 / (len - 1);
            }
            else if(i > center) {
                this.kernel[i] = 1.0/(len - 1);
            }
            else {
                this.kernel[i] = 0;
            }
        }
    }

    public FilterDifferentCurve(){
        this.len = 3;
        this.center = 1;
        this.kernel = new double [this.len];
        this.setKernel();
    }

    public FilterDifferentCurve(int len){
        this.len = len;
        this.center = len/2;
        this.kernel = new double [this.len];
        this.setKernel();
    }
    
    public FilterDifferentCurve(ZoneInterface zone) {
        this.zone = zone;
        this.len = 3;
        this.center = 1;
        this.kernel = new double [this.len];
        this.setKernel();
    }

    public FilterDifferentCurve(int len, ZoneInterface zone) {
        this.zone = zone;
        this.len = len;
        this.center = len/2;
        this.kernel = new double [this.len];
        this.setKernel();
    }

    public FilterDifferentCurve setZone(ZoneInterface zone) {
        this.zone = zone;
        return this;
    }

    public FilterDifferentCurve setLen(int len) {
        this.len = len;
        this.setKernel();
        return this;
    }

    public FilterType getFilterType() {
        return FilterType.DifferentCurve;
    }

    public double [] getKernel(){
        return this.kernel;
    }

    public int getLen() {
        return this.len;
    }

    public int getCenter() {
        return this.center;
    }

    public BufferedImage generate(BufferedImage originImg) {
        BufferedImage resultImg = Convolution.verticalConvolution(
                Convolution.horizontalConvolution(originImg, kernel,1.0, center, new Color(255, 255, 255)),
                kernel,1.0, center, new Color(255, 255, 255));
        if(this.zone == null) {
            return resultImg;
        }
        else {
            return this.zone.filter(resultImg);
        }
    }

}
