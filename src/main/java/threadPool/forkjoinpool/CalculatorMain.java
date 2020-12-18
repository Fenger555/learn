package threadPool.forkjoinpool;

import java.util.stream.LongStream;

/**
 * @Author gaoxing
 * @Date 2020-12-08 14:54
 */
public class CalculatorMain {

    public static void main(String[] args) {
        long[] vals = LongStream.rangeClosed(1, 100000).toArray();

        Calculator forLoopCalculator = new ForLoopCalculator();
        long sum1 = forLoopCalculator.sum(vals);
        System.out.println(sum1);

        Calculator executorServiceCalculator = new ExecutorServiceCalculator();
        long sum2 = executorServiceCalculator.sum(vals);
        System.out.println(sum2);

        Calculator forkJoinCalculator = new ForkJoinCalculator();
        long sum3 = forkJoinCalculator.sum(vals);
        System.out.println(sum3);

    }
}
