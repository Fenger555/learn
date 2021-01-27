package leetcode;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Fenger
 * @date 2021-01-27 15:28
 */
public class Lc300_dp {

    /**
     * dp[i] =
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {

        int[] dp = new int[nums.length];

        dp[0] = 1;
        int maxrs = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            maxrs = Math.max(dp[i], maxrs);
        }

        return maxrs;
    }

    public static void main(String[] args) {
//        int[] nums = {0, 3, 1, 6, 2, 2, 7};
        int[] nums = {1,3,6,7,9,4,10,5,6};
        Lc300_dp lc300_dp = new Lc300_dp();
        System.out.println(lc300_dp.lengthOfLIS(nums));
    }
}
