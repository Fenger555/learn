package leetcode;

import java.util.*;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Fenger
 * @date 2021-02-19 17:28
 */
public class Lc046_trackback {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> rs = new ArrayList();

        List<Integer> list = new ArrayList();
        for (int num : nums) {
            list.add(num);
        }

        Deque<Integer> path = new ArrayDeque();
        boolean[] used = new boolean[nums.length];
//        dfs1(rs, list, list.size(), 0);
        dfs(nums, nums.length, rs, path, 0, used);
        return rs;
    }

    public void dfs(int[] nums, int len, List<List<Integer>> rs, Deque<Integer> path, int depth, boolean[] used) {

        if (depth == len) {
            rs.add(new ArrayList<>(path));
        }

        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, rs, path, depth+1, used);
            path.removeLast();
            used[i] = false;
        }

    }

    public void dfs1(List<List<Integer>> rs, List<Integer> list, int n, int k) {

        if (n == k) {
            rs.add(new ArrayList<>(list));
        }

        for (int i = k; i < n; i++) {
            Collections.swap(list, i, k);
            dfs1(rs, list, n, k+1);
            Collections.swap(list, i, k);
        }

    }

    // todo
    public void bfs() {

    }

    public static void main(String[] args) {

        Lc046_trackback lc046_trackback = new Lc046_trackback();
        int[] nums = {1, 2, 3};
        List<List<Integer>> rs = lc046_trackback.permute(nums);
        System.out.println(rs);
    }

}
