package me.bravojin.controller.histogram;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.histogram.createHistogram.Data.HistogramDataLuma;
import me.bravojin.histogram.createHistogram.Data.HistogramDataRGB;
import me.bravojin.histogram.createHistogram.DrawGraph.HistogramDataDrawGraph;
import me.bravojin.histogram.createHistogram.Generate.HistogramGenerate;
import me.bravojin.layer.api.GenerateBufferedImage;
import me.bravojin.layer.gradient.GradientInterface;
import me.bravojin.views.mainwindow.MainWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-11.
 */
public class GenerateHistogramController {
    private MainWindow mainWindow;
    private LayerContent layerContent;

    public GenerateHistogramController(LayerContent layerContent, MainWindow mainWindow) {
        this.layerContent = layerContent;
        this.mainWindow = mainWindow;
    }

    public void update(){
        HistogramDataRGB histogramDataRGB = (HistogramDataRGB) HistogramGenerate.generateHistogramDataRGB(8, this.layerContent.getTop());
        this.mainWindow.paintHistogram(HistogramDataDrawGraph.drawRGBWithShade(histogramDataRGB.getR(),
                histogramDataRGB.getG(), histogramDataRGB.getB(), 200, new Color(255, 255, 255),
                2, 2, 2));
        System.out.println("[log]Create Histogram");
    }

    public void updateLuma(){
        HistogramDataLuma histogramDataLuma = (HistogramDataLuma) HistogramGenerate.generateHistogramDataLuma(8, this.layerContent.getTop());
        this.mainWindow.paintHistogram(HistogramDataDrawGraph.draw(histogramDataLuma.getLuma(),
                200,new Color(0, 0, 0), new Color(255, 255, 255), 2));
        System.out.println("[log]Create Histogram Luma");
    }
}
