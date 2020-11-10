package thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author gaoxing
 * @Date 2020-08-03 16:11
 */
public class ThreadUtil {

    public static void sleep(long s) {
        try {
            TimeUnit.SECONDS.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
