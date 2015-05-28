package me.bravojin.histogram.createHistogram.Data;

/**
 * Created by tyrionlanister on 15-5-28.
 */
public class HistogramDataRGB implements HistogramDataInterface {
    private int [] R;
    private int [] G;
    private int [] B;
    private int imgHeight;
    private int imgWidth;
    private int bits;

    public HistogramDataRGB(int bits) {
        this.bits = bits;
        int length = (int)Math.pow(2,bits);
        this.R = new int [length];
        this.G = new int [length];
        this.B = new int [length];
    }

    public HistogramDataRGB(int bits, int imgWidth, int imgHeight) {
        this.bits = bits;
        int length = (int)Math.pow(2,bits);
        this.R = new int [length];
        this.G = new int [length];
        this.B = new int [length];
        this.imgHeight = imgHeight;
        this.imgWidth = imgWidth;
    }

    public void set(int [] R, int [] G, int [] B) {
        int length = (int)Math.pow(2, bits);
        for(int i = 0 ; i < length ; i++) {
            this.R[i] = R[i];
            this.G[i] = G[i];
            this.B[i] = B[i];
        }
    }

    public void setR(int [] R) {
        int length = (int)Math.pow(2, bits);
        for(int i = 0 ; i < length ; i++) {
            this.R[i] = R[i];
        }
    }

    public void setG(int [] G) {
        int length = (int)Math.pow(2, bits);
        for(int i = 0 ; i < length ; i++) {
            this.G[i] = G[i];
        }
    }

    public void setB(int [] B) {
        int length = (int)Math.pow(2, bits);
        for(int i = 0 ; i < length ; i++) {
            this.B[i] = B[i];
        }
    }

    public void setRFrequent(int frequency, int number) {
        int length = (int)Math.pow(2, bits);
        if(frequency < length && frequency >= 0) {
            this.R[frequency] = number;
        }
    }

    public void setGFrequent(int frequency, int number) {
        int length = (int)Math.pow(2, bits);
        if(frequency < length && frequency >= 0) {
            this.G[frequency] = number;
        }
    }

    public void setBFrequent(int frequency, int number) {
        int length = (int)Math.pow(2, bits);
        if(frequency < length && frequency >= 0) {
            this.B[frequency] = number;
        }
    }

    public void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }

    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }


    public int [] getR() {
        return this.R;
    }

    public int [] getG() {
        return this.G;
    }

    public int [] getB() {
        return this.B;
    }

    public int getImgHeight() {
        return this.imgHeight;
    }

    public int getImgWidth() {
        return this.imgWidth;
    }

    public int getRFrequent(int frequency) {
        return this.R[frequency];
    }

    public int getGFrequent(int frequency) {
        return this.G[frequency];
    }

    public int getBFrequent(int frequency) {
        return this.B[frequency];
    }

    public int getBits() {
        return this.bits;
    }

    public HistogramDataType getDataType() {
        return HistogramDataType.RGB;
    }
}
