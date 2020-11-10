package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author gaoxing
 * @Date 2020-06-26 16:12
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier end = new CyclicBarrier(10, () -> {
            System.out.println("end");
        });

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " start");
                    end.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "T_" + i).start();
        }


    }

}
