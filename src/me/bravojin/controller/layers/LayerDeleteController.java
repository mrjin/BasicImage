package me.bravojin.controller.layers;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.layer.Layer;
import me.bravojin.views.mainwindow.MainWindow;

/**
 * 图层删除对应的控制器
 */
public class LayerDeleteController {
    private LayerContent layerContent;
    private MainWindow mainWindow;

    public LayerDeleteController(LayerContent layerContent, MainWindow mainWindow) {
        this.layerContent  = layerContent;
        this.mainWindow = mainWindow;
    }

    public LayerDeleteController update(int index) {
        if(index == -1) {
            this.layerContent.delete(this.layerContent.getLayerSize() - 1);
            this.mainWindow.getTopLayerShowController().update();
            this.mainWindow.getUpdateLayerController().update();
        }
        else {
            this.layerContent.delete(index);
            this.mainWindow.getTopLayerShowController().update();
            this.mainWindow.getUpdateLayerController().update();
        }
        return this;
    }
}
