package me.bravojin.filter.basic;

import me.bravojin.filter.FilterInterface;
import me.bravojin.zone.ZoneInterface;

import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-5-29.
 */
abstract public class FilterMatrix implements FilterInterface {
    protected int [][] paramMatrix;
    protected int sum;
    protected int kernelWidthLength;
    protected int kernelHeightLength;
    protected ZoneInterface zone;

    protected FilterMatrix(int kernelWidthLength, int kernelHeightLength) {
        this.kernelHeightLength = kernelHeightLength;
        this.kernelWidthLength = kernelWidthLength;
        this.paramMatrix = new int [kernelHeightLength][kernelWidthLength];
    }

    public FilterInterface setZone(ZoneInterface zone) {
        this.zone = zone;
        return this;
    }

    public FilterInterface setParamMatrix(int [][] param) {
        for(int x = 0 ; x < this.kernelHeightLength ; x++) {
            for(int y = 0 ; y < this.kernelWidthLength ; y++) {
                this.paramMatrix[x][y] = param[x][y];
            }
        }
        return this;
    }

    public ZoneInterface getZone() {
        return this.zone;
    }

    public int [][] getParamMatrix() {
        return this.paramMatrix;
    }

    abstract public BufferedImage generate(BufferedImage originImg);
}
