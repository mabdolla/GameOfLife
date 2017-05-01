import java.lang.*;
import java.lang.Thread;

/**
 * Created by Hans Jacob on 27.04.2017.
 */
public class Run {
    public static void main(String[] args) {


        System.out.println("Ran thread " +
                Thread.currentThread().getId());
        Thread thread = new Thread(() -> {
            System.out.println("Ran thread " +
                    Thread.currentThread().getId());
        });

        thread.start();

    }

        public void nextGenPrintPerformance() {
        long start = System.currentTimeMillis();
        countNeighbours();
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Counting time (ms): " + elapsed);


    }

    private void countNeighbours() {
    }

    public void nextGenCurrentPrintPerformance() {
    int numWorkers = Runtime.getRuntime().availableProcessors();
    }

    public void nextGenCurrent() {

    }





        /*Thread T1 = new Thread(new Thread("one"));
        Thread T2 = new Thread(new Thread("two"));
        Thread T3 = new Thread(new Thread("three"));
        Thread T4 = new Thread(new Thread("four"));

        T1.start();
        T2.start();
        T3.start();
        T4.start();*/

    }

