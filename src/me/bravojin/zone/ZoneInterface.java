package me.bravojin.zone;

import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-5-25.
 */
public interface ZoneInterface {
    public ZoneType getZoneType();
    BufferedImage filter(BufferedImage originImg);
}
