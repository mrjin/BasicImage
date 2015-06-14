package me.bravojin.controller.layers;

import me.bravojin.layer.Layer;
import me.bravojin.layer.LayerToShow;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

/**
 * Created by tyrionlanister on 15-6-13.
 */
public class LayerToShowJTree {
    public static DefaultMutableTreeNode generate(LayerToShow layerToShow, Layer layer, String filename, int index) {
        layerToShow.setLayer(layer).setfilename(filename).setIndex(index).generate();
        DefaultMutableTreeNode layerNode = new DefaultMutableTreeNode(layerToShow.getLayerName());

        DefaultMutableTreeNode gradientNode = new DefaultMutableTreeNode("Gradient");
        DefaultMutableTreeNode filterNode = new DefaultMutableTreeNode("Filter");
        DefaultMutableTreeNode maskNode = new DefaultMutableTreeNode("Mask");
        DefaultMutableTreeNode paramNode = new DefaultMutableTreeNode("Parameter");

        ArrayList<String> temp = layer.getGradient();
        System.out.println(temp.size());
        for (int i = 0; i < temp.size(); i++) {
            gradientNode.add(new DefaultMutableTreeNode(temp.get(i)));
        }
        temp = (layerToShow).get("mask");
        for (int i = 0; i < temp.size(); i++) {
            maskNode.add(new DefaultMutableTreeNode(temp.get(i)));
        }
        temp = (layerToShow).get("filter");
        for (int i = 0; i < temp.size(); i++) {
            filterNode.add(new DefaultMutableTreeNode(temp.get(i)));
        }
        temp = (layerToShow).get("param");
        for (int i = 0; i < temp.size(); i++) {
            paramNode.add(new DefaultMutableTreeNode(temp.get(i)));
        }

        layerNode.add(gradientNode);
        layerNode.add(maskNode);
        layerNode.add(filterNode);
        layerNode.add(paramNode);

        return layerNode;
    }
}
