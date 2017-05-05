package sample.Board;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

/**
 * The Game Of Life application created for HIOA
 * The DynamicBoard class contains constructor and methods for creating and using arraylist as board.
 * Next generation method updating the board according to rules you will find in this class.
 * Next generation method is also running on Threads based on x number of processors from your computer.
 *
 * @author Fredrik Kluftodegaard, Hans Jacob Hauge, Mohammad Abdolla
 *         Studentnr : S309293, s305064, s309856
 */
public class DynamicBoard extends StaticBoard {

    final int proseccors = Runtime.getRuntime().availableProcessors();
    public ArrayList<ArrayList<Integer>> board = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> nextGen;
    double cellSize = 9;
    int boardSplit;

    /**
     * Constructs and init a board with rows, columns, gc and canvas.
     * The forloop runs through number of columns and rows and adds the amount to the board Arraylist as columns and rows
     *
     * @param rows    is the first parameter in DynamicBoard constructor.
     * @param columns is the second parameter in DynamicBoard constructor.
     * @param gc      is the third parameter used for drawing.
     * @param canvas  is the fourth parameter in DynamicBoard constructor which is used to draw onto.
     * @see #StaticBoard (int, int, GraphicsContext, Canvas) (String)
     */
    public DynamicBoard(int rows, int columns, GraphicsContext gc, Canvas canvas) {
        super(rows, columns, gc, canvas);

        for (int i = 0; i < getColumns(); i++) {
            board.add(new ArrayList<Integer>());
            for (int j = 0; j < getRows(); j++) {
                board.get(i).add(0);
            }
        }
    }


    /**
     * calculates the next generation and updates the board.
     *
     * Defines the nextgen Arraylist with the size of the columns of the array
     * Then a for loop is created that runs through the number of columns and rows and adds a new Arraylist with getRows in nextgen with the add function
     * Then the Threads are created with a for loop that runs through the amount of proseccors and creates threads of the same amount, then another for loop inside that runs throug the amount of threads and applies the rules and getneighbours
     * Then within two forloops the Threads are started and joined, join also needed to be inside a try and catch.
     * after the threads are started the Board Arraylist is equal to the nextgen Arraylist
     * Then the method expand is runned
     *
     */
    public void nextGenerationConcurrent() {
        List<Thread> workers = new ArrayList<Thread>();
        //lag nextGenBoard = new array-.---..
        nextGen = new ArrayList<>(getColumns());

        //Creates an empty arraylist called nextgen -> ArrayList<ArrayList<Integer>>

        for (int i = 0; i < getColumns(); i++) {
            nextGen.add(new ArrayList<>(getRows()));
            for (int j = 0; j < getRows(); j++) {
                nextGen.get(i).add(0);
            }
        }

        for (int i = 0; i < proseccors; i++) {
            int min = i * getColumns() / proseccors;
            int max = (i + 1) * getColumns() / proseccors;
            workers.add(new Thread(() -> {
                for (int x = min; x < max; x++) {
                    for (int y = 0; y < getRows(); y++) {
                        nextGen.get(x).set(y, setCellRules(getValue(x, y), getNeighbours(x, y)));
                    }
                }
            }));
//            workers.add();

            //Dividing threads to workers
        }
        for (Thread t : workers) {
            t.start();
        }
        //Waiting for all threads to complete before they return

        for (Thread t : workers) {
            try {
                t.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        board = nextGen;
        expand();
//        setBoardSplitt();


        /*long start = System.currentTimeMillis();

        PrintNextGenerationPerformance(start, System.currentTimeMillis());
        System.out.println("boarsplitt = " + boardSplit);
        System.out.println("processor : " + proseccors);
        System.out.println("boar size : " + board.size());*/

    }


    /**
     * This method print to console time used to performe nextgeneration method
     * @param start
     * @param end
     * @return value as number of cells around one single cell, and calculate the time spent to execute the code.
     */
    public void PrintNextGenerationPerformance(long start, long end ){
        long time= end -start;
        System.out.println("Next generation time counter is :" + time);
    }

//    public void setBoardSplitt(){
//        this.boardSplit = (int) Math.ceil(board.size()/proseccors);
//    }

    /**
     * This method returning the number of neighbour cells for each cell.
     * The if loops checks if the patterns runs out the board, then it adds on the int totalNumbers
     * @param x is the first parameter in the getNeighbours method
     * @param y is the second parameter in the getNeighbours method
     * @return value as number of cells around one single cell
     */
    @Override
    public int getNeighbours(int x, int y) {
        int totalNumbers = 0;

        if (!(x - 1 == -1) && getValue(x-1,y) == 1) totalNumbers++;                                         //LEFT CENTER
        if (!(y - 1 == -1) && getValue(x,y-1) == 1) totalNumbers++;                                         //UP CENTER
        if (!(x - 1 == -1 || y - 1 == -1) && getValue(x-1,y-1) == 1) totalNumbers++;                      //UP LEFT
        if (!(x - 1 == -1 || y + 1 == getRows()) && getValue(x-1,y+1) == 1) totalNumbers++;            //DOWN LEFT
        if (!(y + 1 == getRows()) && getValue(x,y+1) == 1) totalNumbers++;                               //DOWN CENTER
        if (!(x + 1 == getColumns() || y - 1 == -1) && getValue(x+1,y-1) == 1) totalNumbers++;               //UP RIGHT
        if (!(x + 1 == getColumns() || y + 1 == getRows()) && getValue(x+1,y+1) == 1) totalNumbers++;     //DOWN RIGHT
        if (!(x + 1 == getColumns()) && getValue(x+1,y) == 1) totalNumbers++;                                  //CENTER RIGHT

        return totalNumbers;

    }

    /**
     * This method is checking if a cell is at the edge of the arraylist.
     * Different loops run through the numbers of the columns and checks if the cell is alive and then adds new elements as their neighbours
     * It also checks if the cell on the rows minus one is alive and then adds another column as its neighbour
     * Then another for loop adds a Arraylist as a column to the cells on the edge
     * The last point is to add the new List made in the loops to the Board Arraylist and add a row to it
     *
     * And if it is, it expands the arraylist to the minimum size needed to contain all cells.
     */
    public void expand() {

        ///////////////////CHECKS THE CELLS IN TOP OF ARRAY(TOP OF SCREEN)/////////////////////////
        for (int x = 0; x < getColumns(); x++) {

            if (getValue(x, 0) == 1) {

                for (int j = 0; j < getColumns(); j++) {
                    board.get(j).add(0, 0);
                }
                setRows(getRows() + 1);
            }

            /////////////////CHECKS THE CELLS IN THE BOTTOM OF ARRAY(BOTTOM OF SCREEN)/////////////////////////
            if (getValue(x, getRows() - 1) == 1) {

                for (int j = 0; j < getColumns(); j++) {
                    board.get(j).add(0);
                }
                setRows(getRows() + 1);
            }
        }

        ////////////////CHECKS THE CELLS IN LEFT SIDE OF ARRAY(LEFTSIDE OF SCREEN)/////////////////////////
        for (int y = 0; y < getRows(); y++) {

            if (getValue(0, y) == 1) {
                board.add(0, new ArrayList<Integer>());
                for (int j = 0; j < getRows(); j++) {
                    board.get(0).add(0);
                }
                setColumns(getColumns() + 1);
            }

            ///////////////CHECKS THE CELLS IN THE RIGHT SIDE OF ARRAY(RIGHTSIDE OF SCREEN)/////////////////////////
            if (getValue(getColumns() - 1, y) == 1) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int j = 0; j < getRows(); j++) {
                    list.add(0);
                }
                board.add(list);
                setColumns(getColumns() + 1);
            }
        }
    }

