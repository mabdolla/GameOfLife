package sample.Board;


import FileHandler.FileReader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Game Of Life application created for HIOA
 * The Static board class contains constructor and methods for creating and using a 2d array as board.
 *
 * @author Fredrik, Hans Jacob, Mohammad
 *         Studentnr : S309293, s305064, s309856
 */
public class StaticBoard {
    public GraphicsContext gc;
    public Canvas canvas;
    public int rows;
    public int columns;
    public int[][] brett;
    public double cellSize = 7;
    private int gameSpeed = 40;
    private Color backgroundColor;
    public Color cellColor;
    int[][] rules =     {{0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 1, 1, 0, 0, 0, 0, 0}};

    public StaticBoard(){}


    /**
     * Constructs and initializes a board with rows, columns, gc and canvas
     * @param rows
     * @param columns
     * @param gc
     * @param canvas
     */
    //Constructor
    public StaticBoard(int rows, int columns, GraphicsContext gc, Canvas canvas) {
        this.rows = rows;
        this.columns = columns;
        this.gc = gc;
        brett = new int[rows][columns];
        this.canvas = canvas;
//        draw();
    }

    /**
     * Static board.
     * @param rows
     * @param columns
     */
    public StaticBoard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

    }

    /**
     * Sets background color.
     */
    public void background() {
        gc.setFill(backgroundColor);

        gc.fillRect(0, 0, brett.length * cellSize, brett[0].length * cellSize);
    }

    /**
     * Sets grid color.
     */
    public void draw() {

        background();
        try {
            for (int j = 0; j < brett.length && j < canvas.getWidth() / cellSize; j++) {
                for (int i = 0; i < brett[0].length && i < canvas.getHeight() / cellSize; i++) {
                    if (brett[j][i] == 1) {
                        gc.setFill(cellColor);
                    } else {
                        gc.setFill(Color.AQUA);
                    }
                    gc.fillRect(j * cellSize, i * cellSize, cellSize - 1, cellSize - 1);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You cant draw here" + e);
        }
    }

    /**
     * Gets the value of a grid.
     * @param x
     * @param y
     * @return brett
     */
    public int getValue(int x, int y) {
        return brett[x][y];
    }

    /**
     * @param x
     * @param y
     * @param value
     */
    public void setValue(int x, int y, int value) {
        brett[x][y] = value;
    }


    public void nextGeneration() {
        int[][] nesteBrett = new int[rows][columns];


        //beregning
        for (int x = 0; x < brett.length; x++) {
            for (int y = 0; y < brett[0].length; y++) {
                nesteBrett[x][y] = setCellRules(brett[x][y], getNeighbours(x, y));
            }
        }
        brett = nesteBrett;
        nesteBrett = null;

    }

    public void upDateBoard() {
        int[][] nyBrett = FileReader.openTXTfile();

        for (int x = 0; x < brett.length; x++) {
            for (int y = 0; y < brett[0].length; y++) {
                nyBrett[x][y] = setCellRules(brett[y][x], getNeighbours(x, y));
            }
        }
        brett = nyBrett;

    }

    /**
     * Gets rules.
     * @param rules
     */
    public void setRules(int[][] rules) {

        this.rules = rules;
    }

    public int setCellRules(int isAlive, int naboer) {

        return rules[isAlive][naboer];

/*
       if (isAlive == 0 && naboer <= 2)
            return 0;
        if (isAlive == 0 && naboer == 3)
            return 1;
        if(isAlive == 0 && naboer >= 4)
            return 0;
        if(isAlive == 1 && naboer <= 1)
            return 0;
        if(isAlive == 1 && naboer == 2 || naboer == 3)
            return 1;
        if(isAlive == 1 && naboer >= 4)
            return 0;
        return 0;*/

    }


    /**
     * Declares the rules of the board.
     * @param x
     * @param y
     * @return antall naboer (neighbours).
     */
    public int getNeighbours(int x, int y) {
        //x = rader
        //y = kolonner

        int antallNaboer = 0;


        if (!(x - 1 == -1 || y - 1 == -1) && brett[x - 1][y - 1] == 1)
            antallNaboer++;                                                                         //Exclude top left
        if (!(y - 1 == -1) && brett[x][y - 1] == 1)
            antallNaboer++;                                                                         //Exclude top middle
        if (!(x + 1 == brett.length || y - 1 == -1) && brett[x + 1][y - 1] == 1)
            antallNaboer++;                                                                         //Exclude top right
        if (!(x + 1 == brett.length || y + 1 == brett[0].length) && brett[x + 1][y + 1] == 1)
            antallNaboer++;                                                                         //Exclude bottom right
        if (!(x - 1 == -1 || y + 1 == brett[0].length) && brett[x - 1][y + 1] == 1)
            antallNaboer++;                                                                         //Exclude bottom left
        if (!(x + 1 == brett.length) && brett[x + 1][y] == 1)
            antallNaboer++;                                                                         //Exclude bottom right
        if (!(y + 1 == brett[0].length) && brett[x][y + 1] == 1)
            antallNaboer++;                                                                         //Exclude bottom middle
        if (!(x - 1 == -1) && brett[x - 1][y] == 1)
            antallNaboer++;                                                                         //Exclude left middle

        return antallNaboer;

    }

    /**
     * Gets cell size.
     * @param CSTR
     */
    public void setCellSize(int CSTR) {

        this.cellSize = CSTR;
    }

    /**
     * @param brett
     * @return brett
     */
    public int[][] setBrett(int[][] brett) {
        this.brett = brett;
        return brett;
    }

    /**
     * Gets Brett.
     * @return brett.
     */
    public int[][] getBrett() {
        return brett;
    }

    /**
     * Gets cell Size.
     * @return cell size.
     */
    public double getCellSize() {
        return cellSize;
    }

    /**
     * Gets rows.
     * @return rows
     */
    public int getRows() {

        return rows;
    }

    /**
     * Gets rows.
     * @param rows
     */
    public void setRows(int rows) {

        this.rows = rows;
    }

    /**
     * Gets columns
     * @return columns
     */
    public int getColumns() {

        return columns;
    }

    /**
     * Sets columns.
     * @param columns
     */
    public void setColumns(int columns) {

        this.columns = columns;
    }

    /**
     * Gets game speed.
     * @return
     */
    public int getGameSpeed() {

        return gameSpeed;
    }

    /**
     * Sets game speed
     * @param gameSpeed
     */
    public void setGameSpeed(int gameSpeed) {

        this.gameSpeed = gameSpeed;
    }

    /**
     * Sets background color.
     * @param backgroundColor
     */
    public void setBackgroundColor(Color backgroundColor) {

        this.backgroundColor = backgroundColor;
    }

    /**
     * Sets grid color.
     * @param cellColor
     */
    public void setCellColor(Color cellColor) {

        this.cellColor = cellColor;
    }


    //Opprettett til BrettTest.java
    @Override
    public String toString() {
        String msg = "";
        for (int kolonne = 0; kolonne < brett.length; kolonne++) {
            for (int rows = 0; rows < brett[0].length; rows++) {
                if (brett[rows][kolonne] == 0) {
                    msg = msg + "0";
                } else {
                    msg = msg + "1";
                }
            }
        }
        return msg;
    }


}