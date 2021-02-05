package leetcode;

/**
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 * 输入：
 * matrix = [
 *      ["1","0","1","0","0"],
 *      ["1","0","1","1","1"],
 *      ["1","1","1","1","1"],
 *      ["1","0","0","1","0"]
 * ]
 * 输出：4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Fenger
 * @date 2021-02-05 16:29
 *
 */
public class Lc221_dp {


    /**
     *
     * dp =
     * [
     *  [1,0,1,0,0],
     *  [1,0,1,1,1],
     *  [1,1,1,2,2],
     *  [1,0,0,1,0]
     * ]
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        
        int[][] dp = new int[matrix.length][matrix[0].length];

        int ans = 0;

        for (int i = 0; i < matrix.length; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1:0;
            ans = Math.max(ans, dp[i][0]);
        }
        for (int i = 0; i < matrix[0].length; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1:0;
            ans = Math.max(ans, dp[0][i]);
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), dp[i][j-1]);
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        
        return ans*ans;
    }

    public static void main(String[] args) {
//        char[][] chars = {
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '1', '0'}
//        };

        char[][] chars = {
                {'0','0','0','1'},
                {'1','1','0','1'},
                {'1','1','1','1'},
                {'0','1','1','1'},
                {'0','1','1','1'}};

        Lc221_dp lc221_dp = new Lc221_dp();
        int i = lc221_dp.maximalSquare(chars);
        System.out.println(i);
    }

}
