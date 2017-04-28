package sample.Board;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;

/**
 * The Game Of Life application created for HIOA
 * The Controller class is the fx for fxml, all the features in fxml are assigned in this class.
 * The class is also implementing Initializable interface.
 *
 * @author Fredrik, Hans-Jacob, Mohammad
 * Studentnr : S309293, s
 */
public class DynamicBoard extends Brett {

    public ArrayList<ArrayList<Integer>> board = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> nextGen;
    public int cellSize = 9;

    /**
     *
     *  Constructs and init a board with columns, rows, gc and canvas.
     *
     *  @param rows is the first parameter in DynamicBoard constructor.
     *  @param columns is the second parameter in DynamicBoard constructor.
     *  @param gc is the third parameter used for drawing.
     *  @param canvas is the fourth parameter in DynamicBoard constructor which is used to draw onto.
     */
    public DynamicBoard(int rows, int columns, GraphicsContext gc, Canvas canvas) {
        super(rows, columns, gc, canvas);

        for (int i = 0; i < getRows(); i++) {
            board.add(new ArrayList<Integer>());
            for (int j = 0; j < getColumns(); j++){
                board.get(i).add(0);
            }
        }
    }


    @Override
    public void nextGeneration() {
        nextGen = new ArrayList<>(getRows());
        //lager blank nexGen -> ArrayList<ArrayList<Integer>>
        for (int i = 0; i < getRows(); i++) {
            nextGen.add(new ArrayList<>(getColumns()));
            for (int j = 0; j < getColumns(); j++){
                nextGen.get(i).add(0);
            }
        }

        //beregning
        for (int x = 0; x < getRows(); x++) {
            for (int y = 0; y < getColumns(); y++) {
                nextGen.get(x).set(y, setCellRules(getValue(x,y), getNeighbours(x, y)));
            }
        }

        board = nextGen;
        expand();

    }

    @Override
    public int getNeighbours(int x, int y) {
        int antallNaboer = 0;

        if (!(x - 1 == -1) && getValue(x-1,y) == 1) antallNaboer++;                                         //Venstre midten
        if (!(y - 1 == -1) && getValue(x,y-1) == 1) antallNaboer++;                                         //Oppe midten
        if (!(x - 1 == -1 || y - 1 == -1) && getValue(x-1,y-1) == 1) antallNaboer++;                      //Oppe venstre
        if (!(x - 1 == -1 || y + 1 == getColumns()) && getValue(x-1,y+1) == 1) antallNaboer++;            //Nede venstre
        if (!(y + 1 == getColumns()) && getValue(x,y+1) == 1) antallNaboer++;                               //Nede midten
        if (!(x + 1 == getRows() || y - 1 == -1) && getValue(x+1,y-1) == 1) antallNaboer++;               //Oppe høyre
        if (!(x + 1 == getRows() || y + 1 == getColumns()) && getValue(x+1,y+1) == 1) antallNaboer++;     //Nede høyre
        if (!(x + 1 == getRows()) && getValue(x+1,y) == 1) antallNaboer++;                                  //Midten høyre

        return antallNaboer;

    }

    public void expand(){
        for (int x = 0; x < getRows(); x++) {
            if (getValue(x,0) == 1){ // Sjeker alle på oppe

                System.out.println(getRows() +" rows");
                System.out.println(nextGen.get(0) +" rows 2");
                System.out.println(getColumns() + " coll");
                System.out.println(nextGen.get(0).size() + " coll2");

                System.out.println("fantes oppe");

                for (int j = 0; j < getRows(); j++){
                    board.get(j).add(0,0);
                }
                setColumns(getColumns()+1);
            }
            if (getValue(x, getColumns()-1) == 1){ // Sjekker alle på nede linje
                System.out.println("fantes nede");

                for (int j = 0; j < getRows(); j++){
                    board.get(j).add(0);
                }
                setColumns(getColumns()+1);
            }
        }
        for (int y = 0; y < getColumns(); y++) {
            if (getValue(0, y) == 1){
                System.out.println("fantes venstre");

                board.add(0, new ArrayList<Integer>());
                for (int j = 0; j < getColumns(); j++){
                    board.get(0).add(0);
                }

                setRows(getRows()+1);
            }
            if (getValue(getRows()-1, y) == 1){
                System.out.println("fantes Høyre");

                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int j = 0; j < getColumns(); j++){
                    list.add(0);
                }
                board.add(list);

                setRows(getRows() +1);
            }
        }}

        ///////////////TRYING TO MOVE ARRAYLIST ACCORDING TO KEY PRESSED

        public ArrayList<ArrayList<Integer>> moveCellsLeft(KeyEvent event){

            ArrayList<ArrayList<Integer>> moveCellsLeft = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < getRows()-1; i++) {
            board.add(new ArrayList<Integer>());
            for (int j = 0; j < getColumns(); j++){
                board.get(i).add(0);
            }
        }return moveCellsLeft(event);

                }


    @Override
    public int getValue(int x, int y){
        return board.get(x).get(y);
    }

    @Override
    public void setValue(int x, int y, int value){
        board.get(x).set(y, value);
    }
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

//    public void convertToLine(){
//        ArrayList<ArrayList<Integer>> convertList = new ArrayList<ArrayList<Integer>>();
//        Arrays.asList(brett);
//    }







    @Override
    public String toString() {
        return null;
    }


    public ArrayList<ArrayList<Integer>> getBoard() {
        return board;
    }


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
    public int getCellSize() {
        return cellSize;
    }

    /**
     * @param cellSize parameter is assigned to the cellSize for this class
     */
    @Override
    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

}
