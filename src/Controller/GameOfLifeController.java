package Controller;

import FileHandler.FileReader;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.Board.Brett;
import java.util.Arrays;


public class GameOfLifeController implements Initializable {

    @FXML
    public Canvas canvas;
    @FXML private Slider celleSlider;
    @FXML private Slider sliderSpeed;
    @FXML private HBox CanvasHbox;
    @FXML private Button StartStopBtn;
    @FXML private Button fileOpen;
    @FXML public ListView listView;
    @FXML private ColorPicker colorPicker;
    @FXML private ColorPicker colorpickercell;

    public GraphicsContext gc;
    Brett brett;
    public Timeline timeline = new Timeline();



    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        gc = canvas.getGraphicsContext2D();

        brett = new Brett(50, 50, gc);


        brett.setBackgroundColor(Color.AQUA);
        brett.setCellColor(Color.BLACK);

        brett.draw();


        celleSlider.setValue(brett.getCelleSTR());
        sliderSpeed.setValue(5);

        celleSlider.valueProperty().addListener(((observable, oldValue, newValue) -> {
            brett.setCelleSTR((int) celleSlider.getValue());
            brett.draw();
        }));

        KeyFrame frame = new KeyFrame(Duration.millis(500), event -> {
            brett.nextGeneration();
            brett.draw();
        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }




    @FXML
    public void openFile(Event e) {

        int[][] nyBrett = FileReader.openFromFile();

        System.out.println(Arrays.deepToString(nyBrett));
    }


    //colorpicker
    @FXML
    public void changecolor (ActionEvent e){

        brett.setBackgroundColor(colorPicker.getValue());
        brett.draw();

    }

    @FXML
    public void changeColorCell (ActionEvent c){
        brett.setCellColor(colorpickercell.getValue());
        brett.draw();


    }

    public void clearBoard() {

        brett.setBrett(new int[brett.getRad()][brett.getKolonne()]);
        brett.draw();

    }

    //Next generation button show only next generation at a time
    @FXML
    public void startAnimation() {

        brett.nextGeneration();
        brett.draw();
    }

    //Start & Stop button
    @FXML
    public void startSimulation() {

        if (timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.stop();
            StartStopBtn.setText("Start");
        } else {
            timeline.play();
            StartStopBtn.setText("Stop");
        }
    }

    @FXML
    public void AdjustSpeed() {

        timeline.setRate(sliderSpeed.getValue());
    }

    @FXML
    public void userDrawCell() {
        canvas.setOnMouseDragged(e -> {
            int x = (int) (e.getX() / brett.getCelleSTR());
            int y = (int) (e.getY() / brett.getCelleSTR());

            if (brett.getBrett()[x][y] == 1) {
                brett.getBrett()[x][y] = 1;
                brett.draw();

            } else {
                brett.getBrett()[x][y] = 1;
                brett.draw();
            }
        });
    }


    @FXML
    public void userDrawCellClicked() {
        canvas.setOnMouseClicked(e -> {
            int x = (int) (e.getX() / brett.getCelleSTR());
            int y = (int) (e.getY() / brett.getCelleSTR());

            if (brett.getBrett()[x][y] == 1) {
                brett.getBrett()[x][y] = 0;
                brett.draw();

            } else {
                brett.getBrett()[x][y] = 1;
                brett.draw();
            }
        });
    }

    @FXML
    public void sliderSpeed() {

        timeline.setRate(1);
    }


    @FXML
    public void makeBoard() {

        brett.draw();
    }

}

