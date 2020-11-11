package leetcode.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @Author gaoxing
 * @Date 2020-11-10 15:03
 *
 * 三个不同的线程将会共用一个 Foo 实例。
 *
 * 线程 A 将会调用 first() 方法
 * 线程 B 将会调用 second() 方法
 * 线程 C 将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Lc1114 {

    volatile int flag;

    public Lc1114() {
        flag=0;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        flag++;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (flag<1) {
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        flag++;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (flag<2) {
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) {

        Lc1114 lc = new Lc1114();

        Runnable printRunnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                lc.first(printRunnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "1");

        Thread t2 = new Thread(() -> {
            try {
                lc.second(printRunnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "2");

        Thread t3 = new Thread(() -> {
            try {
                lc.third(printRunnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "3");

        t1.start();
        t2.start();
        t3.start();

    }

}
