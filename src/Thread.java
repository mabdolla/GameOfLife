/**
 * Created by Hans Jacob on 27.04.2017.
 */

import java.lang.*;
import java.util.Random;
import java.util.*;

public class Thread implements Runnable {

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




    

   /* public static void main(String[] args) {
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

    }*/


}
