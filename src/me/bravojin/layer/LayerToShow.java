package me.bravojin.layer;

import me.bravojin.util.Array;

import java.util.ArrayList;

/**
 * Created by tyrionlanister on 15-6-13.
 */
public class LayerToShow {
    private Layer layer;
    private String filename;
    private int index;
    private ArrayList<String> gradient;
    private ArrayList<String> mask;
    private ArrayList<String> filter;
    private ArrayList<String> param;

    public LayerToShow() {
        gradient = new ArrayList<String>();
        mask = new ArrayList<String>();
        filter = new ArrayList<String>();
        param = new ArrayList<String>();
    }

    public String getLayerName(){
        return "layer " + this.index + ":" + this.filename;
    }

    public LayerToShow setfilename(String filename) {
        this.filename = filename;
        return this;
    }

    public LayerToShow setIndex(int index) {
        this.index = index;
        return this;
    }

    public LayerToShow setLayer(Layer layer) {
        this.layer = layer;
        return this;
    }

    public ArrayList<String> get(String kind) {
        if(kind == "gradient") {
            return this.gradient;
        }
        else if(kind == "mask") {
            return this.mask;
        }
        else if(kind == "filter") {
            return this.filter;
        }
        else {
            return this.param;
        }
    }

    public LayerToShow generate(){
        this.gradient = layer.getGradient();
        this.param = layer.getParam();
        this.filter = layer.getFilter();
        this.mask = layer.getMask();
        return this;
    }
}
