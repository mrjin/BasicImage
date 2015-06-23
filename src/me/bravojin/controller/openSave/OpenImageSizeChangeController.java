package me.bravojin.controller.openSave;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.views.mainwindow.MainWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 将打开的图片大小改为和主界面保持一致。
 */
public class OpenImageSizeChangeController {
    private MainWindow mainWindow;
    private LayerContent layerContent;

    public OpenImageSizeChangeController(LayerContent layerContent, MainWindow mainWindow) {
        this.layerContent = layerContent;
        this.mainWindow = mainWindow;
    }

    public BufferedImage getTopSizedImage(BufferedImage originImage) {
        BufferedImage topImg = this.layerContent.getTop();
        if(topImg != null) {
            BufferedImage resultImg = new BufferedImage(topImg.getWidth(), topImg.getHeight(), topImg.getType());
            Graphics g = resultImg.createGraphics();
            g.drawImage(originImage, 0, 0, topImg.getWidth(), topImg.getHeight(), null);
            g.dispose();
            System.out.println("[log]Image Size Changed");
            //System.out.println("Origin:"+topImg.getWidth()+":"+topImg.getHeight());
            //System.out.println("Sized:"+resultImg.getWidth()+":"+resultImg.getHeight());
            return resultImg;
        }
        else {
            System.out.println("[log]Image Size Not Changed");
            return originImage;
        }
    }
}
