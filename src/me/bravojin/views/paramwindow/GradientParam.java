package me.bravojin.views.paramwindow;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.controller.parameter.gradient.GradientClose;
import me.bravojin.controller.parameter.gradient.GradientLightingController;
import me.bravojin.controller.parameter.gradient.GradientTransparentController;
import me.bravojin.views.mainwindow.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-13.
 */
public class GradientParam {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField LightingFromX;
    private JTextField LightingFromY;
    private JTextField LightingToY;
    private JTextField LightingToX;
    private JComboBox LightingKind;
    private JButton LightingNoButton;
    private JButton LightingYesButton;
    private JButton LightingPreviewButton;
    private JTextField LightingImgWidth;
    private JTextField LightingImgHeight;
    private JLabel TransparentLabel;
    private JTextField TransparentImgWidth;
    private JTextField TransparentImgHeight;
    private JTextField TransparentFromX;
    private JTextField TransparentFromY;
    private JTextField TransparentToX;
    private JTextField TransparentToY;
    private JComboBox TransparentKind;
    private JButton TransparentPreviewButton;
    private JButton TransparentYesButton;
    private JButton TransparentNoButton;
    private JLabel LightingLabel;
    private JTextField TransparentLevel;
    private JTextField LightingLevel;

    private LayerContent layerContent;
    private MainWindow mainWindow;

    private int selectedValue;

    private GradientClose gradientClose;
    private GradientTransparentController gradientTransparentController;
    private GradientLightingController gradientLightingController;

