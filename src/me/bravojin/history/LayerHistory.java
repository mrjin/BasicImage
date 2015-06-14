package me.bravojin.history;

import me.bravojin.layer.Layer;

/**
 * Created by tyrionlanister on 15-6-9.
 */
public class LayerHistory implements History {
    private String className;
    private int classIndex;
    private int topLayerIndex;
    public LayerHistory(String name, int index, int topLayerIndex) {
        this.className = name;
        this.classIndex =index;
        this.topLayerIndex = topLayerIndex;
    }

    public LayerHistory(String name, int index) {
        this.className = name;
        this.classIndex = index;
    }

    public String getClassName() {
        return this.className;
    }

    public int getClassIndex() {
        return this.classIndex;
    }

    public int getTopLayerIndex() {
        return this.topLayerIndex;
    }
    @Override
    public boolean equals(Object obj) {
        LayerHistory layerHistory = (LayerHistory)obj;
        return (layerHistory.getClassIndex() == this.classIndex &&
                layerHistory.getClassName() == this.className &&
                layerHistory.getTopLayerIndex() == this.topLayerIndex);
    }

    public LayerHistory setTopLayerIndex(int topLayerIndex) {
        this.topLayerIndex = topLayerIndex;
        return this;
    }

    public boolean isSame(LayerHistory layerHistory) {
        return layerHistory.getClassIndex() == this.classIndex && layerHistory.getClassName() == this.className;
    }

    public String toString(){
        return this.className +" " + this.classIndex + " " + this.topLayerIndex;
    }
}
