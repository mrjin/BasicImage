package me.bravojin.controller.parameter.smooth;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.views.mainwindow.MainWindow;

import javax.swing.*;

/**
 * Created by tyrionlanister on 15-6-13.
 */
public class SmoothClose {
    private LayerContent layerContent;
    private JFrame smoothParamWindow;
    private MainWindow mainWindow;

    public SmoothClose(LayerContent layerContent,MainWindow mainWindow, JFrame smoothParamWindow) {
        this.layerContent = layerContent;
        this.mainWindow = mainWindow;
        this.smoothParamWindow = smoothParamWindow;
    }

    public void close() {
        this.smoothParamWindow.dispose();
    }
}
