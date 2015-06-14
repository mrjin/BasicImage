package me.bravojin.LayerContent;

import me.bravojin.LayerContent.LayerSuper.LayerSuperDarken;
import me.bravojin.LayerContent.LayerSuper.LayerSuperMultiply;
import me.bravojin.LayerContent.LayerSuper.LayerSuperSubtract;
import me.bravojin.layer.Layer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by tyrionlanister on 15-6-10.
 */
public class LayerContent {
    private ArrayList<Layer> layer;
    private ArrayList<BufferedImage> superPosition;
    private ArrayList<LayerContentKind> superPositionKind;

    public LayerContent() {
        this.layer = new ArrayList<Layer>();
        this.superPosition = new ArrayList<BufferedImage>();
        this.superPositionKind = new ArrayList<LayerContentKind>();
    }

    public LayerContent(Layer layer) {
        this.layer = new ArrayList<Layer>();
        this.superPosition = new ArrayList<BufferedImage>();
        this.superPositionKind = new ArrayList<LayerContentKind>();
        this.add(layer);
    }
    public LayerContent add(Layer layer) {
        this.addLayer(layer).addSuperPositionKind(LayerContentKind.Subtract).addSuperPosition();
        return this;
    }

    public LayerContent add(Layer layer, LayerContentKind layerContentKind) {
        this.addLayer(layer).addSuperPositionKind(layerContentKind).addSuperPosition();
        return this;
    }

    public LayerContent add(int index) {
        if(index < this.layer.size()) {
            this.addLayer(this.layer.get(index)).addSuperPositionKind(LayerContentKind.Subtract).addSuperPosition();
        }
        else {
            System.out.print("[info]401 index out of range");
        }
        return this;
    }

    public LayerContent update(Layer layer, int index) {
        if(index < this.layer.size()) {
            this.layer.set(index, layer);
            this.updateSuperPosition(index);
        }
        return this;
    }

    private LayerContent addLayer(Layer layer) {
        this.layer.add(layer);
        return this;
    }

    private LayerContent addSuperPosition() {
        if(this.superPositionKind.size() < this.layer.size()) {
            this.addSuperPositionKind(LayerContentKind.Subtract);
        }
        this.superPosition.add(this.diffModeGenerate(this.superPosition.size()));
        return this;
    }

    private LayerContent addSuperPositionKind(LayerContentKind layerContentKind) {
        this.superPositionKind.add(layerContentKind);
        return this;
    }

    public LayerContent updateSuperPositionKind(LayerContentKind layerContentKind, int index) {
        if(index < this.superPositionKind.size()) {
            this.superPositionKind.set(index,layerContentKind);
            this.updateSuperPosition(index);
        }
        return this;
    }

    private LayerContent updateSuperPosition(int index) {
        for(int i = index; i < this.layer.size(); i++) {
            this.superPosition.set(i,diffModeGenerate(i));
        }
        return this;
    }

    private BufferedImage diffModeGenerate(int index) {
        if(index == 0) {
            return this.layer.get(0).getTopLayerImg();
        }
        else {
            if(this.superPositionKind.get(index) == LayerContentKind.Darken) {
                return LayerSuperDarken.generate(this.superPosition.get(index - 1), this.layer.get(index).getTopLayerImg());
            }
            if(this.superPositionKind.get(index) == LayerContentKind.Multiply) {
                return LayerSuperMultiply.generate(this.superPosition.get(index - 1), this.layer.get(index).getTopLayerImg());
            }
            if(this.superPositionKind.get(index) == LayerContentKind.Subtract) {
                return LayerSuperSubtract.generate(this.superPosition.get(index - 1), this.layer.get(index).getTopLayerImg());
            }
        }
        return null;
    }

    public BufferedImage getTop() {
        if(this.superPosition.size() == 0) {
            return null;
        }
        else {
            return this.superPosition.get(this.superPosition.size() - 1);
        }
    }

    public int getLayerSize() {
        return this.layer.size();
    }

    public Layer getLayerByIndex(int index) {
        if(index >= this.layer.size()) {
            return null;
        }
        else {
            return this.layer.get(index);
        }
    }
}
