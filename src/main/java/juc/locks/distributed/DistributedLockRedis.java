package juc.locks.distributed;

import com.google.common.collect.Lists;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;
import util.RedisUtil;

/**
 * @Author gaoxing
 * @Date 2020-11-17 09:59
 */
public class DistributedLockRedis {

    private static final String LOCK_NAME = "distributedLock:test";

    public static void main(String[] args) {

        new Thread(() -> {
            while (true) {
                String lock = RedisUtil.getInstance().get(LOCK_NAME);
                System.out.println("Valid: " + lock);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (int i = 0; i < 20; i++) {
            new Thread(new MainLogic(), String.format("%s-%02d", "main", i)).start();
        }

    }

    /**
     * 原子加锁
     * @param value
     * @param toExpire
     * @return
     */
    public static boolean lock(String value, int toExpire) {
        System.out.println("tryLock: " + value);
        String rs = RedisUtil.getInstance().set(LOCK_NAME, value, SetParams.setParams().nx().ex(toExpire));
        return "ok".equalsIgnoreCase(rs);
    }

    /**
     * 解锁
     *
     * 原子解锁实现方式：
     *  1）lua脚本（推荐）
     *  2）redis事务
     *
     * 解锁线程必须为持有锁的线程
     *
     */
    public static void unlock() {
        System.out.println("release: " + Thread.currentThread().getName());
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisUtil.getInstance().eval(script, Lists.newArrayList(LOCK_NAME), Lists.newArrayList(Thread.currentThread().getName()));
    }

    /**
     * 守护线程给锁续期
     */
    static class DaemonRunnable implements Runnable {
        @Override
        public void run() {
            Jedis jedis = RedisUtil.getInstance();
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("daemonThread: " + Thread.currentThread().getName() + " +3s");
                jedis.expire(LOCK_NAME, 3);
            }
        }
    }

    /**
     * 逻辑处理线程
     */
    static class MainLogic implements Runnable {
        @Override
        public void run() {

            String currentThread = Thread.currentThread().getName();

            while (!lock(currentThread, 3)) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("currentThread: " + currentThread + " start");
            try {
                Thread daemonThread = new Thread(
                        new DaemonRunnable(),
                        currentThread + "-Daemon"
                );
                daemonThread.setDaemon(true);
                daemonThread.start();

                // main Logic
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("currentThread: " + currentThread + " end");
            } finally {
                unlock();
                Thread.currentThread().interrupt();
            }
        }
    }

}
