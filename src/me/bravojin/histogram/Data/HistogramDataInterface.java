package me.bravojin.histogram.Data;

/**
 * Created by tyrionlanister on 15-5-28.
 */
public interface HistogramDataInterface {
    public int getBits();
    public void setImgWidth(int imgWidth);
    public int getImgWidth();
    public void setImgHeight(int imgHeight);
    public int getImgHeight();
    public HistogramDataType getDataType();
}
