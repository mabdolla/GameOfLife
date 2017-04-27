package sample.Board;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mohammad Abdolla on 21.04.2017.
 */
public class DynamicBoard extends Brett {

    private ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>();
    private ArrayList<ArrayList<Integer>> nextGen;

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
    public int getValue(int x, int y){
        return board.get(x).get(y);
    }

    @Override
    public void setValue(int x, int y, int value){
        board.get(x).set(y, value);
    }

    @Override
    public void nextGeneration() {
        nextGen = new ArrayList<ArrayList<Integer>>(getRows());
        //lager blank nexGen -> ArrayList<ArrayList<Integer>>
        for (int i = 0; i < getRows(); i++) {
            nextGen.add(new ArrayList<Integer>(getColumns()));
            for (int j = 0; j < getColumns(); j++){
                nextGen.get(i).add(0);
            }
        }
        System.out.println("heisann" + nextGen.get(0).size());

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
                System.out.println(nextGen.size() +" rows 2");
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
        }

        /*
        if (getValue(x,y) == 1){
            if (x==0){

                //add på OPPE side
                System.out.println(x+", "+y+" i live og VENSTRE");
//                nextGen.add(0, new ArrayList<Integer>(getColumns()));
                for (int j = 0; j < getColumns(); j++){
                    nextGen.get(j).add(0,0);
                }
            }
            if (x == getRows()){
//                //add linje NEDE
//                System.out.println(x+", "+y+" i live og på kansten men ikke nabo");
//                nextGen.add(new ArrayList<Integer>(getColumns()));
//                for (int j = 0; j < getColumns(); j++){
//                    nextGen.get(getRows()).add(0);
//                }
            }
            if (y == 0){
                //add linje VENSTRE
                System.out.println(x+", "+y+" i live og på kansten men ikke nabo");
            }
            if (y == getColumns()){
                //add linje HØYRE
                System.out.println(x+", "+y+" i live og på kansten men ikke nabo");
            }
        }
        */
    }

//    public void convertToLine(){
//        ArrayList<ArrayList<Integer>> convertList = new ArrayList<ArrayList<Integer>>();
//        Arrays.asList(brett);
//    }

    @Override
    public int[][] setBrett(int[][] brett) {

        ArrayList<ArrayList<Integer>> convertList = new ArrayList<ArrayList<Integer>>();
        for (int x = 0; x < brett.length; x++){
            convertList.add(new ArrayList<>());
            for(int y = 0; y < brett[x].length; y++){
                convertList.get(x).add(brett[x][y]);
            }
        }

        board=convertList;
        setRows(board.size());
        setColumns(board.get(0).size());

//        setBrett(board);
        return brett;
    }



    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return null;
    }
}
