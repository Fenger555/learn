package threadPool.executors;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Fenger
 * @date 2021-01-19 11:25
 */
public class ExecutorsDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                5,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue(100),
                new ThreadFactory() {
                    private final ThreadGroup tg = new ThreadGroup(Thread.currentThread().getThreadGroup(), "demo");
                    private final AtomicInteger threadNum = new AtomicInteger(1);
                    private final String prefixName = "ExecutorsDemo-";
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(tg, r, prefixName + threadNum.incrementAndGet());
                    }
                }
        );

        Future<String> aaa = executor.submit(
                () -> {
                    try {
                        Thread.sleep(1000);
                        System.out.println("aaa");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "hi";
                }
        );

        Future<String> bbb = executor.submit(
                () -> {
                    try {
                        Thread.sleep(1000);
                        System.out.println("bbb");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "hello";
                }
        );

        while (true) {
            if (aaa.isDone()) {
                try {
                    System.out.println(aaa.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            if (bbb.isDone()) {
                try {
                    System.out.println(bbb.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            if (aaa.isDone() && bbb.isDone())   break;
        }

        executor.shutdown();


    }
}
