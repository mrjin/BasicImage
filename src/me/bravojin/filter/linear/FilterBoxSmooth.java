package me.bravojin.filter.linear;

import me.bravojin.filter.type.FilterType;
import me.bravojin.util.Convolution;

import java.awt.*;
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
            this.widthCov[i] = 1;
            this.heightCov[i] = 1;
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
        for(int i = 0 ; i < kernelHeightWidth ; i++){
            this.heightCov[i] = 1;
        }
        for(int j = 0 ; j < kernelWidthLength ; j++) {
            this.widthCov[j] = 1;
        }
//        for(int i = 0 ; i < kernelWidthLength ; i++){
//            for(int j = 0 ; j < kernelHeightWidth ; j++) {
//                this.paramMatrix[i][j] = 1;
//                this.sum = kernelWidthLength * kernelHeightWidth;
//            }
//        }
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
        BufferedImage resultImg = Convolution.verticalConvolution(
                Convolution.horizontalConvolution(originImg, this.widthCov, this.widthCov.length/2,
                        new Color(255, 255, 255)), this.heightCov,
                        this.heightCov.length/2, new Color(255, 255, 255));
        if(this.zone == null) {
            return resultImg;
        }
        else {
            return this.zone.filter(resultImg);
        }
    }

    public String toString(){
        return this.widthCov.length + ":" + this.heightCov.length;
    }
}