    public GradientParam() {
        gradientLightingController = new GradientLightingController(layerContent, mainWindow, this);
        gradientTransparentController = new GradientTransparentController(layerContent, mainWindow, this);

        LightingNoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gradientClose.close();
            }
        });
        LightingPreviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (LightingFromX.getText().equals("") ||
                        LightingFromY.getText().equals("") ||
                        LightingToX.getText().equals("") ||
                        LightingToY.getText().equals("") ||
                        LightingLevel.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Wrong Input.Not None.", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (selectedValue == -1) {
                        gradientLightingController.setSelectedIndex(layerContent.getLayerSize() - 1);
                        int fromX = Integer.parseInt(LightingFromX.getText());
                        int fromY = Integer.parseInt(LightingFromY.getText());
                        int toX = Integer.parseInt(LightingToX.getText());
                        int toY = Integer.parseInt(LightingToY.getText());
                        double level = Double.parseDouble(LightingLevel.getText());
                        if (fromX < 0 || fromY < 0 || toX < 0 || toY < 0 || level <= 0) {
                            JOptionPane.showMessageDialog(null, "Wrong Input.All positive.", "Error", JOptionPane.WARNING_MESSAGE);
                        } else {
                            BufferedImage bufferedImage = gradientLightingController.getPreview(fromX, fromY, toX,
                                    toY, LightingKind.getSelectedIndex(), level);
                            LightingLabel.setVisible(false);
                            LightingLabel.setIcon(new ImageIcon(bufferedImage));
                            LightingLabel.setText("");
                            LightingLabel.setBounds(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
                            LightingLabel.setVisible(true);
                            System.out.println("[log]Paint Lighting Gradient Preview");
                        }
                    } else {
                        gradientLightingController.setSelectedIndex(selectedValue);
                        int fromX = Integer.parseInt(LightingFromX.getText());
                        int fromY = Integer.parseInt(LightingFromY.getText());
                        int toX = Integer.parseInt(LightingToX.getText());
                        int toY = Integer.parseInt(LightingToY.getText());
                        double level = Double.parseDouble(LightingLevel.getText());
                        if (fromX < 0 || fromY < 0 || toX < 0 || toY < 0 || level <= 0) {
                            JOptionPane.showMessageDialog(null, "Wrong Input.All positive.", "Error", JOptionPane.WARNING_MESSAGE);
                        } else {
                            BufferedImage bufferedImage = gradientLightingController.getPreview(fromX, fromY, toX,
                                    toY, LightingKind.getSelectedIndex(), level);
                            LightingLabel.setVisible(false);
                            LightingLabel.setIcon(new ImageIcon(bufferedImage));
                            LightingLabel.setText("");
                            LightingLabel.setBounds(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
                            LightingLabel.setVisible(true);
                            System.out.println("[log]Paint Lighting Gradient Preview");
                        }
                    }
                }
            }
        });
        LightingYesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (LightingFromX.getText().equals("") ||
                        LightingFromY.getText().equals("") ||
                        LightingToX.getText().equals("") ||
                        LightingToY.getText().equals("") ||
                        LightingLevel.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Wrong Input.Not None.", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (selectedValue == -1) {
                        gradientLightingController.setSelectedIndex(layerContent.getLayerSize() - 1);
                        int fromX = Integer.parseInt(LightingFromX.getText());
                        int fromY = Integer.parseInt(LightingFromY.getText());
                        int toX = Integer.parseInt(LightingToX.getText());
                        int toY = Integer.parseInt(LightingToY.getText());
                        double level = Double.parseDouble(LightingLevel.getText());
                        if (fromX < 0 || fromY < 0 || toX < 0 || toY < 0 || level <= 0) {
                            JOptionPane.showMessageDialog(null, "Wrong Input.All positive.", "Error", JOptionPane.WARNING_MESSAGE);
                        } else {
                            gradientLightingController.update(fromX, fromY, toX,
                                    toY, LightingKind.getSelectedIndex(), level);
                            mainWindow.getTopLayerShowController().update();
                            System.out.println("[log]Paint Lighting Gradient tp Layer");
                            gradientClose.close();
                        }
                    } else {
                        gradientLightingController.setSelectedIndex(selectedValue);
                        int fromX = Integer.parseInt(LightingFromX.getText());
                        int fromY = Integer.parseInt(LightingFromY.getText());
                        int toX = Integer.parseInt(LightingToX.getText());
                        int toY = Integer.parseInt(LightingToY.getText());
                        double level = Double.parseDouble(LightingLevel.getText());
                        if (fromX < 0 || fromY < 0 || toX < 0 || toY < 0 || level <= 0) {
                            JOptionPane.showMessageDialog(null, "Wrong Input.All positive.", "Error", JOptionPane.WARNING_MESSAGE);
                        } else {
                            gradientLightingController.update(fromX, fromY, toX,
                                    toY, LightingKind.getSelectedIndex(), level);
                            mainWindow.getTopLayerShowController().update();
                            System.out.println("[log]Paint Lighting Gradient tp Layer");
                            gradientClose.close();
                        }
                    }
                }
            }
        });
        TransparentNoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gradientClose.close();
            }
        });
        TransparentPreviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (TransparentFromX.getText().equals("") ||
                        TransparentFromY.getText().equals("") ||
                        TransparentToX.getText().equals("") ||
                        TransparentToY.getText().equals("") ||
                        TransparentLevel.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Wrong Input.Not None.", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (selectedValue == -1) {
                        gradientTransparentController.setSelectedIndex(layerContent.getLayerSize() - 1);
                        int fromX = Integer.parseInt(TransparentFromX.getText());
                        int fromY = Integer.parseInt(TransparentFromY.getText());
                        int toX = Integer.parseInt(TransparentToX.getText());
                        int toY = Integer.parseInt(TransparentToY.getText());
                        double level = Double.parseDouble(TransparentLevel.getText());
                        if (fromX < 0 || fromY < 0 || toX < 0 || toY < 0 || level <= 0) {
                            JOptionPane.showMessageDialog(null, "Wrong Input.All positive.", "Error", JOptionPane.WARNING_MESSAGE);
                        } else {
                            BufferedImage bufferedImage = gradientTransparentController.getPreview(fromX, fromY, toX,
                                    toY, TransparentKind.getSelectedIndex(), level);
                            TransparentLabel.setVisible(false);
                            TransparentLabel.setIcon(new ImageIcon(bufferedImage));
                            TransparentLabel.setText("");
                            TransparentLabel.setBounds(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
                            TransparentLabel.setVisible(true);
                            System.out.println("[log]Paint Transparent Gradient Preview");
                        }
                    } else {
                        gradientTransparentController.setSelectedIndex(selectedValue);
                        int fromX = Integer.parseInt(TransparentFromX.getText());
                        int fromY = Integer.parseInt(TransparentFromY.getText());
                        int toX = Integer.parseInt(TransparentToX.getText());
                        int toY = Integer.parseInt(TransparentToY.getText());
                        double level = Double.parseDouble(TransparentLevel.getText());
                        if (fromX < 0 || fromY < 0 || toX < 0 || toY < 0 || level <= 0) {
                            JOptionPane.showMessageDialog(null, "Wrong Input.All positive.", "Error", JOptionPane.WARNING_MESSAGE);
                        } else {
                            BufferedImage bufferedImage = gradientTransparentController.getPreview(fromX, fromY, toX,
                                    toY, TransparentKind.getSelectedIndex(), level);
                            TransparentLabel.setVisible(false);
                            TransparentLabel.setIcon(new ImageIcon(bufferedImage));
                            TransparentLabel.setText("");
                            TransparentLabel.setBounds(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
                            TransparentLabel.setVisible(true);
                            System.out.println("[log]Paint Transparent Gradient Preview");
                        }
                    }
                }
            }
        });
        TransparentYesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (TransparentFromX.getText().equals("") ||
                        TransparentFromY.getText().equals("") ||
                        TransparentToX.getText().equals("") ||
                        TransparentToY.getText().equals("") ||
                        TransparentLevel.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Wrong Input.Not None.", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (selectedValue == -1) {
                        gradientTransparentController.setSelectedIndex(layerContent.getLayerSize() - 1);
                        int fromX = Integer.parseInt(TransparentFromX.getText());
                        int fromY = Integer.parseInt(TransparentFromY.getText());
                        int toX = Integer.parseInt(TransparentToX.getText());
                        int toY = Integer.parseInt(TransparentToY.getText());
                        double level = Double.parseDouble(TransparentLevel.getText());
                        if (fromX < 0 || fromY < 0 || toX < 0 || toY < 0 || level <= 0) {
                            JOptionPane.showMessageDialog(null, "Wrong Input.All positive.", "Error", JOptionPane.WARNING_MESSAGE);
                        } else {
                            gradientTransparentController.update(fromX, fromY, toX,
                                    toY, TransparentKind.getSelectedIndex(), level);
                            mainWindow.getTopLayerShowController().update();
                            System.out.println("[log]Paint Transparent Gradient to Layer");
                            gradientClose.close();
                        }
                    } else {
                        gradientTransparentController.setSelectedIndex(selectedValue);
                        int fromX = Integer.parseInt(TransparentFromX.getText());
                        int fromY = Integer.parseInt(TransparentFromY.getText());
                        int toX = Integer.parseInt(TransparentToX.getText());
                        int toY = Integer.parseInt(TransparentToY.getText());
                        double level = Double.parseDouble(TransparentLevel.getText());
                        if (fromX < 0 || fromY < 0 || toX < 0 || toY < 0 || level <= 0) {
                            JOptionPane.showMessageDialog(null, "Wrong Input.All positive.", "Error", JOptionPane.WARNING_MESSAGE);
                        } else {
                            gradientTransparentController.update(fromX, fromY, toX,
                                    toY, TransparentKind.getSelectedIndex(), level);
                            mainWindow.getTopLayerShowController().update();
                            System.out.println("[log]Paint Transparent Gradient to Layer");
                            gradientClose.close();
                        }
                    }
                }
            }
        });

    }

    public JPanel getPanel1() {
        return this.panel1;
    }

    public GradientParam setTable(int index) {
        tabbedPane1.setSelectedIndex(index);
        return this;
    }

    public GradientParam setLayerContent(LayerContent layerContent) {
        this.layerContent = layerContent;
        this.gradientLightingController.setLayerContent(layerContent);
        this.gradientTransparentController.setLayerContent(layerContent);
        return this;
    }

    public GradientParam setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        return this;
    }

    public GradientParam setGradientClose(GradientClose gradientClose) {
        this.gradientClose = gradientClose;
        return this;
    }

    public GradientParam setSelectedValue(int selectedValue) {
        this.selectedValue = selectedValue;
        return this;
    }

    public GradientParam updateImgInfo() {
        if (selectedValue == -1) {
            int width = this.gradientTransparentController
                    .setSelectedIndex(this.layerContent.getLayerSize() - 1).getImgWidth();
            int height = this.gradientTransparentController
                    .setSelectedIndex(this.layerContent.getLayerSize() - 1).getImgHeight();
            this.TransparentImgWidth.setText("" + width);
            this.TransparentImgHeight.setText("" + height);
            this.LightingImgWidth.setText("" + width);
            this.LightingImgHeight.setText("" + height);
        } else {
            int width = this.gradientTransparentController
                    .setSelectedIndex(selectedValue).getImgWidth();
            int height = this.gradientTransparentController
                    .setSelectedIndex(selectedValue).getImgHeight();
            this.TransparentImgWidth.setText("" + width);
            this.TransparentImgHeight.setText("" + height);
            this.LightingImgWidth.setText("" + width);
            this.LightingImgHeight.setText("" + height);
        }
        return this;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1 = new JTabbedPane();
        panel1.add(tabbedPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 5, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Lighting", panel2);
        final JLabel label1 = new JLabel();
        label1.setText("From(x):");
        panel2.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("From(y):");
        panel2.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("To(x):");
        panel2.add(label3, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("To(y):");
        panel2.add(label4, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LightingFromX = new JTextField();
        panel2.add(LightingFromX, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        LightingFromY = new JTextField();
        panel2.add(LightingFromY, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        LightingToY = new JTextField();
        panel2.add(LightingToY, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        LightingToX = new JTextField();
        panel2.add(LightingToX, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        LightingKind = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("LightToDark");
        defaultComboBoxModel1.addElement("DarkToLight");
        LightingKind.setModel(defaultComboBoxModel1);
        panel2.add(LightingKind, new com.intellij.uiDesigner.core.GridConstraints(3, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        LightingPreviewButton = new JButton();
        LightingPreviewButton.setText("Preview");
        panel2.add(LightingPreviewButton, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LightingYesButton = new JButton();
        LightingYesButton.setText("Yes");
        panel2.add(LightingYesButton, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LightingNoButton = new JButton();
        LightingNoButton.setText("No");
        panel2.add(LightingNoButton, new com.intellij.uiDesigner.core.GridConstraints(5, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel3.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        LightingLabel = new JLabel();
        LightingLabel.setText("");
        scrollPane1.setViewportView(LightingLabel);
        final JLabel label5 = new JLabel();
        label5.setText("ImageWidth(x):");
        panel2.add(label5, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LightingImgWidth = new JTextField();
        panel2.add(LightingImgWidth, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("ImageHeight(y):");
        panel2.add(label6, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LightingImgHeight = new JTextField();
        panel2.add(LightingImgHeight, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Kind");
        panel2.add(label7, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LightingLevel = new JTextField();
        panel2.add(LightingLevel, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Level");
        panel2.add(label8, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 5, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Transparent", panel4);
        final JLabel label9 = new JLabel();
        label9.setText("From(x):");
        panel4.add(label9, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("From(y):");
        panel4.add(label10, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("To(x):");
        panel4.add(label11, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setText("To(y):");
        panel4.add(label12, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TransparentFromY = new JTextField();
        panel4.add(TransparentFromY, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        TransparentToY = new JTextField();
        panel4.add(TransparentToY, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        TransparentToX = new JTextField();
        panel4.add(TransparentToX, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        TransparentKind = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("TransToOpq");
        defaultComboBoxModel2.addElement("OpqToTrans");
        TransparentKind.setModel(defaultComboBoxModel2);
        panel4.add(TransparentKind, new com.intellij.uiDesigner.core.GridConstraints(3, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        TransparentPreviewButton = new JButton();
        TransparentPreviewButton.setText("Preview");
        panel4.add(TransparentPreviewButton, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TransparentYesButton = new JButton();
        TransparentYesButton.setText("Yes");
        panel4.add(TransparentYesButton, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TransparentNoButton = new JButton();
        TransparentNoButton.setText("No");
        panel4.add(TransparentNoButton, new com.intellij.uiDesigner.core.GridConstraints(5, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(panel5, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        panel5.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        TransparentLabel = new JLabel();
        TransparentLabel.setText("");
        scrollPane2.setViewportView(TransparentLabel);
        final JLabel label13 = new JLabel();
        label13.setText("ImageWidth(x):");
        panel4.add(label13, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TransparentImgWidth = new JTextField();
        panel4.add(TransparentImgWidth, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label14 = new JLabel();
        label14.setText("ImageHeight(y):");
        panel4.add(label14, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TransparentImgHeight = new JTextField();
        panel4.add(TransparentImgHeight, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        TransparentFromX = new JTextField();
        panel4.add(TransparentFromX, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label15 = new JLabel();
        label15.setText("Kind");
        panel4.add(label15, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label16 = new JLabel();
        label16.setText("Level");
        panel4.add(label16, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TransparentLevel = new JTextField();
        panel4.add(TransparentLevel, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
