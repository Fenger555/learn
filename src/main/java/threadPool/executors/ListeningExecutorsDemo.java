package threadPool.executors;

import com.google.common.util.concurrent.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Fenger
 * @date 2021-01-19 14:26
 */
public class ListeningExecutorsDemo {
    public static void main(String[] args) {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());

        ListenableFuture<String> listenableFuture = listeningExecutorService.submit(
                () -> {
                    System.out.println("aaa");
                    Thread.sleep(3000);
                    return "hi";
                }
        );

        Futures.addCallback(
                listenableFuture,
                new FutureCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println(result);
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                }
        );
    }
}
