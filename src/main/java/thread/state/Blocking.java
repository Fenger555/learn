package thread.state;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author gaoxing
 * @Date 2020-08-03 16:10
 */
public class Blocking implements Runnable {

    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        while (true) {
            if (1==2) {
                break;
            }
        }
        lock.unlock();
    }

}
