package me.bravojin.views.util;


import me.bravojin.layer.LayerToShow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
/**
 * Created by tyrionlanister on 15-6-12.
 */
public class CellRendererListLayers extends JList implements ListCellRenderer{
    private boolean isClose;
    public CellRendererListLayers() {
        this.isClose = false;
    }
    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        if(value != null) {
            value = (LayerToShow) value;
            JList<String> jlist = new JList<String>();
            Vector<String> vector = new Vector<String>();
            vector.add(((LayerToShow) value).getLayerName());
            this.setListData(vector);
            list.add(this);
        }
        if(isSelected) {
            Vector<String> vector = new Vector<String>();
            ArrayList<String> temp = ((LayerToShow) value).get("gradient");
            for (int i = 0; i < temp.size(); i++) {
                vector.add(temp.get(i));
            }
            temp = ((LayerToShow) value).get("mask");
            for (int i = 0; i < temp.size(); i++) {
                vector.add(temp.get(i));
            }
            temp = ((LayerToShow) value).get("filter");
            for (int i = 0; i < temp.size(); i++) {
                vector.add(temp.get(i));
            }
            temp = ((LayerToShow) value).get("param");
            for (int i = 0; i < temp.size(); i++) {
                vector.add(temp.get(i));
            }
            this.setListData(vector);
            setForeground(new Color(0, 0, 210));
        }
        else{
            JList<String> jList = new JList<String>();
            Vector<String>vector=new Vector<String>();
            vector.add(((LayerToShow)value).getLayerName());
            this.setListData(vector);
            list.add(this);
        }
        return this;
    }
}
