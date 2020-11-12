package thread.waitnotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author gaoxing
 * @Date 2020-11-11 18:05
 * <p>
 * synchronized使用等待唤醒机制
 * 必须是同步状态下才可调用wait/notify方法
 */
public class LockWaitNotify {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            System.out.println("A come in");
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A step 2");
            lock.unlock();
        }, "A").start();

        new Thread(() -> {
            lock.lock();
            System.out.println("B come in");
            condition.signal();
            System.out.println("B step 2");
            lock.unlock();
        }, "B").start();
    }
}
