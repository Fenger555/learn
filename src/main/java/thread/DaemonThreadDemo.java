package thread;

/**
 * @Author gaoxing
 * @Date 2020-11-12 11:05
 */
public class DaemonThreadDemo {

    public static void main(String[] args) {


        new Thread(() -> {

            System.out.println("main start");

            Thread daemon = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("daemon +1s");
                }
            }, "daemon");

            daemon.setDaemon(true);
            daemon.start();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("main end");


        }, "main").start();
    }
}
