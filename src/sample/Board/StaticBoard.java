package sample.Board;


import FileHandler.FileReader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Game Of Life application created for HIOA
 * The Static board class contains constructor and methods for creating and using a 2d array as board.
 * @Deprecated (@link #DynamicBoard class)
 * @author Fredrik, Hans Jacob, Mohammad
 *         Studentnr : S309293, s305064, s309856
 */

@Deprecated
public class StaticBoard {
    public GraphicsContext gc;
    public Canvas canvas;
    public int columns;
    public int rows;
    public int[][] boardStatic;
    public double cellSize = 7;
    private int gameSpeed = 40;
    private Color backgroundColor;
    public Color cellColor;
    int[][] rules =     {{0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 1, 1, 0, 0, 0, 0, 0}};

    public StaticBoard(){}


    /**
     * Constructs and initializes a board with columns, rows, gc and canvas
     * @param columns is the first parameter int the StaticBoard constructor
     * @param rows is the second parameter int the StaticBoard constructor
     * @param gc is the third parameter int the StaticBoard constructor
     * @param canvas is the fourth parameter int the StaticBoard constructor
     */
    //Constructor
    public StaticBoard(int columns, int rows, GraphicsContext gc, Canvas canvas) {
        this.columns = columns;
        this.rows = rows;
        this.gc = gc;
        boardStatic = new int[columns][rows];
        this.canvas = canvas;
//        draw();
    }

    /**
     * Static board is the constructor and defines the rows and columns
     * @param rows is the first parameter in the StaticBoard method.
     * @param columns is the second parameter in the StaticBoard method.
     */
    public StaticBoard(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;

    }

    /**
     * Sets background color by using setFill.
     */
    public void background() {
        gc.setFill(backgroundColor);

        gc.fillRect(0, 0, boardStatic.length * cellSize, boardStatic[0].length * cellSize);
    }

    /**
     * This method draws data to the board.
     */
    public void draw() {

        background();
        try {
            for (int j = 0; j < boardStatic.length && j < canvas.getWidth() / cellSize; j++) {
                for (int i = 0; i < boardStatic[0].length && i < canvas.getHeight() / cellSize; i++) {
                    if (boardStatic[j][i] == 1) {
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
     * @param x is the first parameter in the getValue method.
     * @param y is the second parameter in the getValue method.
     * @return boardStatic is the third parameter in the getValue method.
     */
    public int getValue(int x, int y) {
        return boardStatic[x][y];
    }

    /** Sets the value of a grid.
     * @param x is the first parameter in the setValue method.
     * @param y is the second parameter in the setValue method.
     * @param value is the third parameter in the setValue method.
     */
    public void setValue(int x, int y, int value) {
        boardStatic[x][y] = value;
    }

    /**
     * Calculates the value of next generations cell, and applies this value to each cell.
     */
    public void nextGeneration() {
        int[][] nesteBrett = new int[columns][rows];


        //beregning
        for (int x = 0; x < boardStatic.length; x++) {
            for (int y = 0; y < boardStatic[0].length; y++) {
                nesteBrett[x][y] = setCellRules(boardStatic[x][y], getNeighbours(x, y));
            }
        }
        boardStatic = nesteBrett;
        nesteBrett = null;

    }

    /**
     * Prints the values of a TXT-file to the board.
     */
    public void upDateBoard() {
        int[][] nyBrett = FileReader.openTXTfile();

        for (int x = 0; x < boardStatic.length; x++) {
            for (int y = 0; y < boardStatic[0].length; y++) {
                nyBrett[x][y] = setCellRules(boardStatic[y][x], getNeighbours(x, y));
            }
        }
        boardStatic = nyBrett;

    }

    /**
     * Sets the rules.
     * @param rules is the only parameter in the setRules method.
     */
    public void setRules(int[][] rules) {

        this.rules = rules;
    }

    /**
     * returns 0 or 1, as a representation of dead or alive.
     * @param isAlive is the first parameter in the setCellRules method.
     * @param naboer is the second parameter in the setCellRules method.
     * @return rules.
     */
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
     * @param x is the first parameter in the getNeighbours method.
     * @param y is the second parameter in the getNeighbours method.
     * @return antall naboer (neighbours).
     */
    public int getNeighbours(int x, int y) {
        //x = rader
        //y = kolonner

        int numbOfNeighbours = 0;


        if (!(x - 1 == -1 || y - 1 == -1) && boardStatic[x - 1][y - 1] == 1)
            numbOfNeighbours++;                                                                         //Exclude top left
        if (!(y - 1 == -1) && boardStatic[x][y - 1] == 1)
            numbOfNeighbours++;                                                                         //Exclude top middle
        if (!(x + 1 == boardStatic.length || y - 1 == -1) && boardStatic[x + 1][y - 1] == 1)
            numbOfNeighbours++;                                                                         //Exclude top right
        if (!(x + 1 == boardStatic.length || y + 1 == boardStatic[0].length) && boardStatic[x + 1][y + 1] == 1)
            numbOfNeighbours++;                                                                         //Exclude bottom right
        if (!(x - 1 == -1 || y + 1 == boardStatic[0].length) && boardStatic[x - 1][y + 1] == 1)
            numbOfNeighbours++;                                                                         //Exclude bottom left
        if (!(x + 1 == boardStatic.length) && boardStatic[x + 1][y] == 1)
            numbOfNeighbours++;                                                                         //Exclude bottom right
        if (!(y + 1 == boardStatic[0].length) && boardStatic[x][y + 1] == 1)
            numbOfNeighbours++;                                                                         //Exclude bottom middle
        if (!(x - 1 == -1) && boardStatic[x - 1][y] == 1)
            numbOfNeighbours++;                                                                         //Exclude left middle

        return numbOfNeighbours;

    }

    /**
     * Sets the cell size.
     * @param CSTR is the only parameter in the setCellSize method.
     */
    public void setCellSize(int CSTR) {

        this.cellSize = CSTR;
    }

    /**
     * Sets the board.
     * @param boardT is the only parameter in the setBoard method.
     * @return boardStatic.
     */
    public int[][] setBoard(int[][] boardT) {
        this.boardStatic = boardT;
        return boardT;
    }

    /**
     * Gets Brett.
     * @return boardStatic.
     */
    public int[][] getBoardStatic() {
        return boardStatic;
    }

    /**
     * Gets cell Size.
     * @return cell size.
     */
    public double getCellSize() {
        return cellSize;
    }

    /**
     * Gets columns.
     * @return columns
     */
    public int getColumns() {

        return columns;
    }

    /**
     * Sets columns.
     * @param columns is the only parameter in the setColumns method
     */
    public void setColumns(int columns) {

        this.columns = columns;
    }

    /**
     * Gets rows
     * @return rows
     */
    public int getRows() {

        return rows;
    }

    /**
     * Sets rows.
     * @param rows is the only parameter in the setRows method
     */
    public void setRows(int rows) {

        this.rows = rows;
    }

    /**
     * Gets game speed.
     * @return gameSpeed.
     */
    public int getGameSpeed() {

        return gameSpeed;
    }

    /**
     * Sets game speed.
     * @param gameSpeed is the only parameter int the setGameSpeed method.
     */
    public void setGameSpeed(int gameSpeed) {

        this.gameSpeed = gameSpeed;
    }

    /**
     * Sets background color.
     * @param backgroundColor is the only parameter in the setBackgroundVColor method.
     */
    public void setBackgroundColor(Color backgroundColor) {

        this.backgroundColor = backgroundColor;
    }

    /**
     * Sets grid color.
     * @param cellColor is the only parameter in the setCellColor method.
     */
    public void setCellColor(Color cellColor) {

        this.cellColor = cellColor;
    }


    //Opprettett til BrettTest.java

    /**
     * Returns and array of 0 or 1.
     * @return message.
     */
    @Override
    public String toString() {
        String msg = "";
        for (int kolonne = 0; kolonne < boardStatic.length; kolonne++) {
            for (int rows = 0; rows < boardStatic[0].length; rows++) {
                if (boardStatic[rows][kolonne] == 0) {
                    msg = msg + "0";
                } else {
                    msg = msg + "1";
                }
            }
        }
        return msg;
    }


}