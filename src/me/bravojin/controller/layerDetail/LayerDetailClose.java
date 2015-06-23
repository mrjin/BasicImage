package me.bravojin.controller.layerDetail;

import me.bravojin.views.layerwindow.LayerDetailWindow;

import javax.swing.*;

/**
 * LayerDetail窗口对应的关闭的控制器
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
