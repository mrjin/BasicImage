package me.bravojin.zone;

import me.bravojin.pixelPosition.PixelPosition;
import me.bravojin.pixelPosition.PixelPositionInterface;

import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-5-25.
 */
public class ZoneRectangle implements ZoneInterface {
    private PixelPositionInterface leftUp;
    private PixelPositionInterface rightDown;
    private ZoneType type;

    public ZoneRectangle() {
        this.type = ZoneType.Rectangle;
    }

    public ZoneRectangle(PixelPositionInterface leftUp, PixelPositionInterface rightDown) {
        this.leftUp = leftUp;
        this.rightDown = rightDown;
        this.type = ZoneType.Rectangle;
    }

    public ZoneType getZoneType() {
        return this.type;
    }

    public BufferedImage filter(BufferedImage originImg) {
        int leftUpX = leftUp.getX();
        int leftUpY = leftUp.getY();
        int rightDownX = rightDown.getX();
        int rightDownY = rightDown.getY();
        BufferedImage cutImage = new BufferedImage(rightDownX - leftUpX, rightDownY - leftUpY,
                BufferedImage.TYPE_4BYTE_ABGR);
        int originImageWidth = originImg.getWidth();
        int originImageHeight = originImg.getHeight();
        if(rightDownY <= originImageWidth && rightDownX <= originImageHeight) {
            for(int i = 0; i < cutImage.getWidth(); i++) {
                for(int j = 0 ; j < cutImage.getHeight(); j++) {
                    cutImage.setRGB(i,j,originImg.getRGB(leftUpX + i, leftUpY + j));
                }
            }
            return cutImage;
        }
        else {
            return null;
        }
    }

    public byte[] filter(byte[] originPicByte, int width, int height) {
        int leftUpX = leftUp.getX();
        int leftUpY = leftUp.getY();
        int rightDownX = rightDown.getX();
        int rightDownY = rightDown.getY();
        int cutWidth = rightDownX - leftUpX;
        int cutHeight = rightDownY - leftUpY;
        byte[] cutPicByte = new byte [cutHeight*cutWidth*4];
        if(rightDownY <= width && rightDownX <= height) {
            for(int i = 0; i < cutWidth; i++) {
                for(int j = 0 ; j < cutHeight; j++) {
                    cutPicByte[4*(i + width * j)] = originPicByte[4*((leftUpX + i) + width * (leftUpY + j))];
                    cutPicByte[4*(i + width * j) + 1] = originPicByte[4*((leftUpX + i) + width * (leftUpY + j)) + 1];
                    cutPicByte[4*(i + width * j) + 2] = originPicByte[4*((leftUpX + i) + width * (leftUpY + j)) + 2];
                    cutPicByte[4*(i + width * j) + 3] = originPicByte[4*((leftUpX + i) + width * (leftUpY + j)) + 3];
                }
            }
            return cutPicByte;
        }
        else {
            return null;
        }
    }

    public void setLeftUp(int x, int y) {
        this.leftUp = new PixelPosition(x, y);
    }

    public void setRightDown(int x, int y) {
        this.rightDown = new PixelPosition(x, y);
    }

}
