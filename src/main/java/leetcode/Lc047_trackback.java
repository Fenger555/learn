package leetcode;

import java.util.*;

/**
 * 全排列
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列
 *
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Fenger
 * @date 2021-02-19 17:28
 */
public class Lc047_trackback {

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> rs = new ArrayList();
        Deque<Integer> path = new ArrayDeque();
        boolean[] used = new boolean[nums.length];
//        dfs1(rs, list, list.size(), 0);
        dfs(nums, nums.length, rs, path, 0, used);
        return rs;
    }

    /**
     * 回溯 + 剪枝
     *
     * @param nums
     *      原始数组
     * @param len
     *      原始数组长度
     * @param rs
     *      结果集合
     * @param path
     *      中间结果栈，保存遍历路径
     * @param depth
     *      递归深度
     * @param used
     *      原始数组中的值是否使用标记
     */
    public void dfs(int[] nums, int len, List<List<Integer>> rs, Deque<Integer> path, int depth, boolean[] used) {

        if (depth == len) {
            rs.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }

            // 剪枝
            if (i>0 && nums[i] == nums[i-1] && !used[i-1]) {
                continue;
            }

            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, rs, path, depth+1, used);
            path.removeLast();
            used[i] = false;
        }

    }

    public static void main(String[] args) {
        Lc047_trackback lc047_trackback = new Lc047_trackback();
        int[] nums = {1, 1, 3};
        List<List<Integer>> rs = lc047_trackback.permuteUnique(nums);
        System.out.println(rs);
    }

}
