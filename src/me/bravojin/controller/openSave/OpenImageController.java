package me.bravojin.controller.openSave;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.views.mainwindow.MainWindow;

import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-12.
 */
public class OpenImageController {
    private LayerContent layerContent;
    private MainWindow mainWindow;

    public OpenImageController(LayerContent layerContent, MainWindow mainWindow) {
        this.layerContent = layerContent;
        this.mainWindow = mainWindow;
    }

    public OpenImageController addImage(BufferedImage bufferedImage) {
        System.out.println("[log]Open Image Processed");
        this.mainWindow.getCreateLayerController().createLayer(
                this.mainWindow.getOpenImageSizeChangeController().getTopSizedImage(bufferedImage));
        this.mainWindow.getUpdateLayerController().update();
        this.mainWindow.getTopLayerShowController().update();
        return this;
    }
}
