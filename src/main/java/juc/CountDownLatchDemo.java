package juc;

import java.util.concurrent.CountDownLatch;

/**
 * @Author gaoxing
 * @Date 2020-06-26 15:55
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                long count = countDownLatch.getCount();
                System.out.println(Thread.currentThread().getName() + "start: " +count);
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "end");
            }, "T_" +i).start();
        }

        // 阻塞等待countDownLatch值为0
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main");
    }
}
