package Threads;

import sample.Board.DynamicBoard;

import java.lang.*;
import java.lang.Thread;
import java.util.ArrayList;

/**
 * Created by Fredrik Kluftødegård on 01.05.2017.
 */
public class WorkerThread extends DynamicBoard implements Runnable {

    DynamicBoard dynamicBoard;
    int processor = Runtime.getRuntime().availableProcessors();
    int boardSplit ;
    public static int boardSize = 0;
    ArrayList<Integer> threadboard;

    public WorkerThread (int processor, int boardSplit){
        this.processor = processor;
        this.boardSplit = boardSplit;

        threadboard = new ArrayList<>(dynamicBoard.getRows());
        for (int i = 0; i < getRows(); i++) {
            nextGen.add(new ArrayList<>(getColumns()));
            for (int j = 0; j < getColumns(); j++) {
                nextGen.get(i).add(0);
            }

    }}



    @Override
    public void run() {
        Thread t1 = new Thread(){


        };
        Thread t2 = new Thread(){

        };
    }
}
