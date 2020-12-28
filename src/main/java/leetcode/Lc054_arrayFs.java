package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fenger
 * @date 2020-12-28 17:32
 */
public class Lc054_arrayFs {

    public static List<Integer> spiralOrder(int[][] matrix) {

        int x = 0, y = 0;
        int c = 0, r = 0;

        boolean flag = true;

        boolean rFlag = true;
        boolean cFlag = true;

        int rowNum = matrix.length;
        int colNum = matrix[0].length;

        List<Integer> rs = new ArrayList<>();
        while (true) {


            if (r == rowNum || c == colNum) {
                break;
            }
            System.out.println(String.format("(%s,%s) %s-%s, %s-%s", x, y, r, c,rFlag,cFlag));
            System.out.println(matrix[x][y]);
            rs.add(matrix[x][y]);

            // 行模式
            if (flag) {
                if ((y == colNum - c/2 -1 && rFlag) || (y == c/2 && !rFlag)) {
                    // 置为列模式
                    flag = false;
                    // 行方向翻转
                    rFlag = !rFlag;
                    r++;
                    x += cFlag ? 1 : -1;
                } else {
                    // 移动一个单位
                    y += rFlag ? 1 : -1;
                }
            } else {

                if ((x == rowNum - r/2 -1 && cFlag) || (x == r/2 && !cFlag)) {
                    flag = true;
                    cFlag = !cFlag;
                    c++;
                    y += rFlag ? 1 : -1;
                } else {
                    x += cFlag ? 1 : -1;
                }
            }

        }

        return rs;
    }

    public static void main(String[] args) {

//        int[][] ints = {
//                {1, 2, 3, 4, 5},
//                {6, 7, 8, 9, 10},
//                {11, 12, 13, 14, 15}
//        };

//        int[][] ints = {
//                {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 9}
//        };

        int[][] ints = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        List<Integer> rs = spiralOrder(ints);
        System.out.println(rs);
    }

}
