package me.bravojin.controller.parameter.gradient;

import javax.swing.*;

/**
 * Created by tyrionlanister on 15-6-13.
 */
public class GradientClose {
    private JFrame gradientParamWindow;

    public GradientClose(JFrame gradientParamWindow) {
        this.gradientParamWindow = gradientParamWindow;
    }
    public void close() {
        this.gradientParamWindow.dispose();
    }
}
