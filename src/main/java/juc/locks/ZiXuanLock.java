package juc.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author gaoxing
 * @Date 2020-06-25 18:21
 */
public class ZiXuanLock implements Lock {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    @Override
    public void lock() {
        String currentName = Thread.currentThread().getName();
        System.out.println(currentName + ": in ");
        while (!atomicReference.compareAndSet(null, Thread.currentThread()));
        System.out.println(currentName + ": ing ");
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void unlock() {
        String currentName = Thread.currentThread().getName();
        System.out.println(currentName + ": out ");
        System.out.println("------------------");
        atomicReference.compareAndSet(Thread.currentThread(), null);
    }

    public static void main(String[] args) {

        ZiXuanLock ziXuanLock = new ZiXuanLock();

        for (int i=0; i< 10; i++) {
            new Thread(() -> {
                ziXuanLock.lock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ziXuanLock.unlock();
            }, "T" + i).start();
        }
    }




}
