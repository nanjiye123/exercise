package com.exercise.test1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤");
        });

        for (int i = 1; i<=7;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("收集第" +Thread.currentThread().getName() + "颗" );
                    try {
                        cyclicBarrier.await();
                        //等待被唤醒,唤醒后的操作
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            },String.valueOf(i)).start();
        }
    }



}
