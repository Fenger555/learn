package leetcode;

import java.util.*;

/**
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 示例:
 * 输入:n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Fenger
 * @date 2021-02-20 14:53
 */
public class Lc077_trackback {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> rs = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque();
        dfs(n, k, rs, path, 0, 1);
        return rs;
    }

    public void dfs(int n, int k, List<List<Integer>> rs, Deque<Integer> path, int depth, int begin) {

        // 剪枝，极大优化
        if (depth + n - begin +1 -k <0) {
            return;
        }

        if (depth == k) {
            rs.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i <= n; i++) {
            path.addLast(i);
            dfs(n, k, rs, path, depth+1, i+1);
            path.removeLast();
        }

    }

    public static void main(String[] args) {
        Lc077_trackback lc077_trackback = new Lc077_trackback();
        List<List<Integer>> rs = lc077_trackback.combine(10, 4);
        System.out.println(rs);
    }


}
