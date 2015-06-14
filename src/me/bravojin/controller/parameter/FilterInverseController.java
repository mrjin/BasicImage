package me.bravojin.controller.parameter;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.filter.basic.FilterInverseColor;
import me.bravojin.layer.Layer;
import me.bravojin.views.mainwindow.MainWindow;

/**
 * Created by tyrionlanister on 15-6-13.
 */
public class FilterInverseController {
    private MainWindow mainWindow;
    private LayerContent layerContent;

    private int selectedIndex;

    public FilterInverseController(LayerContent layerContent, MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.layerContent = layerContent;
    }

    public FilterInverseController setSelectedIndex(int selectedIndex) {
        if(selectedIndex == -1) {
            this.selectedIndex = this.layerContent.getLayerSize()-1;
        }
        else {
            this.selectedIndex = selectedIndex;
        }
        return this;
    }

    public FilterInverseController update() {
        Layer layer = layerContent.getLayerByIndex(selectedIndex);
        layer.add(new FilterInverseColor()).reGenerate();
        this.layerContent.update(layer, selectedIndex);
        return this;
    }
}
