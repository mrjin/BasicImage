package me.bravojin.controller.layerDetail;

import me.bravojin.views.layerwindow.LayerDetailWindow;

import javax.swing.*;

/**
 * Created by tyrionlanister on 15-6-15.
 */
public class LayerDetailClose {
    private JFrame layerDetailWindow;

    public LayerDetailClose(JFrame layerDetailWindow) {
        this.layerDetailWindow = layerDetailWindow;
    }

    public LayerDetailClose close() {
        this.layerDetailWindow.dispose();
        return this;
    }
}
