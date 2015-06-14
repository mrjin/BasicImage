package me.bravojin.views.mainwindow;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.controller.histogram.GenerateHistogramController;
import me.bravojin.controller.layers.CreateLayerController;
import me.bravojin.controller.layers.TopLayerShowController;
import me.bravojin.controller.layers.UpdateLayerController;
import me.bravojin.controller.openSave.OpenImageController;
import me.bravojin.controller.openSave.OpenImageSizeChangeController;
import me.bravojin.controller.openSave.SaveImageController;
import me.bravojin.controller.parameter.FilterInverseController;
import me.bravojin.controller.parameter.gradient.GradientClose;
import me.bravojin.controller.parameter.smooth.SmoothClose;
import me.bravojin.filter.basic.FilterInverseColor;
import me.bravojin.views.paramwindow.GradientParam;
import me.bravojin.views.paramwindow.SmoothParam;
import me.bravojin.views.util.CellRendererListChannels;
import me.bravojin.views.util.CellRendererListLayers;
import me.bravojin.views.util.MyFileFilter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Vector;

/**
 * Created by tyrionlanister on 15-6-10.
 */
public class MainWindow {
    private JTabbedPane HistogramChannel;
    private JButton openButton;
    private JButton closeButton;
    private JButton saveButton;
    private JButton createButton;
    private JButton deleteButton;
    private JPanel panelMain;
    private JPanel PanelHistogram;
    private JPanel PanelChannel;
    private JList ListChannels;
    private JButton undoButton;
    //private JList ListLayer;
    private JButton normalizeButton;
    private JPanel PanelToolbar;
    private JPanel PanelImg;
    private JPanel PanelSide;
    private JPanel PanelMainWIndow;
    private JComboBox comboBox1;
    private JLabel ImageLabel;
    private JButton generateButton;
    private JLabel histoLabel;
    private JButton lumaButton;
    private JPanel HistoPanel;
    private JButton mixedButton;
    private JButton redoButton;
    private JButton smoothButton;
    private JButton gradientButton;
    private JButton maskButton;
    private JButton filterButton;
    private JButton paramButton;
    private JList ListLayer;
    private JButton detailButton;
    private JButton gammaButton;
    private JButton generateButtonChannels;

    private JPopupMenu smoothPopMenu;
    private JPopupMenu gradientPopMenu;
    private JPopupMenu maskPopMenu;
    private JPopupMenu filterPopMenu;
    private JPopupMenu paramPopMenu;

    private JButton GuassSmoothButton;
    private JButton BoxSmoothButton;

    private JButton TransparentGradientButton;
    private JButton LightingGradientButton;

    private JButton InverseColorButton;

    private CreateLayerController createLayerController;
    private UpdateLayerController updateLayerController;
    private TopLayerShowController topLayerShowController;
    private GenerateHistogramController generateHistogramController;
    private OpenImageController openImageController;
    private OpenImageSizeChangeController openImageSizeChangeController;
    private SaveImageController saveImageController;
    private FilterInverseController filterInverseController;

    private long saveTime;
    private String saveFilename;
    private String saveFilefix;
    private String saveFilepath;

    private JFrame smoothParamWindow;
    private SmoothParam smoothParam;

    private JFrame gradientParamWindow;
    private GradientParam gradientParam;

    private DefaultMutableTreeNode root;

