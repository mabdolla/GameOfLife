/**
 * Created by Hans Jacob on 27.04.2017.
 */

import java.util.*;
public class Thread {
    /*static int a = 0;
    static List<Thread> workers = new ArrayList<Thread>();
    public static void f() {
        a++;
    }
    // opprett Java trådobjekter
   /public static void createWorkers() {
        for(int i = 0; i < 10; i++) {
            workers.add(new Thread(() -> {f();}));
        }
    }
    // kjør trådobjektene
    public static void runWorkers() throws InterruptedException {
        for(Thread t : workers) {
            t.start();
        }
// vent på at alle trådene har kjørt ferdig før vi returnerer
        for(Thread t : workers) {
            t.join();
        }
    }

    private void start() {
    }

    private void join() {

    }

    public static void main(String[] args) throws InterruptedException {
        createWorkers();
        runWorkers();
// når en tråd har kjørt en gang, blir den ubrukelig.
// vi kan derfor slette tråd-objektene
        workers.clear();
        System.out.println(a);
        createWorkers();
        runWorkers();
        System.out.println(a);
    }
}


    /*
    String name;
  int time;
  Random r = new Random();

  public Thread (String x) {
      name = x;
      time = r.nextInt(999);

  }

  public void run() {
      try {
          System.out.printf("%s is sleeping for%d/n", name, time);
          java.lang.Thread.sleep(time);
          System.out.printf("%s is done");

      }catch(Exception e) {}

    }






    

    public static void main(String[] args) {
        System.out.println("Thread " + Thread.currentThread().getId());

        Thread thread = new Thread(() -> {
            System.out.println("Thread " + Thread.currentThread().getId());
        });
        thread.start();
    }

    private static void currentThread() {
    }

    @Override
    public void run() {

    }

    public void start() {
*/
    }



