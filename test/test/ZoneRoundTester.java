package test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import me.bravojin.zone.ZoneRound;
/**
 * Created by tyrionlanister on 15-5-26.
 */
public class ZoneRoundTester {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        BufferedImage testImg = null;
        try {
            testImg = ImageIO.read(new FileInputStream("/home/tyrionlanister/图片/test.jpg"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        ZoneRound testZoneRec = new ZoneRound();
        System.out.println(testZoneRec.getZoneType());
        testZoneRec.setCenterPixel(200, 200);
        testZoneRec.setEndPixel(200, 300);
        BufferedImage resultImg = testZoneRec.filter(testImg);
        try {
            ImageIO.write(resultImg, "png", new File("/home/tyrionlanister/图片/result.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime));
    }
}
