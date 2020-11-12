package thread.waitnotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author gaoxing
 * @Date 2020-11-11 18:05
 */
public class LockSupportDemo {

    public static void main(String[] args) {

        Thread a = new Thread(() -> {
            System.out.println("A come in");
            // 消耗一次运行许可
            LockSupport.park();
            System.out.println("A step 2");
        }, "A");
        a.start();

        Thread b = new Thread(() -> {
            System.out.println("B come in");
            // 给a发放一次运行许可
            LockSupport.unpark(a);
            System.out.println("B step 2");
        }, "B");
        b.start();
    }
}
