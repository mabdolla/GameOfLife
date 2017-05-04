//package Controller;
//import sample.Board.DynamicBoard;
//
//import java.util.*;
//import java.util.concurrent.ExecutorService;
//
///**
// * Created by Mohammad Abdolla on 03.05.2017.
// */
//public class TreThread{
//    public static DynamicBoard dynamicBoard;
//    static final int processors = Runtime.getRuntime().availableProcessors();
////    static int i = 0;
// //   static int min = (i * dynamicBoard.getRows())/ processors;
// //   static int max = ((i+1) * dynamicBoard.getRows())/ processors;
//
////    public TreThread(DynamicBoard dynamicBoard){
////        this.dynamicBoard = dynamicBoard;
////
////    }
//
//    public static List<Thread> workers = new ArrayList<Thread>();
//    int min;
//    int max;
//
//    public static int getTotal() {
//        return total;
//    }
//
//    public static void setTotal(int total) {
//        TreThread.total = total;
//    }
//
//    private static int total;
//
//
//    public static ExecutorService newCachedThreadPool(){
//
//        return null;
//    }
//
//
//    public static synchronized void f(int min, int max) {
//       // min = dynamicBoard.getRows()/processors; //+++
//        System.out.println(min+ " "+ max);
//
//        for (int x = min; x < max; x++) {
//            for (int y = 0; y < dynamicBoard.getColumns(); y++) {
//                dynamicBoard.nextGen.get(x).set(y, dynamicBoard.setCellRules(dynamicBoard.getValue(x,y), dynamicBoard.getNeighbours(x, y)));
//            }
//        }
//
//        dynamicBoard.board = dynamicBoard.nextGen;
//        dynamicBoard.expand();
//
//
//
//        //        for (int x = 0; x < dynamicBoard.getRows(); x++) {
////            for (int y = 0; y < dynamicBoard.getColumns(); y++) {
////                dynamicBoard.nextGen.get(x).set(y, dynamicBoard.setCellRules(dynamicBoard.getValue(x,y), dynamicBoard.getNeighbours(x, y)));
////            }
////        }
//
//    }
////        min = i * dynamicBoard.getRows()/ processors;
////        max = ((i+1) * dynamicBoard.getRows())/ processors;
////        i++;
////    }
////
////        //for (int j = 0; j < ; j++) {
////
////        }
//////        for (int j = 0; j < workers.size(); j++) {
//////            dynamicBoard.getBoard().add(new ArrayList<Integer>());
//////        }
//////
//////        Thread t1 = new Thread("");
//////        workers.add(min, t1);
//////        workers.add(new Thread (()-> {System.out.println(min+ " and "+ max);}));
////
//////        System.out.println(dynamicBoard.getRows() + "Rows");
////        //System.out.println(min+ " "+ max);
////        //i++;
////    }
//
//    // opprett Java trådobjekter
//    public static void createWorkers() {
//        for (int i = 0; i < processors; i++) {
//            int min = i * dynamicBoard.getRows() / processors;
//            int max = (i+1) * dynamicBoard.getRows()/ processors;
//            workers.add(new Thread(() -> {
//                f((min),(max));
//                dynamicBoard.nextGen = new ArrayList<>(dynamicBoard.getRows());
//
//
//
////                for (int i = 0; i < dynamicBoard.getRows(); i++) {
////                    dynextGen.add(new ArrayList<>(getColumns()));
////                    for (int j = 0; j < getColumns(); j++){
////                        nextGen.get(i).add(0);
////                    }
////                }
////
////                for (int x = min; x < max; x++) {
////                    for (int y = 0; y < dynamicBoard.getRows(); y++) {
////                        dynamicBoard.nextGen.get(x).set(y, dynamicBoard.setCellRules(dynamicBoard.getValue(x,y), dynamicBoard.getNeighbours(x, y)));
////                    }
////                }
////                System.out.println(min + "1" + max);
//            }));
////            System.out.println(min+ " 2 "+ max);
//
//        }
//        System.out.println(Thread.currentThread().getId() + "tID");
//
//    }
//
//    // kjør trådobjektene
//    public static void runWorkers() throws InterruptedException {
//        for (Thread t : workers) {
//            t.start();
//            System.out.println("test");
//
//            t.isAlive();
//        }
//// vent på at alle trådene har kjørt ferdig før vi returnerer
//
//
//        for (Thread t : workers) {
//            t.join();
//        }
//
//        workers.clear();
//    }
//
//    public void nextGenerationConcurrentPrintPerformance() {}
//
////    public static List<Thread> workers = new ArrayList<Thread>();
////    public void nextGenerationConcurrent() {
////        //lag nextGenBoard = new array-.---..
////        nextGen = new ArrayList<>(getRows());
////        //lager blank nexGen -> ArrayList<ArrayList<Integer>>
////        for (int i = 0; i < getRows(); i++) {
////            nextGen.add(new ArrayList<>(getColumns()));
////            for (int j = 0; j < getColumns(); j++){
////                nextGen.get(i).add(0);
////            }
////        }
////
////        for (int i = 0; i < processors; i++) {
////            int min = i * dynamicBoard.getRows() / processors;
////            int max = (i+1) * dynamicBoard.getRows()/ processors;
////            workers.add(new Thread(() -> {
////                for (int x = min; x < max; x++) {
////                    for (int y = 0; y < dynamicBoard.getColumns(); y++) {
////                        dynamicBoard.nextGen.get(x).set(y, dynamicBoard.setCellRules(dynamicBoard.getValue(x,y), dynamicBoard.getNeighbours(x, y)));
////                    }
////                }
////            }));
////
////        }
////        for (Thread t : workers) {
////            t.start();
////        }
////// vent på at alle trådene har kjørt ferdig før vi returnerer
////        for (Thread t : workers) {
////            try {
////                t.join();
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////        }
////
////        board = nextgen;
////        expand();
////
////
////    }
//
////
////    public static int getRows() {
////        return dynamicBoard.getRows();
////    }
////
////    public static int getColumns() {
////        return dynamicBoard.columns;
////    }
//}
