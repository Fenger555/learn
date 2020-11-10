package juc;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @Author gaoxing
 * @Date 2020-06-26 16:40
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                int t=0;
                int wait = 0;
                try {
                    wait = (int)(Math.random()*2000.0);
                    Thread.sleep(wait);
                    int available = semaphore.availablePermits();
                    System.out.println(String.format("%s：需要车位 - %s 当前可用:%s", Thread.currentThread().getName(), wait, available));
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "：停车");
                    t = (int)(Math.random()*4000.0);
                    Thread.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    System.out.println(Thread.currentThread().getName() + "：Bye - " + t);
                    semaphore.release();
                }
            }, "T_" + i).start();
        }


    }
}
