package thread.management;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author gaoxing
 * @Date 2020-08-11 10:34
 */
public class Reporter implements Runnable {



    public void init() {

    }

    public void startReport(){
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::run, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        printStack(false);
    }

    void printStack(boolean stack) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);

        System.out.println("-----------------------------------------------");
        System.out.println("Thread Count: " + threadInfos.length + "\tBlocking Count: " + threadInfos);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(String.format("[%s]Thread Name: %s - %s", threadInfo.getThreadId(), threadInfo.getThreadName(), threadInfo.getThreadState()));
            StackTraceElement[] stackTrace = threadInfo.getStackTrace();
            if (stack) {
                for (StackTraceElement element : stackTrace) {
                    System.out.println("\t" + element.toString());
                }
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------");
    }

    public static void main(String[] args) {
        new Reporter().startReport();
    }
}
