package collection;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author gaoxing
 * @Date 2020-06-27 11:01
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {

        Queue<Integer> queue = new ArrayBlockingQueue<>(3);

        // add：入队，队列满时add抛异常
        System.out.println(queue.add(1));
        System.out.println(queue.add(2));
        System.out.println(queue.add(3));
        // 异常
//        System.out.println(queue.add(4));

        // remove：出队，队列空时remove抛异常
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        // 异常
//        System.out.println(queue.remove());

        queue.clear();
        // offer：入队，队列满时offer返回false
        System.out.println(queue.offer(1));
        System.out.println(queue.offer(2));
        System.out.println(queue.offer(3));
        System.out.println(queue.offer(4));

        // poll：出队，队列空时peek返回null
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());


        System.out.println("----------------------------");
        queue.clear();
        BlockingQueue blockingQueue = (BlockingQueue)queue;

        // put：入队，队列满时put，线程阻塞，慎用
        try {
            blockingQueue.put(11);
            blockingQueue.put(12);
            blockingQueue.put(13);
//            blockingQueue.put(14);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // take：出队，队列空时take，线程阻塞，慎用
        try {
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
//            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        blockingQueue.clear();

        // offer：入队，队列满时，限制阻塞时间内还未入队，返回false
        try {
            System.out.println(blockingQueue.offer(11, 1, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer(12, 1, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer(13, 1, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer(14, 1, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // poll：出队，队列空时，限制阻塞时间内还未有出队元素，返回null
        try {
            System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(1, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
