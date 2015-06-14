package me.bravojin.controller.parameter.smooth;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.filter.linear.FilterBoxSmooth;
import me.bravojin.filter.linear.FilterGuassSmooth;
import me.bravojin.layer.Layer;
import me.bravojin.views.mainwindow.MainWindow;
import me.bravojin.views.paramwindow.SmoothParam;

import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-13.
 */
public class GuassSmoothController {
    private LayerContent layerContent;
    private MainWindow mainWindow;
    private SmoothParam smoothParam;

    public GuassSmoothController(LayerContent layerContent, MainWindow mainWindow, SmoothParam smoothParam) {
        this.layerContent = layerContent;
        this.mainWindow = mainWindow;
        this.smoothParam = smoothParam;
    }

    public GuassSmoothController setLayerContent(LayerContent layerContent) {
        this.layerContent = layerContent;
        return this;
    }

    public BufferedImage getPreview(double sigma, int defaultParam, int index){
        if(index == -1) {
            Layer layer = this.layerContent.getLayerByIndex(this.layerContent.getLayerSize()-1);
            if(layer != null) {
                System.out.println("[log]create guass smooth preview.");
                return (new FilterGuassSmooth(sigma,defaultParam)).generate(layer.getTopLayerImg());
            }
            else {
                System.out.println("[debug]No layer found");
                return null;
            }
        }
        else {
            Layer layer = this.layerContent.getLayerByIndex(index);
            if(layer == null) {
                System.out.println("[debug]No layer found");
            }
            else {
                System.out.println("[log]create box smooth preview.");
                return (new FilterGuassSmooth(sigma,defaultParam)).generate(layer.getTopLayerImg());
            }
        }
        return null;
    }

    public GuassSmoothController update(double sigma, int defaultParam, int index) {
        if(index == -1) {
            Layer layer = this.layerContent.getLayerByIndex(this.layerContent.getLayerSize()-1);
            if(layer != null) {
                System.out.println("[log]create guass smooth effect to layer.");
                layer.add(new FilterGuassSmooth(sigma,defaultParam)).reGenerate();
                layerContent.update(layer, this.layerContent.getLayerSize()-1);
            }
            else {
                System.out.println("[debug]No layer found");
            }
        }
        else {
            Layer layer = this.layerContent.getLayerByIndex(index);
            if(layer == null) {
                System.out.println("[debug]No layer found");
            }
            else {
                System.out.println("[log]create box smooth effect to layer");
                layer.add(new FilterGuassSmooth(sigma,defaultParam)).reGenerate();
                layerContent.update(layer,index);
            }
        }
        return this;
    }
}