    public MainWindow() {
        LayerContent layerContent = new LayerContent();

        GuassSmoothButton = new JButton("Guass Smooth");
        BoxSmoothButton = new JButton("Box Smooth");

        TransparentGradientButton = new JButton("Transparent Gradient");
        LightingGradientButton = new JButton("Lighting Gradient");

        InverseColorButton = new JButton("Inverse Color");

        smoothParam = new SmoothParam();
        smoothParamWindow = new JFrame("Smooth Parameter");
        smoothParamWindow.setContentPane(smoothParam.getPanel1());
        smoothParamWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        smoothParamWindow.pack();
        smoothParam.setMainWindow(this).setLayerContent(layerContent).setSmoothClose(new SmoothClose(layerContent, this, smoothParamWindow));
        smoothParamWindow.setSize(new Dimension(500, 400));

        gradientParam = new GradientParam();
        gradientParamWindow = new JFrame("Gradient Parameter");
        gradientParamWindow.setContentPane(gradientParam.getPanel1());
        gradientParamWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gradientParamWindow.pack();
        gradientParamWindow.setSize(new Dimension(600, 500));
        gradientParam.setMainWindow(this).setLayerContent(layerContent).setGradientClose(new GradientClose(gradientParamWindow));

        this.ListLayer = new JList();

        $$$setupUI$$$();


        this.ListLayer.setVisible(true);
        Vector<String> a = new Vector<String>();
        a.add("Name");
        a.add("Sexual");
        this.ListLayer.setListData(a);
        this.ListChannels.setCellRenderer(new CellRendererListChannels());

        this.createLayerController = new CreateLayerController(layerContent, this);
        this.updateLayerController = new UpdateLayerController(layerContent, this);
        this.topLayerShowController = new TopLayerShowController(layerContent, this);
        this.generateHistogramController = new GenerateHistogramController(layerContent, this);
        this.openImageController = new OpenImageController(layerContent, this);
        this.openImageSizeChangeController = new OpenImageSizeChangeController(layerContent, this);
        this.saveImageController = new SaveImageController(layerContent, this);
        this.filterInverseController = new FilterInverseController(layerContent, this);

        smoothPopMenu = new JPopupMenu();
        smoothPopMenu.add(this.GuassSmoothButton);
        smoothPopMenu.add(this.BoxSmoothButton);
        gradientPopMenu = new JPopupMenu();
        gradientPopMenu.add(this.TransparentGradientButton);
        gradientPopMenu.add(this.LightingGradientButton);
        maskPopMenu = new JPopupMenu();
        filterPopMenu = new JPopupMenu();
        filterPopMenu.add(this.InverseColorButton);
        paramPopMenu = new JPopupMenu();

        saveTime = System.currentTimeMillis();
//        Vector<BufferedImage> bufferedImages = new Vector<BufferedImage>();
//        bufferedImages.add(testImg1);
//        bufferedImages.add(testImg2);
//        this.paintChannel(bufferedImages);

        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("[log]Create a new Layer");
                createLayerController.createLayer();
                updateLayerController.update();
                topLayerShowController.update();
            }
        });

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                generateHistogramController.update();
                System.out.println("[log]Receive Histogram");
            }
        });

        lumaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                generateHistogramController.updateLuma();
                System.out.println("[log]Receive Histogram Luma");
            }
        });
        smoothButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                smoothPopMenu.show(smoothButton, smoothButton.getX(), smoothButton.getY() + smoothButton.getHeight());
            }
        });
        gradientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gradientPopMenu.show(gradientButton, gradientButton.getX() - gradientButton.getWidth(), gradientButton.getY() + gradientButton.getHeight());
            }
        });
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                filterPopMenu.show(filterButton, filterButton.getX() - 3 * filterButton.getWidth(), filterButton.getY() + filterButton.getHeight());
            }
        });
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.setMultiSelectionEnabled(false);
                MyFileFilter jpg = new MyFileFilter(".jpg", "JPEG Image(.jpg)");
                MyFileFilter jpeg = new MyFileFilter(".jpeg", "JPEG Image(.jpeg)");
                MyFileFilter bmp = new MyFileFilter(".bmp", "Bit Map Image(.bmp)");
                MyFileFilter png = new MyFileFilter(".png", "Portable Graphics Format(.png)");
                chooser.addChoosableFileFilter(jpg);
                chooser.addChoosableFileFilter(jpeg);
                chooser.addChoosableFileFilter(bmp);
                chooser.addChoosableFileFilter(png);

                if (chooser.showDialog(new JFrame(), "Open Image") == JFileChooser.APPROVE_OPTION) {
                    File f = chooser.getSelectedFile();
                    BufferedImage openImg = null;
                    try {
                        openImg = ImageIO.read(new FileInputStream(f));
                    } catch (Exception e) {
                        System.out.println("[debug]Open Image Exception.");
                        e.printStackTrace();
                    }
                    System.out.println("[info]Open File");
                    openImageController.addImage(openImg);
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (System.currentTimeMillis() - saveTime > 1000 * 60) {
                    int optionClose = JOptionPane.showConfirmDialog(null, "Closing without saving?",
                            "Closing", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (optionClose == 0) {
                        System.out.println("[info]Close without Saving");
                        System.exit(0);
                    } else if (optionClose == 1) {
                        System.out.println("[info]Close Saved");
                    }
                } else {
                    System.out.println("[info]Close");
                    System.exit(0);
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogType(JFileChooser.SAVE_DIALOG);
                chooser.setMultiSelectionEnabled(false);
                MyFileFilter jpg = new MyFileFilter(".jpg", "JPEG Image(.jpg)");
                MyFileFilter jpeg = new MyFileFilter(".jpeg", "JPEG Image(.jpeg)");
                MyFileFilter bmp = new MyFileFilter(".bmp", "Bit Map Image(.bmp)");
                MyFileFilter png = new MyFileFilter(".png", "Portable Graphics Format(.png)");
                chooser.addChoosableFileFilter(jpg);
                chooser.addChoosableFileFilter(jpeg);
                chooser.addChoosableFileFilter(bmp);
                chooser.addChoosableFileFilter(png);

                int index = chooser.showDialog(null, "Save Image");
                if (index == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    MyFileFilter filter = (MyFileFilter) chooser.getFileFilter();
                    saveFilefix = filter.getEnds();
                    File newFile = null;
                    if (file.getAbsolutePath().toLowerCase().endsWith(saveFilefix.toLowerCase())) {
                        newFile = file;
                        saveFilename = file.getAbsolutePath().toLowerCase();
                    } else {
                        newFile = new File(file.getAbsoluteFile() + saveFilefix);
                        saveFilename = file.getAbsolutePath() + saveFilefix;
                    }
                    saveImageController.saveToFile(newFile, saveFilefix);
                }
            }
        });

        BoxSmoothButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                smoothParam.setTable(0).setSelectedValue(getLayerListSelectedIndex());
                System.out.println("[log]Box Smooth Open");
                smoothParamWindow.setVisible(true);
            }
        });

        GuassSmoothButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                smoothParam.setTable(1).setSelectedValue(getLayerListSelectedIndex());
                System.out.println("[log]Guass Box Open");
                smoothParamWindow.setVisible(true);
            }
        });

        TransparentGradientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gradientParam.setTable(1).setSelectedValue(getLayerListSelectedIndex()).updateImgInfo();
                System.out.println("[log]Transparent Gradient Open");
                gradientParamWindow.setVisible(true);
            }
        });

        LightingGradientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gradientParam.setTable(0).setSelectedValue(getLayerListSelectedIndex()).updateImgInfo();
                System.out.println("[log]Lighting Gradient Open");
                gradientParamWindow.setVisible(true);
            }
        });

        InverseColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                filterInverseController.setSelectedIndex(getLayerListSelectedIndex()).update();
                topLayerShowController.update();
                System.out.println("[log]Inverse Color Filter to Layer");
            }
        });
    }

