package thread.waitnotify;

/**
 * @author Fenger
 * @date 2021-03-08 17:28
 */
public class AlternateShow {

    static int max = 10;
    static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < max; i+=2) {
                synchronized (lock) {
                    System.out.println(i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i < max; i+=2) {
                synchronized (lock) {
                    System.out.println(i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "B").start();
    }

}
