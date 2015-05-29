package me.bravojin.filter.basic;

import me.bravojin.filter.FilterInterface;
import me.bravojin.filter.type.FilterType;

import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-5-29.
 */
public class FilterBoxSmooth extends FilterMatrix {
    private int [] widthCov;
    private int [] heightCov;

    public FilterBoxSmooth() {
        super(3, 3);
        this.widthCov = new int [3];
        this.heightCov = new int [3];
        for(int i = 0 ; i < 3 ; i++){
            this.widthCov[i] = 0;
            this.heightCov[i] = 0;
        }
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++) {
                this.paramMatrix[i][j] = 1;
                this.sum = 9;
            }
        }
    }

    public FilterBoxSmooth(int kernelWidthLength, int kernelHeightWidth){
        super(kernelWidthLength, kernelHeightWidth);
        this.widthCov = new int [kernelWidthLength];
        this.heightCov = new int [kernelHeightWidth];
        for(int i = 0 ; i < 3 ; i++){
            this.widthCov[i] = 0;
            this.heightCov[i] = 0;
        }
        for(int i = 0 ; i < kernelWidthLength ; i++){
            for(int j = 0 ; j < kernelHeightWidth ; j++) {
                this.paramMatrix[i][j] = 1;
                this.sum = kernelWidthLength * kernelHeightWidth;
            }
        }
    }

    public int [] getWidthCov() {
        return this.widthCov;
    }

    public int [] getHeightCov() {
        return this.heightCov;
    }

    public FilterType getFilterType() {
        return FilterType.BoxSmooth;
    }

    public BufferedImage generate(BufferedImage originImg) {
        return null;
    }
}
