package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的数字不可重复被选取。
 *
 * 输入: candidates =[10,1,2,7,6,1,5], target =8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Fenger
 * @date 2021-02-20 11:27
 */
public class Lc040_trackback {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> rs = new ArrayList<>();
        if (candidates.length == 0 || target<=0) {
            return rs;
        }
        List<Integer> path = new ArrayList<>();
        int count = 0;
        boolean[] used = new boolean[candidates.length];
        // 剪枝需排序
        Arrays.sort(candidates);
        dfs(candidates, rs, target, path, count, 0, used);
        return rs;
    }

    /**
     * 回溯
     * @param nums
     * @param rs
     * @param target
     * @param path
     * @param count
     * @param used
     */
    public void dfs(int[] nums, List<List<Integer>> rs, int target, List<Integer> path, int count, int begin, boolean[] used) {
        if (count == target) {
            rs.add(new ArrayList<>(path));
            return;
        }

        if (count > target) {
            return;
        }

        for (int i = begin; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            // 剪枝去重
            if (i>0 && nums[i]==nums[i-1] && !used[i-1]){
                continue;
            }
            path.add(nums[i]);
            count+=nums[i];
            used[i] = true;
            dfs(nums, rs, target, path, count, i+1, used);
            path.remove(Integer.valueOf(nums[i]));
            count-=nums[i];
            used[i] = false;
        }

    }

    public static void main(String[] args) {
        Lc040_trackback lc040_trackback = new Lc040_trackback();
        List<List<Integer>> rs = lc040_trackback.combinationSum2(new int[]{10,1,2,7,6,1,5}, 10);
        System.out.println(rs);
    }

}
