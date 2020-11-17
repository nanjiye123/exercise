package com.exercise.test1;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    private static final int CLASSAMTE_NUMBER = 6;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(CLASSAMTE_NUMBER);
        for (int i = 1; i <= CLASSAMTE_NUMBER; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" \t 同學离开");
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 班长锁门,必须最后离开");

    }
}
