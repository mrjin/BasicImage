package me.bravojin.pixelPosition;

import java.util.Objects;

/**
 * Created by tyrionlanister on 15-5-25.
 */
public class PixelPosition implements PixelPositionInterface {
    private int x;
    private int y;

    public PixelPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    public boolean equals(Object obj) {
        if(obj instanceof PixelPosition) {
            PixelPosition pixel = (PixelPosition)obj;
            if(pixel.getX() == this.x && pixel.getY() == this.y) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public String toString() {
        return "(" + this.x + "," + this.y+")";
    }
}
