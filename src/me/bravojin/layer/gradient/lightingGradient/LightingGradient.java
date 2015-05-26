package me.bravojin.layer.gradient.lightingGradient;

/**
 * Created by tyrionlanister on 15-5-25.
 */
import me.bravojin.layer.gradient.GradientInterface;
import me.bravojin.layer.gradient.gradientType.GradientTypeInterface;
import me.bravojin.layer.util.GenerateBufferedImage;
import me.bravojin.layer.util.Zone;
import me.bravojin.pixelPosition.PixelPosition;
import me.bravojin.zone.ZoneInterface;
import me.bravojin.pixelPosition.PixelPositionInterface;
import me.bravojin.layer.gradient.lightingGradient.LightingGradientDirection;
import me.bravojin.util.EculidDistance;

import java.awt.*;
import java.awt.image.BufferedImage;


public class LightingGradient implements GradientInterface,Zone,GenerateBufferedImage {
    private String number;
    private PixelPositionInterface startPixel;
    private PixelPositionInterface endPixel;
    private ZoneInterface zone;
    private GradientTypeInterface gradientType;
    private LightingGradientDirection directionConfig;

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

    public String getNumber() {
        return this.number;
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

    public Color colorCalculate(Color color, int x, double inc, double aparent, boolean toLight) {
        if(toLight) {
            return (new Color((int)(color.getRed() + inc*x) > 255? 255 : (int)(color.getRed() + inc*x),
                    (int)(color.getGreen() + inc*x) > 255? 255 : (int)(color.getGreen() + inc*x),
                    (int)(color.getBlue() + inc * x) > 255? 255 : (int)(color.getBlue() + inc*x),
                    (int)(color.getAlpha()/aparent)));
        }
        else {
//            System.out.println((int)(color.getRed()));
//            System.out.println((int)(color.getGreen()));
//            System.out.println((int)(color.getBlue()));
//            System.out.println((int)(color.getRed() + inc*x));
//            System.out.println((int)(color.getGreen() + inc*x));
//            System.out.println((int)(color.getBlue() + inc*x));
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
            double verticalInc, double horizentalInc) {
        int imgWidth = originImg.getWidth();
        int imgHeight = originImg.getHeight();

        if(direction == LightingGradientDirection.FromDarkToLight) {
            for(int x = 0; x < imgWidth; x++) {
                for(int y = 0; y < imgHeight; y++) {
                    Color rgb = new Color(originImg.getRGB(x,y));
                    if(horizentalPixel.getX() > originPixel.getX()) {
                        rgb = colorCalculate(rgb, x, horizentalInc, 1, true);
                    }
                    else {
                        rgb = colorCalculate(rgb, imgWidth - 1 - x, horizentalInc, 1, false);
                    }
                    if(verticalPixel.getY() > originPixel.getY()) {
                        rgb = colorCalculate(rgb, imgHeight - 1 - y, verticalInc, 1, true);
                    }
                    else {
                        rgb = colorCalculate(rgb, y, verticalInc, 1, false);
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
                        rgb = colorCalculate(rgb, x, horizentalInc, 1, true);
                    }
                    else {
                        rgb = colorCalculate(rgb, imgWidth - 1 - x, horizentalInc, 1, false);
                    }
                    if(verticalPixel.getY() > originPixel.getY()) {
                        rgb = colorCalculate(rgb, imgHeight - 1 - y, verticalInc, 1, true);
                    }
                    else {
                        rgb = colorCalculate(rgb, y, verticalInc, 1, false);
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
                * Math.abs(horizentalPixel.getX() - originPixel.getX());
        double verticalInc = 255/Math.pow(EculidDistance.distance(new PixelPosition(0, 0), new PixelPosition(imgWidth, imgHeight)),2)
                * Math.abs(verticalPixel.getY() - originPixel.getY());

        BufferedImage resultImg = new BufferedImage(imgWidth, imgHeight,BufferedImage.TYPE_4BYTE_ABGR);

        pixelCalculate(resultImg, originImg,
                originPixel, horizentalPixel, verticalPixel,
                this.directionConfig, verticalInc, horizentalInc);

        return resultImg;
    }

    public BufferedImage generate(BufferedImage originPic) {
        return this.zone.filter(imageCalculate(originPic));
    }


}
