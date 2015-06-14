package me.bravojin.zone;

import me.bravojin.pixelPosition.PixelPosition;
import me.bravojin.pixelPosition.PixelPositionInterface;
import me.bravojin.util.EculidDistance;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 * Created by tyrionlanister on 15-5-26.
 */
public class ZoneRound implements ZoneInterface{
    private ZoneType type;
    private PixelPositionInterface centerPixel;
    private PixelPositionInterface endPixel;

    public ZoneRound() {
        this.type = ZoneType.Round;
    }

    public ZoneRound(PixelPositionInterface centerPixel, PixelPositionInterface endPixel) {
        this.centerPixel = centerPixel;
        this.endPixel = endPixel;
    }

    public BufferedImage filter(BufferedImage originImg) {
        int radius = (int) EculidDistance.distance(centerPixel, endPixel);
        PixelPositionInterface leftUp = new PixelPosition(centerPixel.getX() - radius,
                centerPixel.getY() - radius);
        PixelPositionInterface rightDown = new PixelPosition(centerPixel.getX() + radius,
                centerPixel.getY() + radius);
        ZoneRectangle recZone = new ZoneRectangle(leftUp, rightDown);
        BufferedImage resultImg = recZone.filter(originImg);
        Ellipse2D shape = new Ellipse2D.Double(0, 0, resultImg.getWidth(), resultImg.getHeight());

        Graphics2D graphics = resultImg.createGraphics();
        graphics.setBackground(new Color(0, 0, 0, 0));
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.fill(new Area(shape));
        graphics.setClip(shape);
        graphics.dispose();

        return resultImg;
    }

    public ZoneType getZoneType() {
        return this.type;
    }

    public void setCenterPixel(int x, int y) {
        this.centerPixel = new PixelPosition(x, y);
    }

    public void setEndPixel(int x, int y) {
        this.endPixel = new PixelPosition(x, y);
    }

    public void setCenterPixel(PixelPositionInterface centerPixel) {
        this.centerPixel = centerPixel;
    }

    public void setEndPixel(PixelPositionInterface endPixel) {
        this.endPixel = endPixel;
    }
}
