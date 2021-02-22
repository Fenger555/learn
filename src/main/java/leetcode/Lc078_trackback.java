package leetcode;

import java.util.*;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * @author Fenger
 * @date 2021-02-22 13:59
 */
public class Lc078_trackback {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> rs = new ArrayList();
        Deque<Integer> path = new ArrayDeque<Integer>();
        dfs(rs, nums, 0, path, 0);
        return rs;
    }

    public void dfs(List<List<Integer>> rs, int[] nums, int depth, Deque path, int begin) {

        rs.add(new ArrayList<>(path));
        if (depth == nums.length) {
            return;
        }

        for (int i = begin; i < nums.length; i++) {
            path.addLast(nums[i]);
            dfs(rs, nums, depth+1, path, i+1);
            path.removeLast();
        }

    }

    public static void main(String[] args) {
        Lc078_trackback lc078_trackback = new Lc078_trackback();
        List<List<Integer>> rs = lc078_trackback.subsets(new int[]{});
        System.out.println(rs);
    }

}
