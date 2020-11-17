package com.exercise.test1.lock8;

import java.util.concurrent.TimeUnit;


/**
 * sendEmail()休眠三秒后  是先执行sendEmail() 还是 callPhone()
 * 答案： sendEmail
 * 解释：被 synchronized 修饰的方式，锁的对象是方法的调用者
 * 所以说这里两个方法调用的对象是同一个，先调用的先执行！
 */

public class LockDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Phone2 phone2 = new Phone2();
        new Thread(()->{
            try {
                phone2.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        TimeUnit.SECONDS.sleep(1); // 休眠2秒
        new Thread(()->{
            phone2.callPhone();
        },"B").start();
    }
}
class Phone2{
    public synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("sendEmail");
    }
    public synchronized void callPhone(){
        System.out.println("callPhone");
    }
}
