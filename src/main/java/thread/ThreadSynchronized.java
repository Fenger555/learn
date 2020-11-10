package thread;

/**
 * @Author gaoxing
 * @Date 2020-08-03 17:43
 */
public class ThreadSynchronized {

    static boolean f = true;

    static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new WaitThread(), "wait").start();

        ThreadUtil.sleep(1);

        new Thread(new NotifyThread(), "notity").start();

    }

    static class WaitThread implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (f) {
                    System.out.println("wait");

                    try {
                        // wait会释放锁
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("wait rerun");
                }
            }
        }
    }

    static class NotifyThread implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("notify");
                lock.notifyAll();
                System.out.println("success");
                f = false;
            }
            ThreadUtil.sleep(1);
            synchronized (lock) {
                System.out.println("notify rerun");
            }
        }
    }
}
