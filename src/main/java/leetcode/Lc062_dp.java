package leetcode;

/**
 *
 * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 *
 * @author Fenger
 * @date 2020-12-29 16:24
 */
public class Lc062_dp {

    // 其他方法，利用组合数学公式：C(m-1, m+n-2)

    // dp[x][y] = dp[x-1][y] + dp[x][y-1]
    public int uniquePaths1(int m, int n) {

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0]=1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i]=1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

    public int uniquePaths(int m, int n) {

        int[] dp = new int[m*n];

        for (int i = 0; i < n; i++) {
            dp[i]=1;
        }
        for (int i = 0; i < m; i++) {
            dp[i*n]=1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i*n+j] = dp[(i-1)*n+j] + dp[i*n+j-1];
            }
        }

        return dp[m*n-1];
    }

    public static void main(String[] args) {
        Lc062_dp dp = new Lc062_dp();

        System.out.println(dp.uniquePaths(3,29) +" " + dp.uniquePaths1(3,29));
    }

}
