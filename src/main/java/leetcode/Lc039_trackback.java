package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的数字可以无限制重复被选取。
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Fenger
 * @date 2021-02-20 11:27
 */
public class Lc039_trackback {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> rs = new ArrayList<>();
        if (candidates.length == 0 || target<=0) {
            return rs;
        }
        List<Integer> path = new ArrayList<>();
        int count = 0;
        dfs(candidates, rs, target, path, count, 0);
        return rs;
    }

    /**
     * 回溯
     * @param nums
     * @param rs
     * @param target
     * @param path
     * @param count
     * @param begin
     *      结果无需有序，使用begin标记，防止结果重复
     */
    public void dfs(int[] nums, List<List<Integer>> rs, int target, List<Integer> path, int count, int begin) {
        if (count == target) {
            rs.add(new ArrayList<>(path));
            return;
        }

        if (count > target) {
            return;
        }

        for (int i = begin; i < nums.length; i++) {
            path.add(nums[i]);
            count+=nums[i];
            dfs(nums, rs, target, path, count, i);
            path.remove(Integer.valueOf(nums[i]));
            count-=nums[i];
        }

    }

    public static void main(String[] args) {
        Lc039_trackback lc039_trackback = new Lc039_trackback();
        List<List<Integer>> rs = lc039_trackback.combinationSum(new int[]{2,7,3,6,1}, 9);
        System.out.println(rs);
    }

}
