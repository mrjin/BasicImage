package me.bravojin.histogram.Data;

/**
 * Created by tyrionlanister on 15-5-28.
 */
public class HistogramDataLuma implements HistogramDataInterface{
    private int [] Luma;
    private int imgWidth;
    private int imgHeight;
    private int bits;

    public HistogramDataLuma(int bits) {
        this.bits = bits;
        this.Luma = new int [(int)Math.pow(2, bits)];
    }

    public HistogramDataLuma(int bits, int imgWidth, int imgHeight) {
        this.bits = bits;
        this.imgHeight = imgHeight;
        this.imgWidth = imgWidth;
    }

    public void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }

    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    public void setLuma(int [] luma) {
        int length = (int)Math.pow(2, bits);
        for(int i = 0 ; i < length ; i++) {
            this.Luma[i] = luma[i];
        }
    }

    public void setLumaFrequent(int frequency, int luma) {
        int length = (int)Math.pow(2, bits);
        if(frequency < length && frequency >= 0) {
            this.Luma[frequency] = luma;
        }
    }

    public int getBits() {
        return this.bits;
    }

    public int getImgWidth() {
        return this.imgWidth;
    }

    public int getImgHeight() {
        return this.imgHeight;
    }

    public HistogramDataType getDataType() {
        return HistogramDataType.Luma;
    }

    public int getLumaFrequent(int frequency) {
        int length = (int)Math.pow(2, bits);
        if(frequency < length && frequency >= 0) {
            return this.Luma[frequency];
        }
        else {
            return -1;
        }
    }
}
