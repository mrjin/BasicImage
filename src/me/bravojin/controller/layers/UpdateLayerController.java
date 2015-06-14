package me.bravojin.controller.layers;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.layer.Layer;
import me.bravojin.layer.LayerToShow;
import me.bravojin.views.mainwindow.MainWindow;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Vector;

/**
 * Created by tyrionlanister on 15-6-11.
 */
public class UpdateLayerController {
    private LayerContent layerContent;
    private MainWindow mainWindow;
    private Vector<String> vector;

    public UpdateLayerController(LayerContent layerContent, MainWindow mainWindow) {
        this.vector = new Vector<String>();
        this.layerContent = layerContent;
        this.mainWindow = mainWindow;
        for(int i = 0; i < this.layerContent.getLayerSize(); i++) {
            this.vector.add("Layer "+i);
        }
        this.mainWindow.getListLayer().setListData(this.vector);
    }

    public void reGenerate() {
        this.vector = new Vector<String>();
        System.out.println(this.layerContent.getLayerSize());
        for(int i = 0; i < this.layerContent.getLayerSize(); i++) {
            this.vector.add("Layer "+i);
        }

        this.mainWindow.getListLayer().setListData(this.vector);
    }

    public void update() {
        this.reGenerate();
    }
}
