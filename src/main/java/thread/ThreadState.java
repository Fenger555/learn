package thread;

import thread.management.*;
import thread.state.Blocking;
import thread.state.TimeWaiting;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author gaoxing
 * @Date 2020-08-03 15:03
 */
public class ThreadState {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        new Thread(new TimeWaiting(), "TimeWaiting Thread").start();

        new Thread(new Blocking(), "Blocking Thread-1").start();
        new Thread(new Blocking(), "Blocking Thread-2").start();

        Thread managementThread = new Thread(new ThreadManagement(), "Management Thread");
        managementThread.setDaemon(true);
        managementThread.start();

    }

}
