package me.bravojin.filter;

import me.bravojin.filter.type.FilterType;

import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-5-29.
 */
public interface FilterInterface {
    public BufferedImage generate(BufferedImage originImg);
    public FilterType getFilterType();
}
