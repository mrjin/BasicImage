package me.bravojin.controller.layerDetail;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.layer.Layer;
import me.bravojin.views.layerwindow.LayerDetailWindow;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by tyrionlanister on 15-6-15.
 * 这个文件是每个图层的详细结果，与主框图中的Detail按钮是一一对应的关系。
 */
public class LayerDetailList {
    public LayerDetailWindow layerDetailWindow;
    public LayerContent layerContent;

    public LayerDetailList(LayerDetailWindow layerDetailWindow, LayerContent layerContent) {
        this.layerDetailWindow = layerDetailWindow;
        this.layerContent = layerContent;
    }

    public LayerDetailList setLayerContent(LayerContent layerContent) {
        this.layerContent = layerContent;
        return this;
    }

    public Vector<String> update(int selectedValue) {
        if(layerContent.getLayerSize() > 0) {
            Vector<String> vector = new Vector<String>();
            Layer layer;
            if(selectedValue == -1) {
                layer = this.layerContent.getLayerByIndex(this.layerContent.getLayerSize() - 1);
            }
            else {
                layer = this.layerContent.getLayerByIndex(selectedValue);
            }
            ArrayList<String> temp = layer.getGradient();
            for (int i = 0; i < temp.size(); i++) {
                vector.add("G" + i +":" +temp.get(i));
            }
            temp = layer.getMask();
            for (int i = 0; i < temp.size(); i++) {
                vector.add("M" + i+":"+temp.get(i));
            }
            temp = layer.getFilter();
            for (int i = 0; i < temp.size(); i++) {
                vector.add("F" + i + ":" + temp.get(i));
            }
            temp = layer.getParam();
            for (int i = 0; i < temp.size(); i++) {
                vector.add("P" + i + ":" + temp.get(i));
            }
            return vector;
        }
        else {
            return null;
        }
    }
}
