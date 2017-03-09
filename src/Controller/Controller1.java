package Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import sample.Module.*;

import javax.swing.plaf.basic.BasicTabbedPaneUI;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.Time;

import static javafx.scene.input.KeyCode.T;

public class Controller1 implements Initializable {

    @FXML public Canvas canvas;
    @FXML private Slider celleSlider;
    @FXML private Slider sliderSpeed;
    @FXML private HBox CanvasHbox;
    @FXML private Button StartStopBtn;
    public GraphicsContext gc;
    Brett brett;
    public Timeline timeline = new Timeline();


    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        gc = canvas.getGraphicsContext2D();
        brett = new Brett(50,50,gc);

//        sliderSpeed.setValue(brett.getGameSpeed());

        celleSlider.setValue(brett.getCelleSTR());

        celleSlider.valueProperty().addListener(((observable, oldValue, newValue) -> {
            brett.setCelleSTR((int) celleSlider.getValue());
            clearBoard();
            brett.draw();
        }));

//        sliderSpeed.valueProperty().addListener(((observable, oldValue, newValue) -> {
//            KeyFrame frame = new KeyFrame(Duration.millis(sliderSpeed.getValue()), event -> brett.nextGeneration());
//            timeline.getKeyFrames().add(frame);
//            timeline.setCycleCount(Timeline.INDEFINITE);
//            System.out.println(newValue);
//        }));

        KeyFrame frame = new KeyFrame(Duration.millis(1000), event -> brett.nextGeneration());
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void clearBoard() {
//        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        brett.setBrett(new int[brett.getRad()][brett.getKolonne()]);
        brett.draw();
    }

    //Next generation button show only next generation at a time
    @FXML public void startAnimation(){
        System.out.println("halla");
        brett.nextGeneration();
    }

    //Start & Stop button
    @FXML public void startSimulation(){
        if (timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.stop();
            StartStopBtn.setText("Start");
        } else {
//            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100)));
            timeline.play();
            StartStopBtn.setText("Stop");
        }
    }

    @FXML
    public void AdjustSpeed(){
//        System.out.println(sliderSpeed.getValue() +" ms");
        timeline.setRate(sliderSpeed.getValue());



//        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(sliderSpeed.getValue())));
//        sliderSpeed.getValue();
//        KeyFrame frame = new KeyFrame(Duration.millis(sliderSpeed.getValue()), event -> brett.nextGeneration());
//        timeline.getKeyFrames().add(frame);
    }

    @FXML
    public void userDrawCell(){
        canvas.setOnMouseDragged( e-> {
            int x = (int) (e.getX() / brett.getCelleSTR());
            int y = (int) (e.getY() / brett.getCelleSTR());

            if (brett.getBrett()[x][y] == 1)  {
                brett.getBrett()[x][y] = 0;
                brett.draw();

            }else{
                brett.getBrett()[x][y] = 1;
                brett.draw();
            }
        });
    }

    @FXML
    public void userDrawCellClicked(){
        canvas.setOnMouseClicked( e-> {
            int x = (int) (e.getX() / brett.getCelleSTR());
            int y = (int) (e.getY() / brett.getCelleSTR());

            if (brett.getBrett()[x][y] == 1)  {
                brett.getBrett()[x][y] = 0;
                brett.draw();

            }else{
                brett.getBrett()[x][y] = 1;
                brett.draw();
            }
        });
    }

    @FXML public void sliderSpeed(){
        timeline.setRate(1);

    }


    @FXML public void makeBoard() {
        brett.draw();
    }
    
    }

