package com.pbo2.classPrep5.video2;

public class DaemonApp {
    public static void main(String[] args) {
        var thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("Run thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.setDaemon(true);
        thread.start();
    }
}
