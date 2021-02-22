package leetcode;

import java.util.*;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * 示例:
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Fenger
 * @date 2021-02-22 17:36
 */
public class Lc090_trackback {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> rs = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[nums.length];
        // 剪枝需要先排序
        Arrays.sort(nums);
        dfs(rs, nums, 0, 0, path, used);
        return rs;
    }

    /**
     * 回溯+剪枝
     * @param rs
     * @param nums
     * @param depth
     * @param begin
     * @param path
     * @param used
     */
    public void dfs(List<List<Integer>> rs, int[] nums, int depth, int begin, Deque<Integer> path, boolean[] used) {
        rs.add(new ArrayList<>(path));
        if(depth == nums.length) {
            return;
        }
        for (int i = begin; i < nums.length; i++) {
            // 剪枝，已排好序，与前面一个值相等则跳过，防止重复
            if (i>0 && nums[i]==nums[i-1] && !used[i-1]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(rs, nums, depth+1, i+1, path, used);
            path.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Lc090_trackback lc090_trackback = new Lc090_trackback();
        List<List<Integer>> rs = lc090_trackback.subsetsWithDup(new int[]{1, 2, 2, 3, 3});
        System.out.println(rs);
    }
}
