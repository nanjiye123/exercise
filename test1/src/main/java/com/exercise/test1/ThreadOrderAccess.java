package com.exercise.test1;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：多线程之间按照顺序调用，实现A->B->C
 * AA打印5次，BB打印10次，CC打印15次
 * 。。。循环10轮
 *
 * 考察ReentrantLock的顺序控制
 */

class ShareResource {
    private int num = 1;//1:A,2:B,3:C
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            // 1.判断
            while (num != 1) {
                condition1.await();
            }
            //2 干活
            for (int i=1; i<=5; i++) {
                System.out.println(Thread.currentThread().getName() + i);
            }
            //3修改标志位，通知下一个线程
            num = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            // 1.判断
            while (num != 2) {
                condition1.await();
            }
            //2 干活
            for (int i=1; i<=10; i++) {
                System.out.println(Thread.currentThread().getName() + i);
            }
            //3修改标志位，通知下一个线程
            num = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            // 1.判断
            while (num != 3) {
                condition1.await();
            }
            //2 干活
            for (int i=1; i<=15; i++) {
                System.out.println(Thread.currentThread().getName() + i);
            }
            //3修改标志位，通知下一个线程
            num = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class ThreadOrderAccess {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            shareResource.print5();
        },"A").start();

        new Thread(()->{
            shareResource.print10();
        },"B").start();

        new Thread(()->{
            shareResource.print15();
        },"C").start();
    }
}
