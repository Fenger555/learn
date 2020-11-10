package thread.management;

import thread.ThreadUtil;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Author gaoxing
 * @Date 2020-08-03 15:23
 */
public class ThreadManagement implements Runnable {
    @Override
    public void run() {
        while (true) {
            printStack(false);
            ThreadUtil.sleep(3);
        }

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


}
