package com.exercise.test1.lock8;

import java.util.concurrent.TimeUnit;


/**
 * 两个静态同步方法 都被synchronized 修饰 是先sendEmail() 还是callPhone()？
 * 答案：sendEmial
 * 解释：只要方法被 static 修饰，锁的对象就是 Class模板对象,这个则全局唯一！
 *      所以说这里是同一个锁，并不是因为synchronized  这里程序会从上往下依次执行
 */
public class LockDemo5 {
    public static void main(String[] args) throws InterruptedException {
        Phone5 phone5 = new Phone5();
        new Thread(()->{
            try {
                phone5.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            phone5.callPhone();
        },"B").start();
    }
}
class Phone5{
    public static synchronized void sendEmail() throws InterruptedException {
        System.out.println("sendE");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendEmail");
    }

    public static synchronized void callPhone(){
        System.out.println("callPhone");
    }
}
