package me.bravojin.controller.parameter.gradient;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.layer.Layer;
import me.bravojin.layer.gradient.lightingGradient.LightingGradientDirection;
import me.bravojin.layer.gradient.lightingGradient.LightingGradientLinear;
import me.bravojin.pixelPosition.PixelPosition;
import me.bravojin.views.mainwindow.MainWindow;
import me.bravojin.views.paramwindow.GradientParam;

import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-13.
 */
public class GradientLightingController {
    private LayerContent layerContent;
    private MainWindow mainWindow;
    private GradientParam gradientParam;

    private int selectedIndex;

    public GradientLightingController(LayerContent layerContent, MainWindow mainWindow, GradientParam gradientParam) {
        this.layerContent = layerContent;
        this.mainWindow = mainWindow;
        this.gradientParam = gradientParam;
    }

    public GradientLightingController setLayerContent(LayerContent layerContent) {
        this.layerContent = layerContent;
        return this;
    }

    public GradientLightingController setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        return this;
    }

    public int getImgWidth() {
        if(selectedIndex < this.layerContent.getLayerSize()) {
            return layerContent.getLayerByIndex(selectedIndex).getTopLayerImg().getWidth();
        }
        else {
            System.out.println("[debug]Layer Out of Index Gradient Transparent");
            return -1;
        }
    }

    public int getImgHeight() {
        if(selectedIndex < this.layerContent.getLayerSize()) {
            return layerContent.getLayerByIndex(selectedIndex).getTopLayerImg().getHeight();
        }
        else {
            System.out.println("[debug]Layer Out of Index Gradient Transparent");
            return -1;
        }
    }

    public BufferedImage getPreview(int fromX, int fromY, int toX, int toY, int kind, double level) {
        if(kind == 0) {
            System.out.println("[log]Lighting Preview Generated From Lighting To Dark");
            return (new LightingGradientLinear()).setStartPixel(new PixelPosition(fromX, fromY))
                    .setEndPixel(new PixelPosition(toX, toY)).setDirectionConfig(LightingGradientDirection.FromLightToDark)
                    .setLevel(level).generate(this.layerContent.getLayerByIndex(selectedIndex).getTopLayerImg());
        }
        else if(kind == 1) {
            System.out.println("[log]Lighting Preview Generated From Dark To Lighting");
            return (new LightingGradientLinear()).setStartPixel(new PixelPosition(fromX, fromY))
                    .setEndPixel(new PixelPosition(toX, toY)).setDirectionConfig(LightingGradientDirection.FromDarkToLight)
                    .setLevel(level).generate(this.layerContent.getLayerByIndex(selectedIndex).getTopLayerImg());
        }
        else{
            System.out.println("[debug]No Preview Generated");
            return null;
        }
    }
    public GradientLightingController update(int fromX, int fromY, int toX, int toY, int kind, double level) {
        if(kind == 0) {
            System.out.println("[log]Lighting Gradient Add to Layer From Light to Dark");
            Layer layer = this.layerContent.getLayerByIndex(selectedIndex);
            layer.add((new LightingGradientLinear()).setStartPixel(new PixelPosition(fromX, fromY))
                    .setEndPixel(new PixelPosition(toX, toY)).setDirectionConfig(LightingGradientDirection.FromLightToDark)
                    .setLevel(level)).reGenerate();
            layerContent.update(layer, selectedIndex);
        }
        else if(kind == 1) {
            System.out.println("[log]Lighting Gradient Add to Layer From Dark to Light");
            Layer layer = this.layerContent.getLayerByIndex(selectedIndex);
            layer.add((new LightingGradientLinear()).setStartPixel(new PixelPosition(fromX, fromY))
                            .setEndPixel(new PixelPosition(toX, toY)).setDirectionConfig(LightingGradientDirection.FromDarkToLight)
                            .setLevel(level)).reGenerate();
            layerContent.update(layer, selectedIndex);
        }
        else{
            System.out.println("[debug]No Layer found");
        }
        return this;
    }
}
