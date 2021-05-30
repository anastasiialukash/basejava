package com.urise.webapp.concurency;

public class Test {
    private final String sharedResource;

    public Test(String sharedResource) {
        this.sharedResource = sharedResource;
    }
    public String getSharedResource() {
        return this.sharedResource;
    }
    public synchronized void test1(Test test) throws InterruptedException {
        System.out.println(this.sharedResource + " has " + test.getSharedResource());
        Thread.sleep(1000);
        test.test2(this);
    }
    public synchronized void test2(Test test) {
        System.out.println(this.sharedResource + " has " + test.getSharedResource());
    }
}
