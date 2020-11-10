package thread.state;

import thread.ThreadUtil;

/**
 * @Author gaoxing
 * @Date 2020-08-03 16:13
 */
public class TimeWaiting implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("+++");
            ThreadUtil.sleep(10);
        }
    }
}
