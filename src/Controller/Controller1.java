package Controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import sample.Module.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.Time;

public class Controller1 implements Initializable {

    @FXML public Canvas canvas;
    @FXML private Slider celleSlider;
    @FXML private HBox CanvasHbox;
    public GraphicsContext gc;
    Brett brett;
    final Timeline timeline = new Timeline();

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


    //Next generation button
    @FXML public void startAnimation(){
        System.out.println("halla");
        brett.nextGeneration();
    }

    @FXML public void startSimulation(){
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        KeyFrame keyvalue = new KeyFrame(new Duration(50), e -> brett.nextGeneration());
        timeline.getKeyFrames().add(keyvalue);

        timeline.play();
    }


    @FXML public void makeBoard() {
        brett.draw();
    }
    
}