//    @Override
//    public void actionPerformed(ActionEvent actionEvent) {
//
//    }

    private int getLayerListSelectedIndex() {
        if (ListLayer.isSelectionEmpty()) {
            return -1;
        } else {
            return ListLayer.getSelectedIndex();
        }
    }

    public static void main(String[] args) {


        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().PanelMainWIndow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public JList getListLayer() {
        return this.ListLayer;
    }

    public int getHistoPanelWidth() {
        return this.histoLabel.getWidth();
    }

    public TopLayerShowController getTopLayerShowController() {
        return this.topLayerShowController;
    }

    public UpdateLayerController getUpdateLayerController() {
        return this.updateLayerController;
    }

    public CreateLayerController getCreateLayerController() {
        return this.createLayerController;
    }

    public OpenImageSizeChangeController getOpenImageSizeChangeController() {
        return this.openImageSizeChangeController;
    }

    public void paintTopLayer(BufferedImage bufferedImage) {
        //((JPanelMain) this.panelMain).setImage(bufferedImage);
        this.ImageLabel.setVisible(false);
        this.ImageLabel.setIcon(new ImageIcon(bufferedImage));
        this.ImageLabel.setText("");
        this.ImageLabel.setBounds(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        this.ImageLabel.setVisible(true);
        System.out.println("[log]Paint Top Layer");
    }

    public void paintHistogram(BufferedImage bufferedImage) {
        this.histoLabel.setVisible(false);
        this.histoLabel.setIcon(new ImageIcon(bufferedImage));
        this.histoLabel.setText("");
        this.histoLabel.setBounds(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        this.histoLabel.setVisible(true);
        System.out.println("[log]Paint Histogram");
    }

    public void paintChannel(Vector<BufferedImage> bufferedImageVector) {
        if (bufferedImageVector.size() != 0) {
            this.ListChannels.setListData(bufferedImageVector);
        }
    }

    public void cleanRoot() {
        this.root.removeAllChildren();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        //this.panelMain = new JPanelMain();

    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        PanelMainWIndow = new JPanel();
        PanelMainWIndow.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 20, new Insets(0, 0, 0, 0), -1, -1));
        PanelToolbar = new JPanel();
        PanelToolbar.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        PanelMainWIndow.add(PanelToolbar, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 2, 20, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        PanelToolbar.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JToolBar toolBar1 = new JToolBar();
        PanelToolbar.add(toolBar1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        openButton = new JButton();
        openButton.setText("Open");
        toolBar1.add(openButton);
        final JToolBar.Separator toolBar$Separator1 = new JToolBar.Separator();
        toolBar1.add(toolBar$Separator1);
        closeButton = new JButton();
        closeButton.setText("Close");
        toolBar1.add(closeButton);
        final JToolBar.Separator toolBar$Separator2 = new JToolBar.Separator();
        toolBar1.add(toolBar$Separator2);
        saveButton = new JButton();
        saveButton.setText("Save");
        toolBar1.add(saveButton);
        final JToolBar.Separator toolBar$Separator3 = new JToolBar.Separator();
        toolBar1.add(toolBar$Separator3);
        undoButton = new JButton();
        undoButton.setText("Undo");
        toolBar1.add(undoButton);
        final JToolBar.Separator toolBar$Separator4 = new JToolBar.Separator();
        toolBar1.add(toolBar$Separator4);
        redoButton = new JButton();
        redoButton.setText("Redo");
        toolBar1.add(redoButton);
        final JToolBar toolBar2 = new JToolBar();
        PanelToolbar.add(toolBar2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        smoothButton = new JButton();
        smoothButton.setText("Smooth");
        toolBar2.add(smoothButton);
        final JToolBar.Separator toolBar$Separator5 = new JToolBar.Separator();
        toolBar2.add(toolBar$Separator5);
        gradientButton = new JButton();
        gradientButton.setText("Gradient");
        toolBar2.add(gradientButton);
        final JToolBar.Separator toolBar$Separator6 = new JToolBar.Separator();
        toolBar2.add(toolBar$Separator6);
        maskButton = new JButton();
        maskButton.setText("Mask");
        toolBar2.add(maskButton);
        final JToolBar.Separator toolBar$Separator7 = new JToolBar.Separator();
        toolBar2.add(toolBar$Separator7);
        filterButton = new JButton();
        filterButton.setText("Filter");
        toolBar2.add(filterButton);
        final JToolBar.Separator toolBar$Separator8 = new JToolBar.Separator();
        toolBar2.add(toolBar$Separator8);
        paramButton = new JButton();
        paramButton.setText("Param");
        toolBar2.add(paramButton);
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        PanelMainWIndow.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(3, 18, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        PanelImg = new JPanel();
        PanelImg.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        PanelMainWIndow.add(PanelImg, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 18, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        PanelImg.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        panelMain = new JPanel();
        panelMain.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelImg.add(panelMain, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panelMain.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        scrollPane1.setViewportView(panel1);
        ImageLabel = new JLabel();
        ImageLabel.setText("");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(ImageLabel, gbc);
        PanelSide = new JPanel();
        PanelSide.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelMainWIndow.add(PanelSide, new com.intellij.uiDesigner.core.GridConstraints(3, 19, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        PanelSide.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(300, 300), new Dimension(400, 400), 1, false));
        HistoPanel = new JPanel();
        HistoPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(HistoPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(300, 300), new Dimension(400, 400), 0, false));
        HistogramChannel = new JTabbedPane();
        HistoPanel.add(HistogramChannel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(300, 300), null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        HistogramChannel.addTab("Histogram", panel4);
        PanelHistogram = new JPanel();
        PanelHistogram.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(PanelHistogram, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelHistogram.add(panel5, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JToolBar toolBar3 = new JToolBar();
        panel5.add(toolBar3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        normalizeButton = new JButton();
        normalizeButton.setText("Normalize");
        toolBar3.add(normalizeButton);
        final JToolBar.Separator toolBar$Separator9 = new JToolBar.Separator();
        toolBar3.add(toolBar$Separator9);
        gammaButton = new JButton();
        gammaButton.setText("Gamma");
        toolBar3.add(gammaButton);
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        PanelHistogram.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelHistogram.add(panel6, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        histoLabel = new JLabel();
        histoLabel.setText("");
        panel6.add(histoLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JToolBar toolBar4 = new JToolBar();
        PanelHistogram.add(toolBar4, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        generateButton = new JButton();
        generateButton.setText("Generate");
        toolBar4.add(generateButton);
        final JToolBar.Separator toolBar$Separator10 = new JToolBar.Separator();
        toolBar4.add(toolBar$Separator10);
        lumaButton = new JButton();
        lumaButton.setText("Luma");
        toolBar4.add(lumaButton);
        final JToolBar.Separator toolBar$Separator11 = new JToolBar.Separator();
        toolBar4.add(toolBar$Separator11);
        mixedButton = new JButton();
        mixedButton.setText("Mixed");
        toolBar4.add(mixedButton);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        HistogramChannel.addTab("Channels", panel7);
        PanelChannel = new JPanel();
        PanelChannel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel7.add(PanelChannel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        PanelChannel.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        ListChannels = new JList();
        scrollPane2.setViewportView(ListChannels);
        final JToolBar toolBar5 = new JToolBar();
        PanelChannel.add(toolBar5, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        generateButtonChannels = new JButton();
        generateButtonChannels.setText("Generate");
        toolBar5.add(generateButtonChannels);
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel8, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(400, 400), new Dimension(400, 400), 1, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        panel8.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel8.add(panel9, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane3 = new JScrollPane();
        panel9.add(scrollPane3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        ListLayer.setModel(defaultListModel1);
        ListLayer.setSelectionMode(0);
        scrollPane3.setViewportView(ListLayer);
        final JToolBar toolBar6 = new JToolBar();
        panel8.add(toolBar6, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        createButton = new JButton();
        createButton.setText("Create");
        toolBar6.add(createButton);
        final JToolBar.Separator toolBar$Separator12 = new JToolBar.Separator();
        toolBar6.add(toolBar$Separator12);
        deleteButton = new JButton();
        deleteButton.setText("Delete");
        toolBar6.add(deleteButton);
        final JToolBar.Separator toolBar$Separator13 = new JToolBar.Separator();
        toolBar6.add(toolBar$Separator13);
        detailButton = new JButton();
        detailButton.setText("Detail");
        toolBar6.add(detailButton);
        comboBox1 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        comboBox1.setModel(defaultComboBoxModel1);
        toolBar6.add(comboBox1);
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer8 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer8, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer9 = new com.intellij.uiDesigner.core.Spacer();
        PanelMainWIndow.add(spacer9, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 18, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return PanelMainWIndow;
    }
}
