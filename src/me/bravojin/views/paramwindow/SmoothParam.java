package me.bravojin.views.paramwindow;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.controller.parameter.smooth.BoxSmoothController;
import me.bravojin.controller.parameter.smooth.GuassSmoothController;
import me.bravojin.controller.parameter.smooth.SmoothClose;
import me.bravojin.views.mainwindow.MainWindow;
import me.bravojin.views.util.CutImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-6-13.
 */
public class SmoothParam {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField boxSmoothKernelWidth;
    private JTextField boxSmoothKernelHeight;
    private JButton boxSmoothNoButton;
    private JButton boxSmoothYesButton;
    private JLabel boxSmoothLabel;
    private JTextField guassSmoothSigma;
    private JTextField guassSmoothParameter;
    private JButton guassSmoothNoButton;
    private JButton guassSmoothYesButton;
    private JLabel guassSmoothLabel;
    private JButton guassSmoothPreviewButton;
    private JButton boxSmoothPreviewButton;

    private BoxSmoothController boxSmoothController;
    private GuassSmoothController guassSmoothController;
    private SmoothClose smoothClose;
    private MainWindow mainWindow;
    private LayerContent layerContent;

    private int selectedValue;

    public JPanel getPanel1() {
        return this.panel1;
    }

    public SmoothParam setTable(int index) {
        tabbedPane1.setSelectedIndex(index);
        return this;
    }

    public SmoothParam setLayerContent(LayerContent layerContent) {
        this.layerContent = layerContent;
        this.boxSmoothController.setLayerContent(layerContent);
        this.guassSmoothController.setLayerContent(layerContent);
        return this;
    }

