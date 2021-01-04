package leetcode;

/**
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 * <p>
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 * <p>
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i]仅由'0' 和'1' 组成
 * 1 <= m, n <= 100
 *
 * @author Fenger
 * @date 2020-12-31 09:39
 */
public class Lc474_dp {

    public static void main(String[] args) {
        String[] strs = {
                "10","0001","111001","1","0"
        };
        int maxForm = new Lc474_dp().findMaxForm1(strs, 5, 3);
        System.out.println(maxForm);
    }

    public int findMaxForm1(String[] strs, int m, int n) {

        int[][][] dp = new int[strs.length+1][m+1][n+1];
        for (int i = 1; i <= strs.length; i++) {
            int s0 = sum0(strs[i-1]);
            int s1 = strs[i-1].length()-s0;
            for (int j = m; j >=s0; j--) {
                for (int k = n; k >=s1; k--) {
                    dp[i][j][k] = max(dp[i-1][j][k], dp[i-1][j-s0][k-s1]+1);
                }
            }

        }
        return dp[strs.length][m][n];
    }

    final static char ZERO = '0';

    public int findMaxForm(String[] strs, int m, int n) {

        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i < strs.length; i++) {
            int s1 = sum0(strs[i]);
            int s0 = strs[i].length()-s1;
            for (int j = m; j >= s1; j--) {
                for (int k = n; k >= s0; k--) {
                    if (j<s1||k<s0) {
                        dp[j][k] = dp[j][k];
                    }else {
                        dp[j][k] = max(dp[j][k], dp[j-s1][k-s0]+1);
                    }
                }
            }

        }
        return dp[m][n];
    }

    public int sum0(String str) {
        int sum = 0;
        for (char c : str.toCharArray()) {
            sum += (c- ZERO ==0 ? 1 : 0);
        }
        return sum;
    }

    public int max(int a, int b) {
        return a>b?a:b;
    }

}
