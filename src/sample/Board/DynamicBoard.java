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

    private ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>(getRows());
    private ArrayList<ArrayList<Integer>> nextGen;

    public DynamicBoard(int rad, int kolonne, GraphicsContext gc, Canvas canvas) {
        super(rad, kolonne, gc, canvas);

        for (int i = 0; i < getRows(); i++) {
            board.add(new ArrayList<Integer>(getRows()));
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
        System.out.println(nextGen.get(0).size());

        //beregning
        for (int x = 0; x < getRows(); x++) {
            for (int y = 0; y < getColumns(); y++) {
                nextGen.get(x).set(y, setCellRules(getValue(x,y), getNeighbours(x, y)));
            }
        }

        board = nextGen;
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

        if (getValue(x,y) == 1){
            if (x==0){
                //add på OPPE side
                System.out.println(x+", "+y+" i live og VENSTRE");
//                nextGen.add(0, new ArrayList<Integer>(getColumns()));
                for (int j = 0; j < getColumns(); j++){
                    nextGen.get(0).add(0);
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

        return antallNaboer;
    }

    @Override
    public String toString() {
        return null;
    }
}
