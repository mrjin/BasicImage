package me.bravojin.layer.gradient.gradientType;

import me.bravojin.pixelPosition.PixelPositionInterface;

/**
 * Created by tyrionlanister on 15-5-25.
 */
public class GradientTypeLinear implements GradientTypeInterface {
    private PixelPositionInterface start;
    private PixelPositionInterface end;

    public void setStart(PixelPositionInterface start) {
        this.start = start;
    }

    public void setEnd(PixelPositionInterface end) {
        this.end = end;
    }

    public GradientTypeEnumerator getGradientType() {
        return GradientTypeEnumerator.LinearGradient;
    }
}