    /**
     * This method returns the values of the boards cells
     *
     * @param x is the first coordinate
     * @param y
     * @return value returning if cell is 1 = alive or 0 = dead.
     */
    @Override
    public int getValue(int x, int y) {
        return board.get(x).get(y);
    }

    /**
     * This method let us set the value of each cell. Either dead or alive using 1 = alive or 0 = dead.
     * @param x
     * @param y
     * @param value
     */
    @Override
    public void setValue(int x, int y, int value){
        board.get(x).set(y, value);
    }

    /**
     * This method converts an 2D int array to an Arraylist.
     * The RLE parser returns the file in the form of 2D array
     * and is nessesary to be converted for using it as a dynamicboard.
     * The board Arraylist is equal to the convertlist Arraylist with the columns and rows from boards size.
     * @param brett
     */
    @Override
    public int[][] setBrett(int[][] brett) {

        ArrayList<ArrayList<Integer>> convertList = new ArrayList<ArrayList<Integer>>();
        for (int x = 0; x < brett.length; x++){
            ArrayList<Integer> temp = new ArrayList<>();
            for(int y = 0; y < brett[x].length; y++){
                temp.add(brett[x][y]);
            }
            convertList.add(temp);
        }


        board = convertList;
        setColumns(board.size());
        setRows(board.get(0).size());

        return brett;
    }


    /**
     * This method returning board columns
     * @return columns
     */
    @Override
    public int getColumns() {
        return columns;
    }

    /**
     * This method returning toString method for class board
     * @return toString
     */
    @Override
    public String toString() {
        return null;
    }


    /**
     * This method returns board in form of arraylist.
     * @return board
     */
    public ArrayList<ArrayList<Integer>> getBoard() {
        return board;
    }

    /**
     * This method returning nextgeneration in the form of an arraylist.
     * @return nextGen
     */
    public ArrayList<ArrayList<Integer>> getNextGen() {
        return nextGen;
    }

    public void setNextGen(ArrayList<ArrayList<Integer>> nextGen) {
        this.nextGen = nextGen;
    }

    /**
     * This method returning cellSize
     * @return cellSize
     */
    @Override
    public double getCellSize() {
        return cellSize;
    }

    /**
     * @param cellSize parameter is assigned to the cellSize for this class
     */
    @Override
    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

//    public void setBoardSplitt() {
//
//        this.boardSplit = (int) Math.ceil(board.size() / proseccors);
//    }



}
