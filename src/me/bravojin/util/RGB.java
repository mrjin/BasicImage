package me.bravojin.util;

/**
 * Created by tyrionlanister on 15-5-28.
 */
public class RGB {
    public static String getOrder(int R, int G, int B) {
        if(R >= G && G >= B) {
            return "RGB";
        }
        else if(R >= B && B >= G) {
            return "RBG";
        }
        else if(B >= R && R >= G) {
            return "BRG";
        }
        else if(B >= G && G >= R) {
            return "BGR";
        }
        else if(G >= B && B >= R) {
            return "GBR";
        }
        else if(G >= R && R >= B) {
            return "GRB";
        }
        else {
            return null;
        }
    }
}
