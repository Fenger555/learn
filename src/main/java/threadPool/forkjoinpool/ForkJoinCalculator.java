package threadPool.forkjoinpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Author gaoxing
 * @Date 2020-12-08 15:22
 */
public class ForkJoinCalculator implements Calculator {

    private ForkJoinPool pool;

    public ForkJoinCalculator() {
        pool = new ForkJoinPool();
    }

    @Override
    public long sum(long[] vals) {
        Long rs = pool.invoke(new SumTask(vals, 0, vals.length - 1));
        pool.shutdown();
        return rs;
    }


    // RecursiveTask：有返回值  RecursiveAction：无返回值
    class SumTask extends RecursiveTask<Long> {

        long[] vals;
        int from;
        int to;

        public SumTask(long[] vals, int from, int to) {
            this.vals=vals;
            this.from=from;
            this.to=to;
        }

        @Override
        protected Long compute() {
            if (from == to) {
                return vals[from];
            } else {
                int mid = from+((to-from)>>1);
                SumTask leftTask = new SumTask(vals, from, mid);
                SumTask rightTask = new SumTask(vals, mid + 1, to);
                return leftTask.compute() + rightTask.compute();
            }
        }

    }

}
