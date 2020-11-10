package thread;

/**
 * @Author gaoxing
 * @Date 2020-09-22 20:14
 */
public class ThreadLocalDemo {

    private final static ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();

    private static Long get() {
        return System.currentTimeMillis() - longThreadLocal.get();
    }

    public static void main(String[] args) {


        for (int i = 0; i < 5; i++) {
            final int index = i;
            new Thread(() -> {
                longThreadLocal.set(index*10000L);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                System.out.println(Thread.currentThread().getName()+": " +longThreadLocal.get());
            }, "t"+i).start();
        }
    }
}
