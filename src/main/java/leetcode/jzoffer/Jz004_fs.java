package leetcode.jzoffer;

/**
 *
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target=5，返回true。
 * 给定target=20，返回false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Fenger
 * @date 2021-02-08 09:38
 */
public class Jz004_fs {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        return fs(matrix, target, matrix.length-1, 0);
    }

    // 从左下开始遍历
    // val>target时，向上遍历
    // val<target时，向右遍历
    public boolean fs(int[][] matrix, int target, int i, int j) {
        if (i<0 || j>=matrix[0].length) {
            return false;
        }
        if (matrix[i][j] > target) {
            return fs(matrix, target, i-1, j);
        } else if (matrix[i][j] < target) {
            return fs(matrix, target, i, j+1);
        }
        return true;
    }

    // 复杂度过高
    public boolean dfs(int[][] matrix, int target, int i, int j) {
        if (i>=matrix.length || j>=matrix[0].length || matrix[i][j] > target) {
            return false;
        }
        if (matrix[i][j] == target) {
            return true;
        }
        return dfs(matrix, target, i+1, j) || dfs(matrix, target, i, j+1);
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        Jz004_fs jz004_fs = new Jz004_fs();
        boolean rs = jz004_fs.findNumberIn2DArray(matrix, 109);
        System.out.println(rs);

    }

}
