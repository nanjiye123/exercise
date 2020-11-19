package com.exercise.test1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {


    public static void main(String[] args) throws Exception {
        //无返回值异步
        CompletableFuture<Void> completableFuture1 =
                CompletableFuture.runAsync(() -> {
                    System.out.println(Thread.currentThread().getName()
                            + "\t completableFuture1");
                });
        completableFuture1.get();

        System.out.println("this is main1");

        //异步回调
        CompletableFuture<Integer> completableFuture2 =
            CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "completableFuture2");
                int i = 10 / 0;
                return 1024;
            });
        completableFuture2.whenComplete((t, u) -> {
            //正常结束
            System.out.println("-------t=" + t);
            System.out.println("-------u=" + u);
        }).exceptionally(f -> {
            //异常结束
            System.out.println("-----exception:" + f.getMessage());
            return 444;
        }).get();
    }

}
