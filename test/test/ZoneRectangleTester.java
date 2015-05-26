package test;

import me.bravojin.zone.ZoneRectangle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by tyrionlanister on 15-5-25.
 */

public class ZoneRectangleTester {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
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
        //BufferedImage resultImg = testZoneRec.filter(testImg);
        ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
        try {
            boolean resultWrite = ImageIO.write(testImg, "jpg", imageStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte [] testByte = imageStream.toByteArray();
//        testZoneRec.filter(testByte, testImg.getWidth(), testImg.getHeight());
        System.out.println(testByte[1]);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime));
    }
}
