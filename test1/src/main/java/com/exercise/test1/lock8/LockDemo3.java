package com.exercise.test1.lock8;

import java.util.concurrent.TimeUnit;



/**
 * 被synchronized 修饰的方式和普通方法 先执行sendEmail() 还是 callPhone()
 * 答案： callPhone
 * 解释：新增加的这个方法没有 synchronized 修饰，不是同步方法，不受锁的影响！
 */
public class LockDemo3 {
    public static void main(String[] args) throws InterruptedException {
        Phone3 phone3 = new Phone3();
        new Thread(()->{
            try {
                phone3.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        TimeUnit.SECONDS.sleep(2);
        new Thread(()->{
            phone3.callPhone();
        },"B").start();
    }
}
class Phone3{
    public synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("sendEmail");
    }

    // 没有synchronized 没有static 就是普通方式
    public void callPhone(){
        System.out.println("callPhone");
    }
}
