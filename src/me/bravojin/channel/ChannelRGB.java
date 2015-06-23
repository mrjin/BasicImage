package me.bravojin.channel;

import java.awt.image.BufferedImage;

/**
 * 产生和保存4个通道的算法对应的类，输入参数为原始图片，产生对应的通道。
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
