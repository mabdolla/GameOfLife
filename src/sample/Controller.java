package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Controller implements Initializable {

    @FXML private Canvas canvas;
    @FXML private Slider celleSlider;
    GraphicsContext gc;
    @FXML private HBox CanvasHbox;

    private byte [][] board = new byte[80] [80];
           /* {1,0,0,1,0,0,0},
            {0,1,1,0,0,0,0},
            {0,1,1,0,0,0,0},
            {1,0,0,1,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0}
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
    };*/

    public void initialize(java.net.URL location, java.util.ResourceBundle resources ){
        celleSlider.setValue(90.0);
        draw();
    }


    public void lagSpillebrett () {
        for (int j = 0; j < board.length; j++) {
            for (int i = 0; i < board[0].length; i++) {
                double celleSTR = celleSlider.getValue();

                if (board[j][i] == 0) {
                    gc.setFill(Color.WHITE);
                    gc.fillRect(10+10*j, 10+10*i, celleSTR,celleSTR);
                } else if (board[j][i] == 1) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(10+10*j, 10+10*i, celleSTR, celleSTR);
                }
            }
        }
    }

    public void draw() {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0, 0, canvas.getWidth() , canvas.getHeight());

        lagSpillebrett();
    }
}
