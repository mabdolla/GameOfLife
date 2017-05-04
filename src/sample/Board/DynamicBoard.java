package sample.Board;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The Game Of Life application created for HIOA
 * The DynamicBoard class contains constructor and methods for creating and using arraylist as board.
 * Next generation method updating the board according to rules you will find in this class.
 * Next generation method is also running on Threads based on x number of processors from your computer.
 *
 * @author Fredrik, Hans Jacob, Mohammad
 *         Studentnr : S309293, s305064, s309856
 */
public class DynamicBoard extends StaticBoard {

    public ArrayList<ArrayList<Integer>> board = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> nextGen;

    final int proseccors = Runtime.getRuntime().availableProcessors();


    double cellSize = 9;
    int boardSplit;

    /**
     * Constructs and init a board with columns, rows, gc and canvas.
     *
     * @param rows    is the first parameter in DynamicBoard constructor.
     * @param columns is the second parameter in DynamicBoard constructor.
     * @param gc      is the third parameter used for drawing.
     * @param canvas  is the fourth parameter in DynamicBoard constructor which is used to draw onto.
     * @see #StaticBoard (int, int, GraphicsContext, Canvas) (String)
     */
    public DynamicBoard(int rows, int columns, GraphicsContext gc, Canvas canvas) {
        super(rows, columns, gc, canvas);

        for (int i = 0; i < getRows(); i++) {
            board.add(new ArrayList<Integer>());
            for (int j = 0; j < getColumns(); j++) {
                board.get(i).add(0);
            }
        }
    }


    /**
     * calculates the next generation and updates the board.
     */
    public void nextGenerationConcurrent() {
        List<Thread> workers = new ArrayList<Thread>();
        //lag nextGenBoard = new array-.---..
        nextGen = new ArrayList<>(getRows());

        //Creates an empty arraylist called nextgen -> ArrayList<ArrayList<Integer>>

        for (int i = 0; i < getRows(); i++) {
            nextGen.add(new ArrayList<>(getColumns()));
            for (int j = 0; j < getColumns(); j++) {
                nextGen.get(i).add(0);
            }
        }

        for (int i = 0; i < proseccors; i++) {
            int min = i * getRows() / proseccors;
            int max = (i + 1) * getRows() / proseccors;
            workers.add(new Thread(() -> {
                for (int x = min; x < max; x++) {
                    for (int y = 0; y < getColumns(); y++) {
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
        setBoardSplitt();


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
     * @param x is the first parameter in the getNeighbours method
     * @param y is the second parameter in the getNeighbours method
     * @return value as number of cells around one single cell
     */
    @Override
    public int getNeighbours(int x, int y) {
        int antallNaboer = 0;

        if (!(x - 1 == -1) && getValue(x-1,y) == 1) antallNaboer++;                                         //LEFT CENTER
        if (!(y - 1 == -1) && getValue(x,y-1) == 1) antallNaboer++;                                         //UP CENTER
        if (!(x - 1 == -1 || y - 1 == -1) && getValue(x-1,y-1) == 1) antallNaboer++;                      //UP LEFT
        if (!(x - 1 == -1 || y + 1 == getColumns()) && getValue(x-1,y+1) == 1) antallNaboer++;            //DOWN LEFT
        if (!(y + 1 == getColumns()) && getValue(x,y+1) == 1) antallNaboer++;                               //DOWN CENTER
        if (!(x + 1 == getRows() || y - 1 == -1) && getValue(x+1,y-1) == 1) antallNaboer++;               //UP RIGHT
        if (!(x + 1 == getRows() || y + 1 == getColumns()) && getValue(x+1,y+1) == 1) antallNaboer++;     //DOWN RIGHT
        if (!(x + 1 == getRows()) && getValue(x+1,y) == 1) antallNaboer++;                                  //CENTER RIGHT

        return antallNaboer;

    }

    /**
     * This method is checking if a cell is at the edge of the arraylist.
     * And if it is, it expands the arraylist to the minimum size needed to contain all cells.
     */
    public void expand() {

        ///////////////////CHECKS THE CELLS IN TOP OF ARRAY(TOP OF SCREEN)/////////////////////////

        for (int x = 0; x < getRows(); x++) {


            if (getValue(x, 0) == 1) {


                //System.out.println("Found cells on top");

                for (int j = 0; j < getRows(); j++) {
                    board.get(j).add(0, 0);
                }
                setColumns(getColumns() + 1);
            }
            /////////////////CHECKS THE CELLS IN THE BOTTOM OF ARRAY(BOTTOM OF SCREEN)/////////////////////////

            if (getValue(x, getColumns() - 1) == 1) {
                //System.out.println("Found cell at bottom");

                for (int j = 0; j < getRows(); j++) {
                    board.get(j).add(0);
                }
                setColumns(getColumns() + 1);
            }
        }
        ////////////////CHECKS THE CELLS IN LEFT SIDE OF ARRAY(LEFTSIDE OF SCREEN)/////////////////////////

        for (int y = 0; y < getColumns(); y++) {
            if (getValue(0, y) == 1) {
                //System.out.println("Found cell on lefthand");

                board.add(0, new ArrayList<Integer>());
                for (int j = 0; j < getColumns(); j++) {
                    board.get(0).add(0);
                }

                setRows(getRows() + 1);
            }

            ///////////////CHECKS THE CELLS IN THE RIGHT SIDE OF ARRAY(RIGHTSIDE OF SCREEN)/////////////////////////

            if (getValue(getRows() - 1, y) == 1) {
                //System.out.println("Found cells on righthand");

                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int j = 0; j < getColumns(); j++) {
                    list.add(0);
                }
                board.add(list);

                setRows(getRows() + 1);
            }
        }
    }

    /**
     * This method returning value in arraylist. Returning if cell is alive or dead.
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
     * @param brett
     */
    @Override
    public int[][] setBrett(int[][] brett) {

        ArrayList<ArrayList<Integer>> convertList = new ArrayList<ArrayList<Integer>>();
        for (int x = 0; x < brett.length; x++){
            convertList.add(new ArrayList<>());
            for(int y = 0; y < brett[x].length; y++){
                convertList.get(x).add(brett[x][y]);
            }
        }

        board = convertList;
        setRows(board.size());
        setColumns(board.get(0).size());

        return brett;
    }


    /**
     * This method returning board rows
     * @return rows
     */
    @Override
    public int getRows() {
        return rows;
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

    public void setBoardSplitt() {

        this.boardSplit = (int) Math.ceil(board.size() / proseccors);
    }



}
