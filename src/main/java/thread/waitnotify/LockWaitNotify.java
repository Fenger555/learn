package thread.waitnotify;

/**
 * @Author gaoxing
 * @Date 2020-11-11 18:05
 *
 * synchronized使用等待唤醒机制
 * 必须是同步状态下才可调用wait/notify方法
 *
 */
public class SynchronizedWaitNotify {

    public static void main(String[] args) {
        Object sync = new Object();

        new Thread(() -> {
            synchronized (sync) {
                System.out.println("A come in");
                try {
                    sync.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A step 2");
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (sync) {
                System.out.println("B come in");
                sync.notify();
                System.out.println("B step 2");
            }
        }, "B").start();
    }
}
