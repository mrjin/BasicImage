package me.bravojin.histogram.createHistogram.DrawGraph;

import me.bravojin.util.Array;
import me.bravojin.util.RGB;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by tyrionlanister on 15-5-28.
 */
public class HistogramDataDrawGraph {

    public static BufferedImage draw(int [] R, int height, Color color, Color backgourndColor,
                                      int paramForHeight) {
        BufferedImage returnImg = new BufferedImage(R.length, height, BufferedImage.TYPE_4BYTE_ABGR);
        int imgWidth = R.length;
        int imgHeight = height;
        int [] Rtemp = R;
        int secondRtemp = Array.secondInt(R);
        for(int x = 0 ; x < imgWidth ; x++) {
            if(secondRtemp != 0) {
                Rtemp[x] = R[x]*height/paramForHeight/secondRtemp;
            }
            else {
                int maxRtemp = Array.maxInt(R);
                Rtemp[x] = R[x]*height/paramForHeight/maxRtemp;
            }

        }
        for(int x = 0 ; x < imgWidth ; x++) {
            for(int y = 0; y < imgHeight ; y++) {
                if(y < Rtemp[x]) {
                    returnImg.setRGB(x, imgHeight - 1 - y, color.getRGB());
                }
                else {
                    returnImg.setRGB(x, imgHeight - 1 - y, backgourndColor.getRGB());
                }
            }
        }
        return returnImg;
    }
    public static BufferedImage drawRGB(int [] R, int [] G, int [] B, int height, Color backgourndColor,
                                        double paramForHeightR, double paramForHeightG, double paramForHeightB) {
        BufferedImage returnImg = new BufferedImage(R.length, height, BufferedImage.TYPE_4BYTE_ABGR);
        int imgWidth = R.length;
        int imgHeight = height;
        int [] Rtemp = R;
        int [] Gtemp = G;
        int [] Btemp = B;
        int secondRtemp = Array.secondInt(R);
        int secondGtemp = Array.secondInt(G);
        int secondBtemp = Array.secondInt(B);
        for(int x = 0 ; x < imgWidth ; x++) {
            Rtemp[x] = (int)(R[x]*height/paramForHeightR/secondRtemp);
            Gtemp[x] = (int)(G[x]*height/paramForHeightG/secondGtemp);
            Btemp[x] = (int)(B[x]*height/paramForHeightB/secondBtemp);
        }
        for(int x = 0 ; x < imgWidth ; x++) {
            String order = RGB.getOrder(Rtemp[x], Gtemp[x], Btemp[x]);
            for(int y = 0; y < imgHeight ; y++) {
                if(order.charAt(0) == 'R') {
                    if(y < Rtemp[x]) {
                        returnImg.setRGB(x, height - y - 1, (new Color(255,0,0)).getRGB());
                        if(order.charAt(1) == 'G') {
                            if(y < Gtemp[x]) {
                                returnImg.setRGB(x, height - y - 1, (new Color(0, 255, 0)).getRGB());
                                if(y < Btemp[x]) {
                                    returnImg.setRGB(x, height - y - 1, (new Color(0, 0 ,255)).getRGB());
                                }
                            }
                        }
                        else {
                            if(y < Btemp[x]) {
                                returnImg.setRGB(x, height - y - 1, (new Color(0, 0, 255).getRGB()));
                                if(y < Gtemp[x]) {
                                    returnImg.setRGB(x, height - y - 1, (new Color(0, 255, 0)).getRGB());
                                }
                            }
                        }
                    }
                    else {
                        returnImg.setRGB(x, height - y - 1, backgourndColor.getRGB());
                    }
                }
                else if(order.charAt(0) == 'G') {
                    if(y < Gtemp[x]) {
                        returnImg.setRGB(x, height - y - 1, (new Color(0,255,0)).getRGB());
                        if(order.charAt(1) == 'R') {
                            if(y < Rtemp[x]) {
                                returnImg.setRGB(x, height - y - 1, (new Color(255, 0, 0)).getRGB());
                                if(y < Btemp[x]) {
                                    returnImg.setRGB(x, height - y - 1, (new Color(0, 0 ,255)).getRGB());
                                }
                            }
                        }
                        else {
                            if(y < Btemp[x]) {
                                returnImg.setRGB(x, height - y - 1, (new Color(0, 0, 255).getRGB()));
                                if(y < Rtemp[x]) {
                                    returnImg.setRGB(x, height - y - 1, (new Color(255, 0, 0)).getRGB());
                                }
                            }
                        }
                    }
                    else {
                        returnImg.setRGB(x, height - y - 1, backgourndColor.getRGB());
                    }
                }
                else {
                    if(y < Btemp[x]) {
                        returnImg.setRGB(x, height - y - 1, (new Color(0,0,255)).getRGB());
                        if(order.charAt(1) == 'G') {
                            if(y < Gtemp[x]) {
                                returnImg.setRGB(x, height - y - 1, (new Color(0, 255, 0)).getRGB());
                                if(y < Rtemp[x]) {
                                    returnImg.setRGB(x, height - y - 1, (new Color(255, 0 ,0)).getRGB());
                                }
                            }
                        }
                        else {
                            if(y < Rtemp[x]) {
                                returnImg.setRGB(x, height - y - 1, (new Color(255, 0, 0).getRGB()));
                                if(y < Gtemp[x]) {
                                    returnImg.setRGB(x, height - y - 1, (new Color(0, 255, 0)).getRGB());
                                }
                            }
                        }
                    }
                    else {
                        returnImg.setRGB(x, height - y - 1, backgourndColor.getRGB());
                    }
                }
            }
        }
        return returnImg;
    }

