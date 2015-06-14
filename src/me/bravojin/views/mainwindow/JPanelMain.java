package me.bravojin.views.mainwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-11.
 */
public class JPanelMain extends JPanel{
    private BufferedImage img;

    public JPanel setImage(BufferedImage img) {
        this.img = img;
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        if (this.getWidth() < width && this.getHeight() < height) {
            this.setSize(width, height);
        }
        return this;
    }

    //画出背景
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
