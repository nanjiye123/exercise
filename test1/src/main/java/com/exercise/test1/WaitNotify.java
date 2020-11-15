package com.exercise.test1;


/**
 * 题目：两个线程，操作初始值0的一个变量，
 * 实现两个个线程对该变量加1，两个个线程对该变量减1，
 * 交替10次
 */

/**
 * 解答：生产者消费者
 */
class AtomiNum {
    private int num = 0;
    public synchronized void increment() throws InterruptedException {
        //判断
        while (num > 0) {//此处不能用if，有虚假唤醒问题
            this.wait();
        }
        //干活
        num++;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        //通知
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        //判断
        while (num <= 0) {//此处不能用if，有虚假唤醒问题
            this.wait();
        }
        //干活
        num--;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        //通知
        this.notifyAll();
    }
}

public class WaitNotify {
    public static void main(String[] args) {
        AtomiNum atomiNum = new AtomiNum();
        new Thread(()->{
            for (int i=0; i<10; i++) {
                try {
                    atomiNum.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"A").start();
        new Thread(()->{
            for (int i=0; i<10; i++) {
                try {
                    atomiNum.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"B").start();
        new Thread(()->{
            for (int i=0; i<10; i++) {
                try {
                    atomiNum.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"C").start();
        new Thread(()->{
            for (int i=0; i<10; i++) {
                try {
                    atomiNum.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"D").start();
    }
}