    public static BufferedImage drawRGBWithShade(int [] R, int [] G, int [] B, int height, Color backgourndColor,
                                        double paramForHeightR, double paramForHeightG, double paramForHeightB) {
        BufferedImage returnImg = new BufferedImage(R.length, height, BufferedImage.TYPE_4BYTE_ABGR);
        int imgWidth = R.length;
        int imgHeight = height;
        int [] Rtemp = R;
        int [] Gtemp = G;
        int [] Btemp = B;
        int secondRtemp = Array.secondInt(R);
        int secondGtemp = Array.secondInt(G);
        int secondBtemp = Array.secondInt(B);
        for(int x = 0 ; x < imgWidth ; x++) {
            Rtemp[x] = (int)(R[x]*height/paramForHeightR/secondRtemp);
            Gtemp[x] = (int)(G[x]*height/paramForHeightG/secondGtemp);
            Btemp[x] = (int)(B[x]*height/paramForHeightB/secondBtemp);
        }
        for(int x = 0 ; x < imgWidth ; x++) {
            String order = RGB.getOrder(Rtemp[x], Gtemp[x], Btemp[x]);
            for(int y = 0; y < imgHeight ; y++) {
                if(order.charAt(0) == 'R') {
                    if(y < Rtemp[x]) {
                        if(order.charAt(1) == 'G') {
                            if(y < Gtemp[x]) {
                                if(y < Btemp[x]) {
                                    returnImg.setRGB(x, height - y - 1, (new Color(0, 0 ,255)).getRGB());
                                }
                                returnImg.setRGB(x, height - y - 1, (new Color(0, 255, 0)).getRGB());
                            }
                        }
                        else {
                            if(y < Btemp[x]) {
                                if(y < Gtemp[x]) {
                                    returnImg.setRGB(x, height - y - 1, (new Color(0, 255, 0)).getRGB());
                                }
                                returnImg.setRGB(x, height - y - 1, (new Color(0, 0, 255).getRGB()));
                            }
                        }
                        returnImg.setRGB(x, height - y - 1, (new Color(255,0,0)).getRGB());
                    }
                    else {
                        returnImg.setRGB(x, height - y - 1, backgourndColor.getRGB());
                    }
                }
                else if(order.charAt(0) == 'G') {
                    if(y < Gtemp[x]) {
                        if(order.charAt(1) == 'R') {
                            if(y < Rtemp[x]) {
                                if(y < Btemp[x]) {
                                    returnImg.setRGB(x, height - y - 1, (new Color(0, 0 ,255)).getRGB());
                                }
                                returnImg.setRGB(x, height - y - 1, (new Color(255, 0, 0)).getRGB());
                            }
                        }
                        else {
                            if(y < Btemp[x]) {
                                if(y < Rtemp[x]) {
                                    returnImg.setRGB(x, height - y - 1, (new Color(255, 0, 0)).getRGB());
                                }
                                returnImg.setRGB(x, height - y - 1, (new Color(0, 0, 255).getRGB()));
                            }
                        }
                        returnImg.setRGB(x, height - y - 1, (new Color(0,255,0)).getRGB());
                    }
                    else {
                        returnImg.setRGB(x, height - y - 1, backgourndColor.getRGB());
                    }
                }
                else {
                    if(y < Btemp[x]) {
                        if(order.charAt(1) == 'G') {
                            if(y < Gtemp[x]) {
                                if(y < Rtemp[x]) {
                                    returnImg.setRGB(x, height - y - 1, (new Color(255, 0 ,0)).getRGB());
                                }
                                returnImg.setRGB(x, height - y - 1, (new Color(0, 255, 0)).getRGB());
                            }
                        }
                        else {
                            if(y < Rtemp[x]) {
                                if(y < Gtemp[x]) {
                                    returnImg.setRGB(x, height - y - 1, (new Color(0, 255, 0)).getRGB());
                                }
                                returnImg.setRGB(x, height - y - 1, (new Color(255, 0, 0).getRGB()));
                            }
                        }
                        returnImg.setRGB(x, height - y - 1, (new Color(0,0,255)).getRGB());
                    }
                    else {
                        returnImg.setRGB(x, height - y - 1, backgourndColor.getRGB());
                    }
                }
            }
        }
        return returnImg;
    }
}
