package thread;

import thread.management.*;
import thread.state.Blocking;
import thread.state.TimeWaiting;

/**
 * @Author gaoxing
 * @Date 2020-08-03 16:09
 */
public class ThreadInterrupted {


    public static void main(String[] args) {

        Thread managementThread = new Thread(new ThreadManagement(), "Management Thread");
        managementThread.setDaemon(true);
//        managementThread.start();


        Thread runThread = new Thread(new Blocking(), "Run Thread");
        Thread sleepThread = new Thread(new TimeWaiting(), "Sleep Thread");
        runThread.start();
        sleepThread.start();

        ThreadUtil.sleep(2);
        runThread.interrupt();
        sleepThread.interrupt();


    }

}
