package com.exercise.test1.lock8;

import java.util.concurrent.TimeUnit;


/**
 * 被synchronized 修饰的不同方法 先执行sendEmail() 还是callPhone()？
 * 答案：callPhone
 * 解释：被synchronized 修饰的不同方法 锁的是对象
 * 这里锁的是两个不同的调用者，所有互不影响
 */
public class LockDemo4 {
    public static void main(String[] args) throws InterruptedException {
        Phone4 phoneA = new Phone4();
        Phone4 phoneB = new Phone4();

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
class Phone4{
    public synchronized void sendEmail() throws InterruptedException {
        System.out.println("sendE");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendEmail");
    }
    public synchronized void callPhone(){
        System.out.println("callPhone");
    }
}