    public SmoothParam setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        return this;
    }

    public SmoothParam setSmoothClose(SmoothClose smoothClose) {
        this.smoothClose = smoothClose;
        return this;
    }

    public SmoothParam() {

        boxSmoothController = new BoxSmoothController(layerContent, mainWindow, this);
        guassSmoothController = new GuassSmoothController(layerContent, mainWindow, this);

        boxSmoothKernelHeight.setText("3");
        boxSmoothKernelWidth.setText("3");
        guassSmoothParameter.setText("3");

        boxSmoothYesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (boxSmoothKernelWidth.getText() == "" || boxSmoothKernelHeight.getText() == "") {
                    JOptionPane.showMessageDialog(null, "You must input a number in both text places.", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    int kernelWidth = Integer.parseInt(boxSmoothKernelWidth.getText());
                    int kernelHeight = Integer.parseInt(boxSmoothKernelHeight.getText());
                    if (kernelWidth <= 0 || kernelHeight <= 0) {
                        JOptionPane.showMessageDialog(null, "You must input a positive integer.", "Error", JOptionPane.WARNING_MESSAGE);
                    } else {
                        boxSmoothController.update(kernelWidth, kernelHeight, selectedValue);
                        mainWindow.getTopLayerShowController().update();
                        smoothClose.close();
                    }
                }
            }
        });

        boxSmoothPreviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (boxSmoothKernelWidth.getText() == "" || boxSmoothKernelHeight.getText() == "") {
                    JOptionPane.showMessageDialog(null, "You must input a number in both text places.", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    int kernelWidth = Integer.parseInt(boxSmoothKernelWidth.getText());
                    int kernelHeight = Integer.parseInt(boxSmoothKernelHeight.getText());
                    if (kernelWidth <= 0 || kernelHeight <= 0) {
                        JOptionPane.showMessageDialog(null, "You must input a positive integer.", "Error", JOptionPane.WARNING_MESSAGE);
                    } else {
                        BufferedImage bufferedImage = boxSmoothController.getPreview(kernelWidth, kernelHeight, selectedValue);
                        if (bufferedImage != null) {
                            boxSmoothLabel.setVisible(false);
                            if (bufferedImage.getWidth() >= 200 && bufferedImage.getHeight() >= 200) {
                                boxSmoothLabel.setIcon(new ImageIcon(CutImage.cutImage(200, 200, bufferedImage)));
                            } else if (bufferedImage.getWidth() >= 100 && bufferedImage.getHeight() >= 100) {
                                boxSmoothLabel.setIcon(new ImageIcon(CutImage.cutImage(100, 100, bufferedImage)));
                            } else {
                                boxSmoothLabel.setIcon(new ImageIcon(bufferedImage));
                            }
                            boxSmoothLabel.setText("");
                            boxSmoothLabel.setBounds(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
                            boxSmoothLabel.setVisible(true);
                            System.out.println("[log]Paint Box Smooth Preview");
                        } else {
                            System.out.println("[log]No Image to Paint for Box Smooth");
                        }
                    }
                }
            }
        });

        ActionListener closeAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                smoothClose.close();
            }
        };

        boxSmoothNoButton.addActionListener(closeAction);
        guassSmoothNoButton.addActionListener(closeAction);

        guassSmoothYesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (guassSmoothSigma.getText() == "" || guassSmoothParameter.getText() == "") {
                    JOptionPane.showMessageDialog(null, "You must input a number in both text places.", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    double sigma = Double.parseDouble(guassSmoothSigma.getText());
                    int param = Integer.parseInt(guassSmoothParameter.getText());
                    if (sigma <= 0 || param <= 0) {
                        JOptionPane.showMessageDialog(null, "You must input a positive integer.", "Error", JOptionPane.WARNING_MESSAGE);
                    } else {
                        guassSmoothController.update(sigma, param, selectedValue);
                        mainWindow.getTopLayerShowController().update();
                        smoothClose.close();
                    }
                }
            }
        });

        guassSmoothPreviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (guassSmoothSigma.getText() == "" || guassSmoothParameter.getText() == "") {
                    JOptionPane.showMessageDialog(null, "You must input a number in both text places.", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    double sigma = Double.parseDouble(guassSmoothSigma.getText());
                    int param = Integer.parseInt(guassSmoothParameter.getText());
                    if (sigma <= 0 || param <= 0) {
                        JOptionPane.showMessageDialog(null, "You must input a positive integer.", "Error", JOptionPane.WARNING_MESSAGE);
                    } else {
                        BufferedImage bufferedImage = guassSmoothController.getPreview(sigma, param, selectedValue);
                        if (bufferedImage != null) {
                            guassSmoothLabel.setVisible(false);
                            if (bufferedImage.getWidth() >= 200 && bufferedImage.getHeight() >= 200) {
                                guassSmoothLabel.setIcon(new ImageIcon(CutImage.cutImage(200, 200, bufferedImage)));
                            } else if (bufferedImage.getWidth() >= 100 && bufferedImage.getHeight() >= 100) {
                                guassSmoothLabel.setIcon(new ImageIcon(CutImage.cutImage(100, 100, bufferedImage)));
                            } else {
                                guassSmoothLabel.setIcon(new ImageIcon(bufferedImage));
                            }
                            guassSmoothLabel.setText("");
                            guassSmoothLabel.setBounds(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
                            guassSmoothLabel.setVisible(true);
                            System.out.println("[log]Paint Guass Smooth Preview");
                        } else {
                            System.out.println("[log]No Image to Paint for Guass Smooth");
                        }
                    }
                }
            }
        });
    }

    public SmoothParam setSelectedValue(int selectedValue) {
        this.selectedValue = selectedValue;
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
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Box Smooth", panel2);
        final JLabel label1 = new JLabel();
        label1.setText("Kernel Width");
        panel2.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Kernel Height");
        panel2.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boxSmoothKernelWidth = new JTextField();
        panel2.add(boxSmoothKernelWidth, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        boxSmoothKernelHeight = new JTextField();
        panel2.add(boxSmoothKernelHeight, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        boxSmoothNoButton = new JButton();
        boxSmoothNoButton.setText("No");
        panel2.add(boxSmoothNoButton, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boxSmoothYesButton = new JButton();
        boxSmoothYesButton.setText("Yes");
        panel2.add(boxSmoothYesButton, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel3.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        boxSmoothLabel = new JLabel();
        boxSmoothLabel.setText("");
        scrollPane1.setViewportView(boxSmoothLabel);
        boxSmoothPreviewButton = new JButton();
        boxSmoothPreviewButton.setText("Preview");
        panel2.add(boxSmoothPreviewButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 5, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Guass Smooth", panel4);
        final JLabel label3 = new JLabel();
        label3.setText("Sigma");
        panel4.add(label3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Parameter");
        panel4.add(label4, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guassSmoothSigma = new JTextField();
        panel4.add(guassSmoothSigma, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        guassSmoothParameter = new JTextField();
        panel4.add(guassSmoothParameter, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        guassSmoothNoButton = new JButton();
        guassSmoothNoButton.setText("No");
        panel4.add(guassSmoothNoButton, new com.intellij.uiDesigner.core.GridConstraints(3, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guassSmoothYesButton = new JButton();
        guassSmoothYesButton.setText("Yes");
        panel4.add(guassSmoothYesButton, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(panel5, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        panel5.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        guassSmoothLabel = new JLabel();
        guassSmoothLabel.setText("");
        scrollPane2.setViewportView(guassSmoothLabel);
        guassSmoothPreviewButton = new JButton();
        guassSmoothPreviewButton.setText("Preview");
        panel4.add(guassSmoothPreviewButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
