package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import sample.Module.*;

public class Controller1 implements Initializable {

    @FXML
    public Canvas canvas;
    @FXML
    private Slider celleSlider;
    @FXML
    private HBox CanvasHbox;
    public GraphicsContext gc;
    Brett brett;

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        brett = new Brett(50,50,gc);

        celleSlider.setValue(brett.getCelleSTR());
        celleSlider.valueProperty().addListener(((observable, oldValue, newValue) -> {
            brett.setCelleSTR((int) celleSlider.getValue());
            clearBoard();
            brett.draw();
        }));
        System.out.println("Hehehe");
    }

    public void clearBoard() {
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
    }

    @FXML public void startAnimation(){
        System.out.println("halla");
        brett.nextGeneration();
    }

    @FXML public void makeBoard() {
        brett.draw();
    }


}