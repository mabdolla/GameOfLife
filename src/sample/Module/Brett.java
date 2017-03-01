package sample.Module;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brett {
    public GraphicsContext gc;
    private int rad ;
    private int kolonne;
    private int[][] brett;
    private int celleSTR = 15;

    public Brett(int rad, int kolonne, GraphicsContext gc) {
        this.rad=rad;
        this.kolonne=kolonne;
        this.gc=gc;
        brett = new int [rad][kolonne];
    }

    public void draw() {
        for (int j = 0; j < brett.length; j++) {
            for (int i = 0; i < brett[j].length; i++) {
                gc.setFill(Color.DARKGRAY);
                gc.fillRect(j*celleSTR,i*celleSTR,celleSTR-1,celleSTR-1);
            }
        }
        lagSpillebrett();
    }

    public void lagSpillebrett () {
        for (int j = 0; j < brett.length; j++) {
            for (int i = 0; i < brett[j].length; i++) {
                if (brett[j][i]==1) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(j*celleSTR,i*celleSTR,celleSTR-1,celleSTR-1);

            }
        }
    }}

    public void setCelleSTR (int CSTR) {
        this.celleSTR=CSTR;
    }

    public int getCelleSTR () {
        return celleSTR;
    }


}

