package me.bravojin.layer.gradient;

import me.bravojin.layer.gradient.gradientType.GradientModeEnumerator;
import me.bravojin.layer.gradient.gradientType.GradientTypeEnumerator;
import me.bravojin.zone.ZoneInterface;

import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-5-25.
 */
abstract public interface GradientInterface {
    public void setNumber(String number);
    public GradientModeEnumerator getGradientMode();
    public GradientTypeEnumerator getGradientType();
    public void setZone(ZoneInterface zone);
    public BufferedImage generate(BufferedImage originImg);
}
