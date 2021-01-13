package leetcode;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 * @author Fenger
 * @date 2021-01-13 14:50
 */
public class Lc152_dp {

    public int maxProduct(int[] nums) {

        int max = nums[0];
        int[] dpMax = new int[nums.length+1];
        int[] dpMin = new int[nums.length+1];

        dpMax[0]=nums[0];
        dpMin[0]=nums[0];
        for (int i = 1; i < nums.length; i++) {
            int v = nums[i];
            if (v >= 0) {
                dpMax[i] = Math.max(dpMax[i-1]* v, v);
                dpMin[i] = Math.min(dpMin[i-1]* v, v);
            } else {
                dpMax[i] = Math.max(dpMin[i-1]* v, v);
                dpMin[i] = Math.min(dpMax[i-1]* v, v);
            }
            max = Math.max(dpMax[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] ints = {-2,0,-1,3,4,-1,-9,-2};
        int ans = new Lc152_dp().maxProduct(ints);
        System.out.println(ans);
    }

}
