package jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author gaoxing
 * @Date 2020-06-19 14:23
 */
public class VolatileTest {

    public static void main(String[] args) {
//        kejianxing();

        yuanzixing();

    }

    // volatile不保证原子性
    private static void yuanzixing() {
        Data data = new Data();
        for (int i=0; i<10; i++) {
            new Thread(() -> {
                for (int j=0;j<1000; j++) {
                    data.plusplus();
                    data.atomicPlusPlus();
                }
            }, "T"+i).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(data.num);
        System.out.println(data.atomicNum);
    }

    // volatile可见性
    private static void kejianxing() {
        Data data = new Data();

        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.set();
            System.out.println("T1 update: " + data.num);
        }, "T1").start();

        while (data.num == 0) {
            // 什么原因导致工作内存中的值发生更新？
//            System.out.println("main: " + data.num);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        System.out.println(data.num);
    }

}

class Data {
//    volatile
    int num = 0;

    void set() {
        num = 1;
    }

    // 重量级
//    synchronized
    void plusplus() {
        // ++运算在操作系统级别并不是原子性操作？
        num++;
    }

    // JUC
    AtomicInteger atomicNum = new AtomicInteger();
    void atomicPlusPlus() {
        atomicNum.getAndIncrement();
    }
}
