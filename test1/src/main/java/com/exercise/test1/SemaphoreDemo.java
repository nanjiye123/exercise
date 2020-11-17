package com.exercise.test1;

import ch.qos.logback.core.util.TimeUtil;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    //车位个数
    private static final int PARK_LOT = 2;
    //车辆数
    private static final int CARS = 6;

    private static Semaphore semaphore = new Semaphore(PARK_LOT);

    public static void main(String[] args) {
        for (int i = 1; i <= CARS; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    //看看有没有空车位
//                    if (semaphore.availablePermits() == 0) {
//                        System.out.println("第" + finalI + "辆司机看了看");
//                    }
                    //尝试进入停车位
                    semaphore.acquire();
                    System.out.println("第" + finalI + "成功进入停车场");
                    TimeUnit.SECONDS.sleep(2); //等待4秒钟
                    System.out.println("第" + finalI + "驶出停车场");
                    //离开停车场
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }
}
