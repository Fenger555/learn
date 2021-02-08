package leetcode.jzoffer;

/**
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 * F(0) = 0, F(1)= 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Fenger
 * @date 2021-02-08 10:08
 */
public class Jz010_1_dp {

    // 常规dp
    // 数组中元素并非全程用到
    public int fib(int n) {
        if (n == 0) return 0;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i-1] + dp[i-2])%1000000007;
        }
        return dp[n];
    }

    // 节省内存空间优化版
    public int fib1(int n) {
        int f1 = 0, f2 = 1, tmp = 0;
        for (int i = 0; i < n; i++) {
            tmp = (f1+f2)%1000000007;
            f2 = f1;
            f1 = tmp;
        }
        return tmp;
    }

    public static void main(String[] args) {
        Jz010_1_dp jz010_1_dp = new Jz010_1_dp();
        System.out.println(jz010_1_dp.fib1(5555555));
    }

}
