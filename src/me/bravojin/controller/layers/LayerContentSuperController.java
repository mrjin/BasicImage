package me.bravojin.controller.layers;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.LayerContent.LayerContentKind;
import me.bravojin.views.mainwindow.MainWindow;

/**
 * Created by tyrionlanister on 15-6-14.
 */
public class LayerContentSuperController {
    private LayerContent layerContent;
    private MainWindow mainWindow;

    public LayerContentSuperController(LayerContent layerContent, MainWindow mainWindow) {
        this.layerContent = layerContent;
        this.mainWindow = mainWindow;
    }

    public LayerContentSuperController update(LayerContentKind kind, int selectedValue) {
        if(selectedValue == -1) {
            if (this.layerContent.getLayerKindByIndex(this.layerContent.getLayerSize() - 1) == kind) {
                return this;
            }
            else {
                this.layerContent.updateSuperPositionKind(kind, this.layerContent.getLayerSize() - 1);
                this.mainWindow.getTopLayerShowController().update();
                System.out.println("[log]Layer Super Changed.");
                return this;
            }
        }
        else {
            if (this.layerContent.getLayerKindByIndex(selectedValue) == kind) {
                return this;
            }
            else {
                this.layerContent.updateSuperPositionKind(kind, selectedValue);
                this.mainWindow.getTopLayerShowController().update();
                System.out.println("[log]Layer Super Changed");
                return this;
            }
        }
    }
}
