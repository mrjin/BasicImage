package me.bravojin.controller.parameter.gradient;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.layer.Layer;
import me.bravojin.layer.gradient.transparentGradient.TransparentGradientDirection;
import me.bravojin.layer.gradient.transparentGradient.TransparentGradientLinear;
import me.bravojin.pixelPosition.PixelPosition;
import me.bravojin.views.mainwindow.MainWindow;
import me.bravojin.views.paramwindow.GradientParam;

import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-13.
 */
public class GradientTransparentController {
    private LayerContent layerContent;
    private MainWindow mainWindow;
    private GradientParam gradientParam;

    private int selectedIndex;

    public GradientTransparentController(LayerContent layerContent, MainWindow mainWindow, GradientParam gradientParam) {
        this.layerContent = layerContent;
        this.mainWindow = mainWindow;
        this.gradientParam = gradientParam;
    }

    public GradientTransparentController setLayerContent(LayerContent layerContent) {
        this.layerContent = layerContent;
        return this;
    }

    public GradientTransparentController setSelectedIndex(int selectedIndex) {
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
            System.out.println("[log]Transparent Preview Generated From Trans To Opq");
            return (new TransparentGradientLinear()).setStartPixel(new PixelPosition(fromX, fromY))
                    .setEndPixel(new PixelPosition(toX, toY)).setLevel(level)
                    .setDirectionConfig(TransparentGradientDirection.FromTransparentToOpaque)
                    .generate(this.layerContent.getLayerByIndex(selectedIndex).getTopLayerImg());
        }
        else if(kind == 1){
            System.out.println("[log]Transparent Preview Generated From Trans TO Opq");
            return (new TransparentGradientLinear()).setStartPixel(new PixelPosition(fromX, fromY))
                    .setEndPixel(new PixelPosition(toX, toY)).setLevel(level)
                    .setDirectionConfig(TransparentGradientDirection.FromOpaqueToTransparent)
                    .generate(this.layerContent.getLayerByIndex(selectedIndex).getTopLayerImg());
        }
        else {
            System.out.println("[debug]No Preview Generated");
            return null;
        }
    }

    public GradientTransparentController update(int fromX, int fromY, int toX, int toY, int kind, double level) {
        if(kind == 0) {
            System.out.println("[log]Lighting Gradient Add to Layer From Trans to Qpq");
            Layer layer = this.layerContent.getLayerByIndex(selectedIndex);
            layer.add((new TransparentGradientLinear()).setStartPixel(new PixelPosition(fromX, fromY))
                    .setEndPixel(new PixelPosition(toX, toY)).setDirectionConfig(TransparentGradientDirection.FromTransparentToOpaque)
                    .setLevel(level)).reGenerate();
            layerContent.update(layer, selectedIndex);
        }
        else if(kind == 1) {
            System.out.println("[log]Lighting Gradient Add to Layer From Opg to Trans");
            Layer layer = this.layerContent.getLayerByIndex(selectedIndex);
            layer.add((new TransparentGradientLinear()).setStartPixel(new PixelPosition(fromX, fromY))
                    .setEndPixel(new PixelPosition(toX, toY)).setDirectionConfig(TransparentGradientDirection.FromOpaqueToTransparent)
                    .setLevel(level)).reGenerate();
            layerContent.update(layer, selectedIndex);
        }
        else{
            System.out.println("[debug]No Layer found");
        }
        return this;
    }

}
