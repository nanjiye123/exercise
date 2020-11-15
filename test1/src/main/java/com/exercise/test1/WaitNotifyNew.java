package com.exercise.test1;

/**
 * 题目：两个线程，操作初始值0的一个变量，
 * 实现两个个线程对该变量加1，两个个线程对该变量减1，
 * 交替10次
 *
 * ReentrantLock版本
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 解答：生产者消费者
 */
class AtomiNumNew {

    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (num > 0) {//此处不能用if，有虚假唤醒问题
                condition.await();
            }
            //干活
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            //通知
            //this.notifyAll();
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (num <= 0) {//此处不能用if，有虚假唤醒问题
                condition.await();
            }
            //干活
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            //通知
//            this.notifyAll();
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class WaitNotifyNew {
    public static void main(String[] args) {
        AtomiNumNew atomiNumNew = new AtomiNumNew();
        new Thread(()->{
            for (int i=0; i<10; i++) {
                try {
                    atomiNumNew.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"A").start();
        new Thread(()->{
            for (int i=0; i<10; i++) {
                try {
                    atomiNumNew.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"B").start();
        new Thread(()->{
            for (int i=0; i<10; i++) {
                try {
                    atomiNumNew.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"C").start();
        new Thread(()->{
            for (int i=0; i<10; i++) {
                try {
                    atomiNumNew.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"D").start();
    }
}
