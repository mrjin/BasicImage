package me.bravojin.controller.layers;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.layer.Layer;
import me.bravojin.views.mainwindow.MainWindow;

import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-11.
 */
public class CreateLayerController {
    private LayerContent layerContent;
    private MainWindow mainWindow;

    public CreateLayerController(LayerContent layerContent, MainWindow mainWindow) {
        this.layerContent = layerContent;
        this.mainWindow = mainWindow;
    }

    public CreateLayerController createLayer() {
        if(this.layerContent.getTop() == null) {
            System.out.println("[info]001 No files open.");
        }
        else {
            this.layerContent.add(this.layerContent.getLayerSize()-1);
            System.out.println("[log]Add a new Layer");
        }
        return this;
    }

    public CreateLayerController createLayer(BufferedImage bufferedImage) {
        this.layerContent.add(new Layer(this.layerContent.getLayerSize(),bufferedImage));
        System.out.println("[log]Add a new Layer");
        return this;
    }
}
