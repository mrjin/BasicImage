package me.bravojin.util;

/**
 * Created by tyrionlanister on 15-5-25.
 */
import me.bravojin.pixelPosition.PixelPositionInterface;


public class EculidDistance {
    public static double distance(PixelPositionInterface apixel, PixelPositionInterface bpixel) {
        return Math.sqrt(Math.pow(apixel.getX() - bpixel.getX(),2) +
            Math.pow(apixel.getY() - bpixel.getY(),2));
    }
}
