package juc.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author gaoxing
 * @Date 2020-06-26 14:12
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {

        Cache cache = new Cache();

        for (int i = 0; i < 10; i++) {
            final String id = "id_" + i;
            Thread thread = new Thread(() -> {
                Object v = UUID.randomUUID();
                cache.write(id, v);
            }, "W" + i);
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            final String id = "id_" + i;
            Thread thread = new Thread(() -> {
                Object v = cache.read(id);
            }, "R" + i);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                int size = cache.size();
            }, "S" + i).start();
        }
    }

}

class Cache {

    Map<String, Object> cache = new HashMap();

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void write(String k, Object v) {
        lock.writeLock().lock();
        try {
            System.out.println(String.format("Write:%s<%s, %s>", Thread.currentThread().getName(), k, v));
            Thread.sleep(10);
            cache.put(k, v);
            System.out.println(String.format("Write Over:%s", Thread.currentThread().getName()));
            System.out.println("---------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Object read(String k) {
        Object v = null;
        lock.readLock().lock();
        try {
            System.out.println(String.format("Read:%s[%s]", Thread.currentThread().getName(), k));
            Thread.sleep(2000);
            v = cache.get(k);
            System.out.println(String.format("Read Result:%s<%s, %s>", Thread.currentThread().getName(), k, v));
            System.out.println("---------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
        return v;
    }

    public int size() {
        int s = 0;
        lock.readLock().lock();
        try {
            System.out.println(String.format("Size:%s", Thread.currentThread().getName()));
            Thread.sleep(500);
            s = cache.size();
            System.out.println(String.format("Size:%s - %s", Thread.currentThread().getName(), s));
            System.out.println("---------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
        return s;
    }

}
