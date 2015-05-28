package me.bravojin.layer.gradient.lightingGradient;

/**
 * Created by tyrionlanister on 15-5-25.
 */
import me.bravojin.layer.gradient.GradientInterface;
import me.bravojin.layer.gradient.gradientType.GradientTypeEnumerator;
import me.bravojin.layer.api.GenerateBufferedImage;
import me.bravojin.layer.gradient.gradientType.GradientModeEnumerator;
import me.bravojin.layer.api.Zone;
import me.bravojin.pixelPosition.PixelPosition;
import me.bravojin.zone.ZoneInterface;
import me.bravojin.pixelPosition.PixelPositionInterface;
import me.bravojin.util.EculidDistance;

import java.awt.*;
import java.awt.image.BufferedImage;


public class LightingGradientLinear implements GradientInterface,Zone,GenerateBufferedImage {
    private String number;
    private PixelPositionInterface startPixel;
    private PixelPositionInterface endPixel;
    private ZoneInterface zone;
    private LightingGradientDirection directionConfig;
    private double alpha;
    private double level;

    private void initialParam() {
        this.alpha = 1;
        this.level = 1;
    }

    public LightingGradientLinear() {
        initialParam();
    }

    public void setNumber(String Number) {
        this.number = Number;
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

    public void setDirectionConfig(LightingGradientDirection direction) {
        this.directionConfig = direction;
    }

    public void setLevel(double level) {
        this.level = level;
    }
    public String getNumber() {
        return this.number;
    }

    public double getLevel() {
        return this.level;
    }
    public GradientTypeEnumerator getGradientType() {
        return GradientTypeEnumerator.lightingGradient;
    }

    public GradientModeEnumerator getGradientMode() {
        return GradientModeEnumerator.LinearGradient;
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

    public LightingGradientDirection getDirectionConfig() {
        return this.directionConfig;
    }

    public double getAlpha() {
        return this.alpha;
    }

    public Color colorCalculate(Color color, int x, double inc, double aparent, boolean toLight) {
        if(toLight) {
            return (new Color((int)(color.getRed() + inc*x) > 255? 255 : (int)(color.getRed() + inc*x),
                    (int)(color.getGreen() + inc*x) > 255? 255 : (int)(color.getGreen() + inc*x),
                    (int)(color.getBlue() + inc * x) > 255? 255 : (int)(color.getBlue() + inc*x),
                    (int)(color.getAlpha()/aparent)));
        }
        else {
        return (new Color((int)(color.getRed() - inc*x) < 0? 0 : (int)(color.getRed() - inc*x),
                    (int)(color.getGreen() - inc*x) < 0? 0 : (int)(color.getGreen() - inc*x),
                    (int)(color.getBlue() - inc * x) < 0? 0 : (int)(color.getBlue() - inc*x),
                    (int)(color.getAlpha()/aparent)));
        }
    }

    public void pixelCalculate(
            BufferedImage resultImg, BufferedImage originImg,
            PixelPositionInterface originPixel,
            PixelPositionInterface horizentalPixel,
            PixelPositionInterface verticalPixel,
            LightingGradientDirection direction,
            double verticalInc, double horizentalInc, double alpha) {
        int imgWidth = originImg.getWidth();
        int imgHeight = originImg.getHeight();

        if(direction == LightingGradientDirection.FromDarkToLight) {
            for(int x = 0; x < imgWidth; x++) {
                for(int y = 0; y < imgHeight; y++) {
                    Color rgb = new Color(originImg.getRGB(x,y));
                    if(horizentalPixel.getX() > originPixel.getX()) {
                        rgb = colorCalculate(rgb, x, horizentalInc, alpha, true);
                    }
                    else {
                        rgb = colorCalculate(rgb, imgWidth - 1 - x, horizentalInc, alpha, true);
                    }
                    if(verticalPixel.getY() > originPixel.getY()) {
                        rgb = colorCalculate(rgb, y, verticalInc, alpha, true);
                    }
                    else {
                        rgb = colorCalculate(rgb, imgHeight - 1 - y, verticalInc, alpha, true);
                    }
                    resultImg.setRGB(x, y, rgb.getRGB());
                }
            }
        }
        else if(direction == LightingGradientDirection.FromLightToDark) {
            for(int x = 0; x < imgWidth; x++) {
                for(int y = 0; y < imgHeight; y++) {
                    Color rgb = new Color(originImg.getRGB(x,y));
                    if(horizentalPixel.getX() > originPixel.getX()) {
                        rgb = colorCalculate(rgb, x, horizentalInc, alpha, false);
                    }
                    else {
                        rgb = colorCalculate(rgb, imgWidth - 1 - x, horizentalInc, alpha, false);
                    }
                    if(verticalPixel.getY() > originPixel.getY()) {
                        rgb = colorCalculate(rgb, y, verticalInc, alpha, false);
                    }
                    else {
                        rgb = colorCalculate(rgb, imgHeight - 1 - y, verticalInc, alpha, false);
                    }
                    resultImg.setRGB(x, y, rgb.getRGB());
                }
            }
        }
    }

    private BufferedImage imageCalculate(BufferedImage originImg) {
        int imgWidth = originImg.getWidth();
        int imgHeight = originImg.getHeight();

        PixelPositionInterface originPixel = new PixelPosition(
                this.startPixel.getX(), this.startPixel.getY());
        PixelPositionInterface verticalPixel = new PixelPosition(
                this.startPixel.getX(), this.endPixel.getY());
        PixelPositionInterface horizentalPixel = new PixelPosition(
                this.endPixel.getX(), this.startPixel.getY());

        double horizentalInc = 255/Math.pow(EculidDistance.distance(new PixelPosition(0,0), new PixelPosition(imgWidth,imgHeight)),2)
                * Math.abs(horizentalPixel.getX() - originPixel.getX()) * this.level;
        double verticalInc = 255/Math.pow(EculidDistance.distance(new PixelPosition(0, 0), new PixelPosition(imgWidth, imgHeight)),2)
                * Math.abs(verticalPixel.getY() - originPixel.getY()) * this.level;

        BufferedImage resultImg = new BufferedImage(imgWidth, imgHeight,BufferedImage.TYPE_4BYTE_ABGR);

        pixelCalculate(resultImg, originImg,
                originPixel, horizentalPixel, verticalPixel,
                this.directionConfig, verticalInc, horizentalInc, this.alpha);

        return resultImg;
    }

    public BufferedImage generate(BufferedImage originPic) {
        return this.zone.filter(imageCalculate(originPic));
    }


}
