package me.bravojin.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class RGBTest {

    @Test
    public void testGetOrder() throws Exception {
        assertEquals("RGB", RGB.getOrder(200, 100, 50));
        assertEquals("RGB", RGB.getOrder(200, 200, 200));
        assertEquals("RGB", RGB.getOrder(200, 100, 100));
        assertEquals("RGB", RGB.getOrder(100, 100, 100));

        assertEquals("RBG", RGB.getOrder(200, 50, 100));
        assertEquals("RBG", RGB.getOrder(200, 0, 100));
        assertEquals("RBG", RGB.getOrder(100, 0, 50));

        assertEquals("BRG", RGB.getOrder(50, 50, 100));
        assertEquals("BRG", RGB.getOrder(100, 50, 200));
        assertEquals("BRG", RGB.getOrder(50, 0, 100));

        assertEquals("BGR", RGB.getOrder(0, 50, 100));
        assertEquals("BGR", RGB.getOrder(50, 100, 100));
        assertEquals("BGR", RGB.getOrder(50, 100, 100));

        assertEquals("GRB", RGB.getOrder(50, 100, 49));
        assertEquals("GRB", RGB.getOrder(50, 100, 0));


        assertEquals("GBR", RGB.getOrder(0, 100, 50));
        assertEquals("GBR", RGB.getOrder(50, 100, 50));
    }
}