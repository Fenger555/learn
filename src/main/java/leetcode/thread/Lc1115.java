package leetcode.thread;

/**
 * @Author gaoxing
 * @Date 2020-11-10 15:03
 *
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 *
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-foobar-alternately
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Lc1115 {

    volatile int c;

    private int n;

    public Lc1115(int n) {
        this.n = n;
        this.c=0;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            while (c==1) {}
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            c++;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            while (c==0) {}
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            c--;
        }
    }


    public static void main(String[] args) {

        Lc1115 lc = new Lc1115(5);

        Runnable printRunnable = () -> {
            System.out.print(Thread.currentThread().getName());
        };

        Thread t1 = new Thread(() -> {
            try {
                lc.foo(printRunnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "foo");

        Thread t3 = new Thread(() -> {
            try {
                lc.bar(printRunnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "bar");

        t1.start();
        t3.start();

    }

}
