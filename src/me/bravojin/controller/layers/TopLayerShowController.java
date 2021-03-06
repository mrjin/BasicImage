package me.bravojin.controller.layers;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.layer.Layer;
import me.bravojin.views.mainwindow.MainWindow;

/**
 * 更新主界面图片显示的控制器
 */
public class TopLayerShowController {
    private MainWindow mainWindow;
    private LayerContent layerContent;

    public TopLayerShowController(LayerContent layerContent, MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.layerContent = layerContent;
    }

    public TopLayerShowController update() {
        if(this.layerContent.getTop() == null) {
            this.mainWindow.paintTopLayer(null);
            return this;
        }
        else {
            this.mainWindow.paintTopLayer(this.layerContent.getTop());
//        this.mainWindow.getUpdateLayerController().reGenerate();
            return this;
        }
    }

    public LayerContent getLayerContent() {
        return this.layerContent;
    }
}
