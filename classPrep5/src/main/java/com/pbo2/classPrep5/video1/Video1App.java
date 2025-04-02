package com.pbo2.classPrep5.video1;

public class Video1App {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Multithread myThing = new Multithread(i);
            Thread myThread = new Thread(myThing);
            
            // Use `start` to run in a separate thread
            myThread.start();
            
            // Use `run` to run in the same thread
            // myThread.run();

            // Use `join` to wait for the thread to finish
            /* try {
                myThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } */

            // Use `isAlive` to check if the thread is running
            // if (myThread.isAlive()) {
            //     System.out.println("Thread is still running");
            // }
        }
    }
}
