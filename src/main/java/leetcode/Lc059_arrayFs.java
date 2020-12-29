package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 *
 * @author Fenger
 * @date 2020-12-28 17:32
 */
public class Lc059_arrayFs {

    public static int[][] generateMatrix(int n) {

        if (n<=0) {
            return null;
        }

        int[][] matrix = new int[n][n];

        int x = 0, y = 0;
        int c = 0, r = 0;

        boolean flag = true;

        boolean rFlag = true;
        boolean cFlag = true;

        int k=1;
        while (true) {

            if (r == n || c == n) {
                break;
            }

            matrix[x][y] = k++;

            // 行遍历
            if (flag) {
                if ((y == n - c/2 -1 && rFlag) || (y == c/2 && !rFlag)) {
                    // 置为列遍历
                    flag = false;
                    // 遍历方向
                    rFlag = !rFlag;
                    r++;
                    x += cFlag ? 1 : -1;
                } else {
                    // 移动一个单位
                    y += rFlag ? 1 : -1;
                }
            } else {

                if ((x == n - r/2 -1 && cFlag) || (x == r/2 && !cFlag)) {
                    flag = true;
                    cFlag = !cFlag;
                    c++;
                    y += rFlag ? 1 : -1;
                } else {
                    x += cFlag ? 1 : -1;
                }
            }

        }

        return matrix;
    }

    public static void main(String[] args) {
        int[][] ints = generateMatrix(1);
        System.out.println(ints);

    }

}
