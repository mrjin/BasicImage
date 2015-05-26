package me.bravojin.layer.gradient;

import me.bravojin.layer.gradient.gradientType.GradientModeEnumerator;
import me.bravojin.layer.gradient.gradientType.GradientTypeEnumerator;

/**
 * Created by tyrionlanister on 15-5-25.
 */
abstract public interface GradientInterface {
    public void setNumber(String number);
    public GradientModeEnumerator getGradientMode();
    public GradientTypeEnumerator getGradientType();
}
