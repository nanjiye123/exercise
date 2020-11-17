package com.exercise.test1.lock8;

import java.util.concurrent.TimeUnit;


/**
 * 一个被static+synchronized 修饰的方法和普通的synchronized方法，先执行sendEmail()还是callPhone()？
 * 答案：callPhone()
 * 解释： 只要被static 修饰的锁的就是整个class模板
 * 这里一个锁的是class模板 一个锁的是调用者
 * 所以锁的是两个对象 互不影响
 */

public class LockDemo8 {
    public static void main(String[] args) throws InterruptedException {
        Phone8 phoneA = new Phone8();
        Phone8 phoneB = new Phone8();

        new Thread(()->{
            try {
                phoneA.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            phoneB.callPhone();
        },"B").start();
    }
}
class Phone8{
    public static synchronized void sendEmail() throws InterruptedException {
        System.out.println("sendE");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendEmail");
    }

    public synchronized void callPhone(){
        System.out.println("callPhone");
    }
}

