package Controller;

import FileHandler.FileReader;
import FileHandler.FileReaderRLE;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Board.DynamicBoard;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Optional;

import static InterfaceDialog.Dialogboxes.*;
import static InterfaceSounds.Sounds.btnSound;
import static InterfaceSounds.Sounds.errorSound;
import static InterfaceSounds.Sounds.startUpSound;

/**
 * The Game Of Life application created for HIOA.
 * The Controller class is the fx for fxml, all the features in fxml are assigned in this class.
 * The class is also implementing Initializable interface.
 *
 * @author Fredrik Kluftodegaard, Hans Jacob Hauge, Mohammad Abdolla
 *         Studentnr : S309293, s305064, s309856
 */
public class GameOfLifeController implements Initializable {


    @FXML
    private Canvas canvas;
    private Timeline timeline = new Timeline();
    public GraphicsContext gc;
    DynamicBoard dynamicBoard;

    int oldChangeX;
    int getOldChangeY;

    int oldChangeClick_X;
    int getoldChangeClick_Y;
    @FXML
    private ColorPicker colorpickercell;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Button StartStopBtn;
    @FXML
    private Slider celleSlider;
    @FXML
    private Slider sliderSpeed;
    @FXML
    private LineChart linechart;
    private int iterasjoner = 0;
    int numbofWorkers = Runtime.getRuntime().availableProcessors();


    /**
     * Constructs and initializes the canvas and application features.
     *
     * The start up sound method is ran here.
     * The DynamicBoard object is created.
     * Default values on the colorpickers is set.
     * The zoom Slider is defined with a for loop.
     * The keyframe is created and defined. Within the keyframe we have some important methods to run like the nextGenerationConcurrent,
     * draw, and zoomable, then the keyframe is added in the timeline.
     *
     * @param location is the first parameter in the initialize method.
     * @param resources is the second parameter in the initialize method.
     */

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        System.out.println("Available processors:" + numbofWorkers);

        startUpSound();

        gc = canvas.getGraphicsContext2D();
        dynamicBoard = new DynamicBoard(100, 80, gc, canvas);

        colorpickercell.setValue(Color.BLACK);
        colorPicker.setValue(Color.AQUA);
        draw();

        celleSlider.setValue(dynamicBoard.getCellSize());
        celleSlider.valueProperty().addListener(((observable, oldValue, newValue) -> {
            dynamicBoard.setCellSize((int) celleSlider.getValue());
            if ((double) newValue < (double) oldValue) {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            }

            draw();
        }));

        KeyFrame frame = new KeyFrame(Duration.millis(500), (ActionEvent event) -> {
            zoomable();
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            dynamicBoard.nextGenerationConcurrent();
            draw();
            iterasjoner++;
        });

        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * This method allows the user to upload RLE file containing board pattern from computer disk.
     *
     * First a Filechooseer is created that lets the users choose some file they want, with a showOpenDialog.
     * Then if the file is not empty, the file is added to the filereaders board and rules is added to the dynamicBoards board and rules.
     * Also the if loop is set in a try and catch.
     *
     * @Throws Exeption if user uploads wrong file format, or invalid file.
     * If error occurs an alertbox will show.
     */
    @FXML
    private void RLEopen() throws Exception {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("RLE", "*.rle"));

        File file = fileChooser.showOpenDialog(new Stage());

