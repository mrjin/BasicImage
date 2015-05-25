package me.bravojin.pixelPosition;

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
}
