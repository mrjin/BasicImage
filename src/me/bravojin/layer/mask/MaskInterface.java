package me.bravojin.layer.mask;

import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-5-25.
 */
abstract public interface MaskInterface {
    public BufferedImage generate(BufferedImage originImg);
}
