package com.urise.webapp;

public class Deadlock {

    public static final Object LOCK_ONE = new Object();
    public static final Object LOCK_TWO = new Object();

    public static void main(String[] args) {
        ThreadOne threadOne = new ThreadOne();
        ThreadTwo threadTwo = new ThreadTwo();

        threadOne.start();
        threadTwo.start();
    }

    private static class ThreadOne extends Thread {
        public void run() {
            synchronized (LOCK_ONE) {
                System.out.println("ThreadOne is holding LOCK_ONE");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting...");
                synchronized (LOCK_TWO) {
                    System.out.println("ThreadOne is holding LOCK_ONE and LOCK_TWO");
                }
            }
        }
    }

    private static class ThreadTwo extends Thread {
        public void run() {
            synchronized (LOCK_TWO) {
                System.out.println("ThreadTwo is holding LOCK_ONE");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting...");
                synchronized (LOCK_ONE) {
                    System.out.println("ThreadTwo is holding LOCK_ONE and LOCK_TWO");
                }
            }
        }
    }
}
