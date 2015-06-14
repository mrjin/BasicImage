package me.bravojin.views.util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-12.
 */
public class CellRendererListChannels extends JLabel implements ListCellRenderer{
    private void CellRenderer(){
        setOpaque(true);
    }
    public   Component   getListCellRendererComponent(JList list, Object value, int index,
                                                 boolean isSelected, boolean cellHasFocus) {
        if(value != null) {
            setText("");
            BufferedImage bufferedImage = (BufferedImage)value;
            setIcon(new ImageIcon(bufferedImage));
            setBounds(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        if(isSelected) {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}
