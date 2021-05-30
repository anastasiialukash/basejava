package com.urise.webapp.concurency;

public class Deadlock {

    public static void main(String[] args) {
        final Test first = new Test("sharedResource1");
        final Test second = new Test("sharedResource2");

        new Thread(() -> {
            try {
                first.test1(second);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                second.test1(first);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
