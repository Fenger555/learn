package leetcode;

/**
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * @author Fenger
 * @date 2021-01-05 17:10
 */
public class Lc064_dp {

    public int minPathSum(int[][] grid) {

        int colNum = grid[0].length;
        int[][] dp = new int[grid.length][colNum];

        dp[0][0]=grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            dp[i][0]=dp[i-1][0]+grid[i][0];
        }

        for (int i = 1; i < colNum; i++) {
            dp[0][i]=dp[0][i-1]+grid[0][i];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < colNum; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[grid.length-1][colNum-1];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        int i = new Lc064_dp().minPathSum(grid);
        System.out.println(i);
    }

}
