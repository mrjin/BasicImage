package me.bravojin.channel;

import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-13.
 */
public class ChannelRGB {
    private BufferedImage RImg;
    private BufferedImage GImg;
    private BufferedImage BImg;
    private BufferedImage lumaImg;

    public ChannelRGB generate(BufferedImage originImg) {

        return this;
    }

    public BufferedImage getRImg() {
        return this.RImg;
    }

    public BufferedImage getGImg() {
        return this.GImg;
    }

    public BufferedImage getBImg() {
        return this.BImg;
    }

    public BufferedImage getLumaImg() {
        return this.lumaImg;
    }
}
