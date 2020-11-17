package com.exercise.test1.lock8;

import java.util.concurrent.TimeUnit;


/**
 * 被synchronized 修饰的普通方法和静态方法  是先sendEmail() 还是 callPhone()?
 * 答案：callPhone
 * 解释：只要被static修饰锁的是class, 而synchronized 锁的是对象
 * 这里是两个锁互不影响，按时间先后执行
 */
public class LockDemo6 {
    public static void main(String[] args) throws InterruptedException {
        Phone6 phone6 = new Phone6();
        new Thread(()->{
            try {
                phone6.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            phone6.callPhone();
        },"B").start();
    }
}
class Phone6{
    public static synchronized void sendEmail() throws InterruptedException {
        System.out.println("sendE");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendEmail");
    }

    public synchronized void callPhone(){
        System.out.println("callPhone");
    }
}
