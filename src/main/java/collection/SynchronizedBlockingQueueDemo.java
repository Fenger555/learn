package collection;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author gaoxing
 * @Date 2020-06-27 12:19
 */
public class SynchronizedBlockingQueueDemo {

    public static void main(String[] args) {
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(synchronousQueue.offer(1, 2, TimeUnit.SECONDS));
                System.out.println(synchronousQueue.offer(2, 2, TimeUnit.SECONDS));
                System.out.println(synchronousQueue.offer(3, 2, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Product").start();

        new Thread(() -> {
            try {
                Thread.sleep(2);
                System.out.println(synchronousQueue.poll(1, TimeUnit.SECONDS));
                Thread.sleep(2);
                System.out.println(synchronousQueue.poll(1, TimeUnit.SECONDS));
                Thread.sleep(2);
                System.out.println(synchronousQueue.poll(1, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consumer").start();
    }
}
