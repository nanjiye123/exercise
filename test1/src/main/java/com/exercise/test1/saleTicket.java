package com.exercise.test1;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {
    private int num = 50;
    Lock lock = new ReentrantLock();
    public void sale () {
        lock.lock();
        try {
            if (num > 0) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName());
                System.out.println(num--);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}


public class saleTicket {


    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) ticket.sale();
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) ticket.sale();
        },"B").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) ticket.sale();
        },"C").start();


    }
}
