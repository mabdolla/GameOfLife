package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

public class Controller implements Initializable {

    @FXML private Canvas canvas;
    @FXML private Slider celleSlider;
    GraphicsContext gc;

    private byte [][] board = {
            {1,0,0,1},
            {0,1,1,0},
            {0,1,1,0},
            {1,0,0,1}
    };

    public void initialize(java.net.URL location, java.util.ResourceBundle resources ){
        draw();
    }

    public void lagSpillebrett () {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                double celleSTR = celleSlider.getValue();
                if (board[i][j] == 0) {
                    gc.setFill(Color.INDIANRED);
                    gc.fillRect(50+10*i, 5+20*j, celleSTR,celleSTR);
                } else if (board[i][j] == 1) {
                    gc.setFill(Color.BLUE);
                    gc.fillRect(50+10*i, 5+20*j, celleSTR, celleSTR);
                }
            }
        }
    }

    public void draw() {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.DARKGREY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        lagSpillebrett();
    }


}
