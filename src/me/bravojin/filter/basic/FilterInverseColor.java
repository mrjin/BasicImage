package me.bravojin.filter.basic;

import me.bravojin.filter.FilterInterface;
import me.bravojin.filter.type.FilterType;
import me.bravojin.zone.ZoneInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-5-29.
 */
public class FilterInverseColor implements FilterInterface{
    private ZoneInterface zone;

    public FilterInverseColor() {

    }

    public FilterInverseColor(ZoneInterface zone) {
        this.zone = zone;
    }

    public FilterInverseColor setZone(ZoneInterface zone) {
        this.zone = zone;
        return this;
    }

    public ZoneInterface getZone(){
        return this.zone;
    }

    public FilterType getFilterType() {
        return FilterType.InverseColor;
    }

    public BufferedImage generate(BufferedImage originImg) {
        BufferedImage resultImg = new BufferedImage(originImg.getWidth(), originImg.getHeight(),
                BufferedImage.TYPE_4BYTE_ABGR);
        int imgHeight = resultImg.getHeight();
        int imgWidth = resultImg.getWidth();
        for(int y = 0 ; y < imgHeight ; y++) {
            for(int x = 0 ; x < imgWidth ; x++) {
                Color color = new Color(originImg.getRGB(x, y));
                resultImg.setRGB(x, y, (new Color(255 - color.getRed(),
                        255 - color.getGreen(), 255 - color.getBlue())).getRGB());
            }
        }
        if(zone == null) {
            return resultImg;
        }
        else {
            return zone.filter(resultImg);
        }
    }

}
