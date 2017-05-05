package BoardLogic;


import FileHandler.FileReader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Game Of Life application created for Oslo and Akershus University Collage of Applied Sciences.
 * The Static board class contains constructors and methods for creating and using a 2D array as board.
 * @Deprecated (@link #DynamicBoard class)
 * @author Fredrik Kluftodegaard, Hans Jacob Hauge, Mohammad Abdolla
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
    public Color cellColor;
    int[][] rules =     {{0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 1, 1, 0, 0, 0, 0, 0}};
    private int gameSpeed = 40;
    private Color backgroundColor;

    /**
     * Constructs and initializes a board with columns, rows, gc and canvas.
     * @param columns is the columns used in the constructor.
     * @param rows is the rows used in the constructor.
     * @param gc is the gc used in the constructor.
     * @param canvas is the canvas used in the constructor.
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
     * Static board is the constructor and defines the rows and columns.
     * @param rows is used int the constructor.
     * @param columns is used in the constructor.
     */
    public StaticBoard(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;

    }

    /**
     * This constructor is used in the GETneighbourTEST.
     */
    public StaticBoard(){}

    /**
     * Sets background color by using setFill on the graphicsContext.
     */
    public void background() {
        gc.setFill(backgroundColor);

        gc.fillRect(0, 0, boardStatic.length * cellSize, boardStatic[0].length * cellSize);
    }

    /**
     * This method draws data to the board.
     * Within the try and Catch, a for loop is created that runs through the length of the board and the canvas' width, and checks if the cells is alive and colors the cells, vice versa if they are dead they are also colored.
     * Not in use because its static, The draw method in the Controller is used.
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
     * returns the value of the board.
     * @param x is the first parameter in the getValue method.
     * @param y is the second parameter in the getValue method.
     * @return boardStatic is the third parameter in the getValue method.
     */
    public int getValue(int x, int y) {
        return boardStatic[x][y];
    }

    /**
     * Sets the value of the board.
     * @param x is the first parameter in the setValue method.
     * @param y is the second parameter in the setValue method.
     * @param value is the third parameter in the setValue method.
     */
    public void setValue(int x, int y, int value) {
        boardStatic[x][y] = value;
    }

    /**
     * Calculates the value of next generations cell, and applies this value to each cell.
     * Then a for loop is created that runs through the length of board (rows and columns) and adds a the rules and the getNeighbours in the Array,
     * Then the array is equal to boardStatic Array
     */
    public void nextGeneration() {
        int[][] nextBoard = new int[columns][rows];


        //beregning
        for (int x = 0; x < boardStatic.length; x++) {
            for (int y = 0; y < boardStatic[0].length; y++) {
                nextBoard[x][y] = setCellRules(boardStatic[x][y], getNeighbours(x, y));
            }
        }
        boardStatic = nextBoard;
        nextBoard = null;

    }

    /**
     * Creates a new Array with the input from textfiles.
     * A for loop runs through the lenght (rows and columns) of the board and adds the rules and the neighbours.
     * declares the new board as boardStatic.
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
     * Setter for the rules.
     * @param rules is the only parameter in the setRules method.
     */
    public void setRules(int[][] rules) {

        this.rules = rules;
    }

    /**
     * Setter for CellRules.
     * @param isAlive is the first parameter in the setCellRules method.
     * @param naboer is the second parameter in the setCellRules method.
     * @return 0 or 1, as a representation of dead or alive.
     */
    public int setCellRules(int isAlive, int naboer) {

        return rules[isAlive][naboer];

/*
        ---- This is how the rules were implemented originally ----
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
     * The if loops checks if the patterns runs out the board, then it adds on the int totalNumbers
     * @param x are rows
     * @param y are columns.
     * @return antall naboer (neighbours).
     */
    public int getNeighbours(int x, int y) {

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
    public void setCellSize(double CSTR) {

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
     * This method is the getter for the board.
     * @return boardStatic.
     */
    public int[][] getBoardStatic() {
        return boardStatic;
    }

    /**
     * This method is the getter for the CellSize.
     * @return cell size.
     */
    public double getCellSize() {
        return cellSize;
    }

    /**
     * This method is the getter for the Columns.
     * @return columns
     */
    public int getColumns() {

        return columns;
    }

    /**
     * This method is the setter for the Columns.
     * @param columns is the only parameter in the setColumns method
     */
    public void setColumns(int columns) {

        this.columns = columns;
    }

    /**
     * This method is the getter for the Rows.
     * @return rows
     */
    public int getRows() {

        return rows;
    }

    /**
     * This method is the setter for the rows.
     * @param rows is the only parameter in the setRows method
     */
    public void setRows(int rows) {

        this.rows = rows;
    }

    /**
     * This method is the getter for the gameSpeed.
     * @return gameSpeed.
     */
    public int getGameSpeed() {

        return gameSpeed;
    }

    /**
     * This method is the setter for the gameSpeed.
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
     * This method is the setter for the cellColor.
     * @param cellColor is the only parameter in the setCellColor method.
     */
    public void setCellColor(Color cellColor) {

        this.cellColor = cellColor;
    }

    /**
     * This is a toString method.
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