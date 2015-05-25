package me.bravojin.test;

import me.bravojin.zone.ZoneInterface;
import me.bravojin.zone.ZoneRectangle;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by tyrionlanister on 15-5-25.
 */

public class ZoneRectangleTester {

    public static void main(String[] args) {
        BufferedImage testImg = null;
        try {
            testImg = ImageIO.read(new FileInputStream("/home/tyrionlanister/图片/test.jpg"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        ZoneRectangle testZoneRec = new ZoneRectangle();
        System.out.println(testZoneRec.getZoneType());
        testZoneRec.setLeftUp(0, 0);
        testZoneRec.setRightDown(500,300);
        BufferedImage resultImg = testZoneRec.filter(testImg);
        try {
            ImageIO.write(resultImg, "png", new FileOutputStream("/home/tyrionlanister/图片/result.png"));
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
