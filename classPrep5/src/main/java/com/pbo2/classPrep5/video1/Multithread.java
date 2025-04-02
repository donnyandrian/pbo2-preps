package com.pbo2.classPrep5.video1;

public class Multithread implements Runnable {
    private int threadNumber;

    public Multithread(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(i + " from thread " + threadNumber);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
