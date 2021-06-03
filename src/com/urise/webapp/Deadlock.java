package com.urise.webapp;

public class Deadlock {

    public static final String LOCK_ONE = "LOCK_ONE";
    public static final String LOCK_TWO = "LOCK_TWO";

    public static void main(String[] args) {
        deadLock(LOCK_ONE, LOCK_TWO);
        deadLock(LOCK_TWO, LOCK_ONE);
    }

    private static void deadLock(Object lockOne, Object lockTwo) {
        new Thread(() -> {
            synchronized (lockOne) {
                System.out.println("Holding " + lockOne);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting for " + lockTwo);
                synchronized (lockTwo) {
                    System.out.println("Holding " + lockTwo);
                }
            }
        }).start();
    }
}
