package com.exercise.test1.lock8;


import java.util.concurrent.TimeUnit;



/**
 * 标准情况下 是先sendEmail()　还是先callPhone()?
 * 答案：sendEmail
 * 解释：synchronized 锁的是Phone1类
 * 两个方法调用的对象是同一个，先调用的先执行！
 */
class Phone1 {
    public synchronized void sendEmail() {
        System.out.println("sendEmail");
    }

    public synchronized void callPhone() {
        System.out.println("callPhone");
    }
}

public class LockDemo1 {
    public static void main(String[] args) throws InterruptedException {
        Phone1 phone1 = new Phone1();
        new Thread(()->{
            phone1.sendEmail();
        },"A").start();
        TimeUnit.SECONDS.sleep(3);
        new Thread(()->{
            phone1.callPhone();
        },"B").start();
    }
}
