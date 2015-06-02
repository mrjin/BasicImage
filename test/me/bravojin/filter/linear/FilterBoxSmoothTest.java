package me.bravojin.filter.linear;

import me.bravojin.filter.type.FilterType;
import me.bravojin.pixelPosition.PixelPosition;
import me.bravojin.zone.ZoneRectangle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import static org.junit.Assert.*;

public class FilterBoxSmoothTest {
    private BufferedImage testImg1;
    private FilterBoxSmooth boxSmooth1;
    private FilterBoxSmooth boxSmooth2;
    private FilterBoxSmooth boxSmooth3;

    @Before
    public void setUp() throws Exception {
        this.testImg1 = null;
        try {
            this.testImg1 = ImageIO.read(new FileInputStream("/home/tyrionlanister/图片/Java/test7.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        boxSmooth1 = new FilterBoxSmooth();
        boxSmooth2 = new FilterBoxSmooth(5,5);
        boxSmooth3 = new FilterBoxSmooth(7,7);
        boxSmooth3.setZone(new ZoneRectangle(new PixelPosition(0,0), new PixelPosition(500, 500)));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetWidthCov() throws Exception {
        int [] testArray1 = boxSmooth1.getWidthCov();
        int [] testArray2 = boxSmooth2.getWidthCov();
        int [] testArray3 = boxSmooth3.getWidthCov();
        for(int i = 0 ; i < testArray1.length ; i++) {
            assertEquals(1, testArray1[i]);
        }
        for(int i = 0 ; i < testArray2.length ; i++) {
            assertEquals(1, testArray2[i]);
        }
        for(int i = 0 ; i < testArray3.length ; i++) {
            assertEquals(1, testArray3[i]);
        }
    }

    @Test
    public void testGetHeightCov() throws Exception {
        int [] testArray1 = boxSmooth1.getHeightCov();
        int [] testArray2 = boxSmooth2.getHeightCov();
        int [] testArray3 = boxSmooth3.getHeightCov();
        for(int i = 0 ; i < testArray1.length ; i++) {
            assertEquals(1, testArray1[i]);
        }
        for(int i = 0 ; i < testArray2.length ; i++) {
            assertEquals(1, testArray2[i]);
        }
        for(int i = 0 ; i < testArray3.length ; i++) {
            assertEquals(1, testArray3[i]);
        }
    }

    @Test
    public void testGetFilterType() throws Exception {
        assertSame(FilterType.BoxSmooth, this.boxSmooth1.getFilterType());
        assertSame(FilterType.BoxSmooth, this.boxSmooth2.getFilterType());
        assertSame(FilterType.BoxSmooth, this.boxSmooth3.getFilterType());
    }

    @Test
    public void testGenerate() throws Exception {
        this.boxSmooth1.generate(this.testImg1);
        this.boxSmooth2.generate(this.testImg1);
        this.boxSmooth3.generate(this.testImg1);
    }

    @Test
    public void testSetZone() throws Exception {
        this.boxSmooth2.setZone(new ZoneRectangle(new PixelPosition(0,0), new PixelPosition(500, 500)));
    }

    @Test
    public void testSetParamMatrix() throws Exception {
        int [][] testParam = new int [5][5];
        for(int i = 0 ; i < 5 ; i++) {
            for(int j = 0; j < 5 ; j++) {
                testParam[i][j] = 2;
            }
        }
        this.boxSmooth2.setParamMatrix(testParam);
        assertEquals(2, this.boxSmooth2.getParamMatrix()[0][0]);
    }

    @Test
    public void testGetZone() throws Exception {
        assert(this.boxSmooth1.getZone() == null);
        assert(this.boxSmooth2.getZone() == null);
        assert(this.boxSmooth3.getZone().equals(
                new ZoneRectangle(new PixelPosition(0, 0), new PixelPosition(500, 500))));
    }
}