package Controller;

import FileHandler.FileReader;
import FileHandler.FileReaderRLE;
import FileHandler.FileReaderURL;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Board.Brett;
import sample.Board.DynamicBoard;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Optional;

public class GameOfLifeController implements Initializable {

    @FXML private ColorPicker colorpickercell;
    @FXML private ColorPicker colorPicker;
    @FXML private Button StartStopBtn;
    @FXML private Slider celleSlider;
    @FXML private Slider sliderSpeed;
    @FXML public Canvas canvas;

    public Timeline timeline = new Timeline();
//    FileReaderRLE file2 = new FileReaderRLE();
    FileReaderURL URLfile = new FileReaderURL();
    public GraphicsContext gc;
    Brett brett;


    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        gc = canvas.getGraphicsContext2D();
        brett = new DynamicBoard(900, 400, gc, canvas);

        brett.setBackgroundColor(Color.AQUA);
        brett.setCellColor(Color.BLACK);
        draw();

        celleSlider.setValue(canvas.getWidth()/brett.getCelleSTR()/canvas.getHeight()/brett.getCelleSTR());
        sliderSpeed.setValue(10);

        celleSlider.valueProperty().addListener(((observable, oldValue, newValue) -> {
            brett.setCelleSTR((int) celleSlider.getValue());
            draw();
        }));

        KeyFrame frame = new KeyFrame(Duration.millis(500), event -> {
            brett.nextGeneration();
            draw();
        });

        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }



    @FXML
    public void RLEopen() throws IOException {
//        try {
//            file2.readBoard();
//            brett.setBrett(file2.brett);
//            brett.setRules(file2.rules);
//            draw();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("RLE","*.rle"));

        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null){
            FileReaderRLE filereaderRle = new FileReaderRLE(file);
            brett.setBrett(filereaderRle.brett);
            brett.setRules(filereaderRle.rules);
            draw();
        } else {
            System.out.println("ingen fil funnet");
            //TODO alert box
        }
    }

    @FXML
    public void URLopen() throws IOException {
//        URLfile.readBoardURL();
//        brett.setBrett(URLfile.brett);
//        brett.setRules(URLfile.rules);
//        draw();


        TextInputDialog dialog = new TextInputDialog("//");
        dialog.setTitle("URL FileReader");
        dialog.setHeaderText("Copy and paste url adress here");
        dialog.setContentText("URL:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()){
            System.out.println("URL-adress: " + result.get());
            //lage til File og sende til FileReaderRLE
            URL website = new URL(result.get());
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());

            FileOutputStream fos = new FileOutputStream("tempPattern.rle");
            fos.getChannel().transferFrom(rbc,0,Long.MAX_VALUE);

            File file = new File("temp.rle");
            //////////

            FileReaderRLE filereaderRle = new FileReaderRLE(file);
            brett.setBrett(filereaderRle.brett);//eventuelt, sette disse tre linjene inne i FileReaderRle
            brett.setRules(filereaderRle.rules);
            draw();

        } else {
            System.out.println("URL-adress not found!");
        }
    }

    @FXML
    public void openFile(Event e) {

        int[][] nyBrett = FileReader.openTXTfile();

        for (int x = 0; x < nyBrett.length; x++) {
            for (int y = 0; y < nyBrett[0].length; y++) {
                brett.getBrett()[x][y] = nyBrett[x][y];
            }
        }
        draw();
    }

    @FXML
    public void changecolor (ActionEvent e){
        brett.setBackgroundColor(colorPicker.getValue());
        draw();


    }

    @FXML
    public void changeColorCell (ActionEvent c){
        brett.setCellColor(colorpickercell.getValue());
        draw();
    }

    public void clearBoard() {
        brett.setBrett(new int[brett.getRows()][brett.getColumns()]);
        draw();
    }

    //Next generation button show only 1 generation at each click
    @FXML
    public void startAnimation() {
        brett.nextGeneration();
        draw();
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
    public void AboutGameofLife(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Game of Life");
        alert.setContentText("About Game of Life" +
                "+dccdc" +
                "+cdcdc" +
                "+dcdcd" +
                "+dcdcd" +
                "+dcdcdc" +
                "+dcdccd" +
                "+cdcdcdcd");

        alert.showAndWait();

    }

    @FXML
    public void AdjustSpeed() {

        timeline.setRate(sliderSpeed.getValue());

    }

    @FXML
    public void userDrawCell() {

        canvas.setOnMouseDragged((MouseEvent e) -> {
            int x = (int) (e.getX() / brett.getCelleSTR());
            int y = (int) (e.getY() / brett.getCelleSTR());

            if (x < brett.getRows() && y < brett.getColumns()) {
                if (brett.getValue(x,y) == 1) {
                    brett.setValue(x,y,1);
                    draw();
                } else {
                    brett.setValue(x,y,1);
                    draw();
                }
            }
        });
    }

    public void draw() {

        brett.background();
        try{
            for (int j = 0; j < brett.getRows() && j < canvas.getWidth()/brett.getCelleSTR(); j++) {
                for (int i = 0; i < brett.getColumns() && i < canvas.getHeight()/brett.getCelleSTR(); i++) {
                    if (brett.getValue(j,i) == 1) {
                        gc.setFill(brett.cellColor);
                    } else {
                        gc.setFill(Color.WHITE);
                    }
                    gc.fillRect(j * brett.getCelleSTR(), i * brett.getCelleSTR(), brett.getCelleSTR()-1 , brett.getCelleSTR()-1 );
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("You cant draw here" + e);
        }
    }

    @FXML
    public void userDrawCellClicked() {
        canvas.setOnMouseClicked(e -> {
            int x = (int) (e.getX() / brett.getCelleSTR());
            int y = (int) (e.getY() / brett.getCelleSTR());

            if (brett.getBrett()[x][y] == 1) {
                brett.getBrett()[x][y] = 0;
                draw();

            } else {
                brett.getBrett()[x][y] = 1;
                draw();
            }
        });
    }
}

