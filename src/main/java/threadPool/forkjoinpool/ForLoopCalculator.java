package threadPool.forkjoinpool;

/**
 * @Author gaoxing
 * @Date 2020-12-08 14:47
 */
public class ForLoopCalculator implements Calculator {
    @Override
    public long sum(long[] vals) {
        long rs = 0;
        for (long val : vals) {
            rs+=val;
        }
        return rs;
    }
}