        try {
            if (file != null) {
                FileReaderRLE filereaderRle = new FileReaderRLE(file);
                dynamicBoard.setBoard(filereaderRle.brett);
                dynamicBoard.setRules(filereaderRle.rules);
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                draw();

            }

        } catch (Exception e) {
            errorSound();
            filenotFoundError();
        }
    }

    /**
     * This method allows the user to upload RLE board pattern from URL link.
     *
     * First, a text input Dialog is created with some messages to the users,
     * then the URL is checked if it is present, then the content is read with ReadableByteChannel.
     * The content is put in a temporary file with FileOutputStream.
     * The original file is equal to the new temporary file.
     * The file is added to the Filereader of RLE, with the board and the rules of Dynamicboard.
     *
     * @Throws Exeption if user uploads wrong file format, or invalid file.
     * If error occurs an alertbox will show.
     */
    @FXML
    private void URLopen() throws Exception {

        TextInputDialog dialog = new TextInputDialog("//");
        dialog.setTitle("URL FileReader");
        dialog.setHeaderText("Copy and paste url adress here:");
        dialog.setContentText("URL:");

        Optional<String> result = dialog.showAndWait();

        try {
            if (result.isPresent()) {
                System.out.println("URL-adress: " + result.get());
                //lage til File og sende til FileReaderRLE
                URL website = new URL(result.get());
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());

                FileOutputStream fos = new FileOutputStream("tempPattern.rle");
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

                File file = new File("tempPattern.rle");

                FileReaderRLE filereaderRle = new FileReaderRLE(file);
                dynamicBoard.setBoard(filereaderRle.brett);
                dynamicBoard.setRules(filereaderRle.rules);
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                draw();
            }

        } catch (Exception e) {
            errorSound();
            filenotFoundError();
        }
    }

    /**
     * This method allows the user to upload txt file containing board pattern.
     *
     * A new board is created and the dynamic Boards sizes is added to the new board,
     * then the other methods from the dynamicboard is runned
     *
     *
     * @param e is the only parameter.
     */
    @FXML
    public void openFile(Event e) {

        int[][] nyBrett = FileReader.openTXTfile();

        for (int x = 0; x < nyBrett.length; x++) {
            for (int y = 0; y < nyBrett[0].length; y++) {
                dynamicBoard.getBoardStatic()[x][y] = nyBrett[x][y];
            }
        }

        dynamicBoard.setBoard(nyBrett);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        draw();
    }

    /**
     * This method auto-zooms when the board is expanding
     */
    private void zoomable() {
        if (dynamicBoard.board.size() > canvas.getWidth() / dynamicBoard.getCellSize()) {
            celleSlider.setValue(celleSlider.getValue() - 0.01);
            dynamicBoard.setCellSize((int) (celleSlider.getValue() - 0.01));
        }
        if (dynamicBoard.board.get(0).size() > canvas.getHeight() / dynamicBoard.getCellSize()) {
            celleSlider.setValue(celleSlider.getValue() - 0.01);
            dynamicBoard.setCellSize((int) (celleSlider.getValue() - 0.01));
        }
    }

    @FXML
    private void seeStatistics() {
        //linechart.setData(iterasjoner);

    }

    /**
     * This method allows user to change the color for dead cells.
     * @param e is the only parameter in the changeColor method.
     */
    @FXML
    private void changecolor(ActionEvent e) {
        dynamicBoard.setBackgroundColor(colorPicker.getValue());
        draw();
    }

    /**
     * This method allows userinput to change the background color with input from colorpicker.
     * @param c is the name of the actionevent that chooses color.
     */
    @FXML
    private void changeColorCell(ActionEvent c) {
        dynamicBoard.setCellColor(colorpickercell.getValue());
        draw();
    }

    /**
     * This method allows user to clear canvas and creates a new empty board.
     */
    public void resetBoard() {
        btnSound();
        gc = canvas.getGraphicsContext2D();
        dynamicBoard = new DynamicBoard(dynamicBoard.getColumns(), dynamicBoard.getRows(), gc, canvas);
        draw();
    }

    /**
     * This method allows user to click start/stop button in the application.
     * Inside a if loop, if the timeline is running it will stop when the button is clicked and the button text will change to start
     * and if the timeline is stopped it will play and the text on the button wil be changed to stop
     */
    @FXML
    public void startSimulation() {

        btnSound();
        if (timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.stop();
            StartStopBtn.setText("Start");
        } else {
            timeline.play();
            StartStopBtn.setText("Stop");
        }
    }

    /**
     * This method calls a method that show a alert box with information on how to play the game.
     */
    @FXML
    public void howToPlay() {

        howToPlayInfo();

    }

    /**
     * This method calls a method that show a alert box with information about game of life.
     */
    @FXML
    public void AboutGameofLife() {

        gameInformation();

    }

    /**
     * This method allows user to change the gamespeed.
     * With the value of the slider, the speed of the timeline is adjusted.
     */
    @FXML
    public void AdjustSpeed() {

        timeline.setRate(sliderSpeed.getValue());
    }

    /**
     * This method draws the background to the canvas.
     * It fills the GraphicsContext with a simple color,
     * and fills the rects and adjusts the sizes.
     */
    public void background() {
        gc.setFill(Color.GRAY);

        double cellsize1 = dynamicBoard.getCellSize();
        gc.fillRect(0, 0, dynamicBoard.getColumns() * cellsize1, dynamicBoard.getRows() * cellsize1);
    }

    /**
     * This method draws the grid/cells on canvas.
     * Within a try and catch there is some for and if loops that runs through the number of columns of the board and the width of the canvas.
     * If the value/cell is alive, it fills it with a color picked from the Color picker.
     * The same happens if the value/cell is dead.
     * All the rects are filled.
     *
     */
    public void draw() throws ArrayIndexOutOfBoundsException {

        background();

        try {

            for (int j = 0; j < dynamicBoard.getColumns() && j < canvas.getWidth(); j++) {

                for (int i = 0; i < dynamicBoard.getRows() && i < canvas.getWidth(); i++) {
                    if (dynamicBoard.getValue(j, i) == 1) {
                        gc.setFill(colorpickercell.getValue());

                    } else {
                        gc.setFill(colorPicker.getValue());
                    }

                    double CellSize = dynamicBoard.getCellSize();

                    gc.fillRect(j * CellSize, i * CellSize, CellSize - 0.5, CellSize - 0.5);

                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            drawError();
        }
    }

    /**
     *
     * @param e
     * @throws Exception
     */


    @FXML
    public void userDrawCell(MouseEvent e) throws Exception {

        try {
            int x = (int) (e.getX() / dynamicBoard.getCellSize());
            int y = (int) (e.getY() / dynamicBoard.getCellSize());
            if (x == oldChangeX && y == getOldChangeY)
                return;

            if (x >= 0 && y >= 0 && x < dynamicBoard.getColumns() && y < dynamicBoard.getRows()) {
                boolean value = dynamicBoard.getValue(x, y) == 0;
                dynamicBoard.setValue(x, y, value ? 1 : 0); //ternary operator
                oldChangeX = x;
                getOldChangeY = y;
                draw();
            }
        } catch (Exception f) {
            errorSound();
            drawError();
        }
    }

    @FXML
    public void userDrawCellClicked(MouseEvent e) throws Exception {

        try {
            int x = (int) (e.getX() / dynamicBoard.getCellSize());
            int y = (int) (e.getY() / dynamicBoard.getCellSize());
            if (x == oldChangeClick_X && y == getoldChangeClick_Y)
                return;

            if (x >= 0 && y >= 0 && x < dynamicBoard.getColumns() && y < dynamicBoard.getRows()) {
                boolean value = dynamicBoard.getValue(x, y) == 0;
                dynamicBoard.setValue(x, y, value ? 1 : 0); //ternary operator
                oldChangeClick_X = x;
                getoldChangeClick_Y = y;
                draw();
            } else if (x < dynamicBoard.getColumns() && y < dynamicBoard.getRows()) {

                if (dynamicBoard.getValue(x, y) == 0) {
                    dynamicBoard.setValue(x, y, 0);
                    draw();
                }
            }

        } catch (Exception err) {
            errorSound();
            drawError();
        }
    }


    /**
     * This method close the application when button Exit is pressed.
     */
    @FXML
    public void closeButtonAction() {
        Platform.exit();

    }

}

