package me.bravojin.layer.gradient.transparentGradient;

import me.bravojin.layer.api.GenerateBufferedImage;
import me.bravojin.layer.gradient.GradientInterface;
import me.bravojin.layer.gradient.gradientType.GradientModeEnumerator;
import me.bravojin.layer.gradient.gradientType.GradientTypeEnumerator;
import me.bravojin.layer.api.Zone;
import me.bravojin.pixelPosition.PixelPosition;
import me.bravojin.pixelPosition.PixelPositionInterface;
import me.bravojin.util.EculidDistance;
import me.bravojin.zone.ZoneInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-5-26.
 */
public class TransparentGradientLinear implements GradientInterface,Zone,GenerateBufferedImage {
    private String number;
    private ZoneInterface zone;
    private PixelPositionInterface startPixel;
    private PixelPositionInterface endPixel;
    private TransparentGradientDirection directionConfig;
    private double level;


    public TransparentGradientLinear() {

    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setZone(ZoneInterface zone) {
        this.zone = zone;
    }

    public void setStartPixel(PixelPositionInterface start) {
        this.startPixel = start;
    }

    public void setEndPixel(PixelPositionInterface end) {
        this.endPixel = end;
    }

    public void setDirectionConfig(TransparentGradientDirection direction) {
        this.directionConfig = direction;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public String getNumber() {
        return this.number;
    }

    public GradientModeEnumerator getGradientMode() {
        return GradientModeEnumerator.LinearGradient;
    }

    public GradientTypeEnumerator getGradientType() {
        return GradientTypeEnumerator.transparentGradient;
    }

    public ZoneInterface getZone() {
        return this.zone;
    }

    public PixelPositionInterface getStartPixel() {
        return this.startPixel;
    }

    public PixelPositionInterface getEndPixel() {
        return this.endPixel;
    }

    public TransparentGradientDirection getDirectionConfig() {
        return this.directionConfig;
    }

    public double getLevel() {
        return this.level;
    }

    public Color colorCalculate(Color color, int x, double inc, boolean toTransparent) {
        if(toTransparent) {
            return new Color(color.getRed(),color.getGreen(),color.getBlue(),
                    (int)(color.getAlpha() - x*inc) < 0? 0 : (int)(color.getAlpha() - x*inc));
        }
        else {
            return new Color(color.getRed(), color.getGreen(), color.getBlue(),
                    (int)(color.getAlpha() + x*inc) > 255? 255: (int)(color.getAlpha() + x*inc));
        }
    }

    public void pixelCalculate(BufferedImage resultImg, BufferedImage originImg,
                               PixelPositionInterface originPixel,
                               PixelPositionInterface horizentalPixel,
                               PixelPositionInterface verticalPixel,
                               TransparentGradientDirection direction,
                               double verticalInc, double horizentalInc) {
        int imgWidth = originImg.getWidth();
        int imgHeight = originImg.getHeight();

        if(direction == TransparentGradientDirection.FromOpaqueToTransparent) {
            for(int x = 0 ; x < imgWidth; x++) {
                for(int y = 0 ; y < imgHeight; y++) {
                    Color rgb = new Color(originImg.getRGB(x,y));
                    if(horizentalPixel.getX() > originPixel.getX()) {
                        rgb = colorCalculate(rgb, x, horizentalInc, true);
                    }
                    else {
                        rgb = colorCalculate(rgb, imgWidth - 1 - x, horizentalInc, true);
                    }
                    if(verticalPixel.getY() > originPixel.getY()) {
                        rgb = colorCalculate(rgb, y, verticalInc, true);
                    }
                    else {
                        rgb = colorCalculate(rgb, imgHeight - 1 - y, verticalInc, true);
                    }
                    resultImg.setRGB(x, y, rgb.getRGB());
                }
            }
        }
        else if(direction == TransparentGradientDirection.FromTransparentToOpaque) {
            for(int x = 0; x < imgWidth; x++) {
                for(int y = 0; y < imgHeight; y++) {
                    Color rgb = new Color(originImg.getRGB(x,y));
                    if(horizentalPixel.getX() > originPixel.getX()) {
                        rgb = colorCalculate(rgb, x, horizentalInc, false);
                    }
                    else {
                        rgb = colorCalculate(rgb, imgWidth - 1 - x, horizentalInc, false);
                    }
                    if(verticalPixel.getY() > originPixel.getY()) {
                        rgb = colorCalculate(rgb, y, verticalInc, false);
                    }
                    else {
                        rgb = colorCalculate(rgb, imgHeight - 1 - y, verticalInc, false);
                    }
                    resultImg.setRGB(x, y, rgb.getRGB());
                }
            }
        }
    }

    public BufferedImage imageCalculate(BufferedImage originImg) {
        int imgWidth = originImg.getWidth();
        int imgHeight = originImg.getHeight();

        PixelPositionInterface originPixel = new PixelPosition(
                this.startPixel.getX(), this.startPixel.getY());
        PixelPositionInterface verticalPixel = new PixelPosition(
                this.startPixel.getX(), this.endPixel.getY());
        PixelPositionInterface horizentalPixel = new PixelPosition(
                this.endPixel.getX(), this.startPixel.getY());

        double horizentalInc = 255/Math.pow(EculidDistance.distance(new PixelPosition(0, 0), new PixelPosition(imgWidth, imgHeight)),2)
                * Math.abs(horizentalPixel.getX() - originPixel.getX()) * this.level;
        double verticalInc = 255/Math.pow(EculidDistance.distance(new PixelPosition(0, 0), new PixelPosition(imgWidth, imgHeight)),2)
                * Math.abs(verticalPixel.getY() - originPixel.getY()) * this.level;

        BufferedImage resultImg = new BufferedImage(imgWidth, imgHeight,BufferedImage.TYPE_4BYTE_ABGR);

        pixelCalculate(resultImg, originImg,
                originPixel, horizentalPixel, verticalPixel,
                this.directionConfig, verticalInc, horizentalInc);

        return resultImg;
    }

    public BufferedImage generate(BufferedImage originImg) {
        return this.zone.filter(imageCalculate(originImg));
    }
}
