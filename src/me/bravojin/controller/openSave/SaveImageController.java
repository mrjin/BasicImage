package me.bravojin.controller.openSave;

import me.bravojin.LayerContent.LayerContent;
import me.bravojin.views.mainwindow.MainWindow;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by tyrionlanister on 15-6-13.
 */
public class SaveImageController {
    private MainWindow mainWindow;
    private LayerContent layerContent;
    public SaveImageController(LayerContent layerContent, MainWindow mainWindow) {
        this.layerContent = layerContent;
        this.mainWindow = mainWindow;
    }

    public SaveImageController saveToFile(File file, String fileType) {
        if(this.layerContent.getTop() == null) {
            System.out.println("[info]No Top Image");
        }
        else {
            try {
                String f = fileType.substring(1,fileType.length());
                System.out.print(f);
                if(f.toLowerCase().equals("png")) {
                    ImageIO.write(this.layerContent.getTop(), "png", new FileOutputStream(file.getAbsoluteFile()));
                    System.out.println("[debug]Save Type PNG");
                }
                else if(f.toLowerCase().equals("jpg")) {
                    ImageIO.write(this.layerContent.getTop(), "jpg", new FileOutputStream(file.getAbsoluteFile()));
                    System.out.println("[debug]Save Type JPG");
                }
                else if(f.toLowerCase().equals("jpeg")) {
                    ImageIO.write(this.layerContent.getTop(), "jpeg", new FileOutputStream(file.getAbsoluteFile()));
                    System.out.println("[debug]Save Type JPEG");
                }
                else if(f.toLowerCase().equals("bmp")) {
                    ImageIO.write(this.layerContent.getTop(), "bmp", new FileOutputStream(file.getAbsoluteFile()));
                    System.out.println("[debug]Save Type BMP");
                }
                else {
                    System.out.println("[debug]No File Saved.");
                }
            } catch (Exception e) {
                System.out.println("[Debug]Save Exception");
                e.printStackTrace();
            }
            System.out.println("[info]File Saved");
        }
        return this;
    }
}
