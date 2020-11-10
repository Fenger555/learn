package threadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author gaoxing
 * @Date 2020-07-09 13:19
 */
public class Demo {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                4,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5),
                new MyThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

//        executor.execute();
        for (int i = 0; i < 20; i++) {
//            new Thread(() -> {
//
//            })
        }
    }

}
