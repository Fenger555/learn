package leetcode.dp;

/**
 * 0-1背包问题
 *
 * @author Fenger
 * @date 2020-12-30 14:58
 */
public class KnapsackProblem {

    //             |- dp[i-1][w], w<wi  背包剩余容量放不下当前item
    // dp[i][w] = -|
    //             |- max(dp[i-1][w], dp[i-1][w-wi]+vi), w>=wi  剩余容量足够放当前item，转换成子问题比较放与不放的价值
    private int knapsack(int[][] matrix, int w) {

        int[][] dp = new int[matrix.length + 1][w + 1];

        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= w; j++) {
                int wi = matrix[i - 1][1];
                if (j < wi) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - wi] + matrix[i - 1][0]);
                }
            }
        }

        return dp[matrix.length][w];
    }

    public int max(int a, int b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        int[][] ints = {
                {1, 1},
                {6, 2},
                {18, 5},
                {22, 6},
                {28, 7}
        };

        int v = new KnapsackProblem().knapsack(ints, 11);
        System.out.println(v);
    }

}
