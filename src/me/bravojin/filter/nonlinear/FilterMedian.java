package me.bravojin.filter.linear;

import me.bravojin.filter.FilterInterface;
import me.bravojin.filter.NonLinearFilterCal;
import me.bravojin.filter.type.FilterType;
import me.bravojin.util.Convolution2D;
import me.bravojin.zone.ZoneInterface;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.Arrays;
/**
 * Created by tyrionlanister on 15-6-2.
 */
public class FilterMedian implements FilterInterface, NonLinearFilterCal{
    private int width;
    private int height;
    private ZoneInterface zone;

    public FilterMedian(int width, int height, ZoneInterface zone) {
        this.zone = zone;
        this.width = width;
        this.height = height;
    }

    public FilterMedian(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public FilterMedian(ZoneInterface zone) {
        this.width = 3;
        this.height = 3;
        this.zone = zone;
    }

    public FilterMedian() {
        this.width = 3;
        this.height = 3;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public ZoneInterface getZone() {
        return this.zone;
    }

    public FilterMedian setWidth(int width) {
        this.width = width;
        return this;
    }

    public FilterMedian setHeight(int height) {
        this.height = height;
        return this;
    }

    public FilterMedian setZone(ZoneInterface zone) {
        this.zone = zone;
        return this;
    }

    public int getCenterValue(int [][] rgb) {
        int [] R = new int [this.width*this.height];
        int [] G = new int [this.width*this.height];
        int [] B = new int [this.width*this.height];
        int inc = 0;
        for(int i = 0 ; i < this.height ; i++) {
            for(int j = 0 ; j < this.width ; j++) {
                Color color = new Color(rgb[i][j]);
                R[inc] = color.getRed();
                G[inc] = color.getGreen();
                B[inc] = color.getBlue();
                inc++;
            }
        }
        int center = (this.width*this.height)/2;
        Arrays.sort(R);
        Arrays.sort(G);
        Arrays.sort(B);
        return (new Color(R[center], G[center], B[center])).getRGB();
    }

    public FilterType getFilterType(){
        return FilterType.Median;
    }

    public BufferedImage generate(BufferedImage originImg) {
        BufferedImage resultImg = Convolution2D.Convolution(originImg, this,
                                                            this.width/2, this.height/2, new Color(255, 255, 255));
        if(this.zone == null) {
            return resultImg;
        }
        else {
            return zone.filter(resultImg);
        }
    }
}
