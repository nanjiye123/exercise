package com.exercise.test1;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private volatile Map<String, String> map = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public void put(String k, String v) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始写入");
            map.put(k, v);
            System.out.println(Thread.currentThread().getName() + "写入完成");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    public void get(String k) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始读取");
            String s = map.get(k);
            System.out.println(Thread.currentThread().getName() + "读取完成" + s);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache.put(finalI + "", finalI + "");
            }, i + "").start();
        }
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache.get(finalI + "");
            }, i + "").start();
        }
    }

}
