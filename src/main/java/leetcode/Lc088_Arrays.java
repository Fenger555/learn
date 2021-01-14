package leetcode;

/**
 * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。
 * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。你可以假设nums1有足够的空间（空间大小等于m + n）来保存 nums2 中的元素。
 * <p>
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 *
 * @author Fenger
 * @date 2021-01-14 11:13
 */
public class Lc088_Arrays {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        System.arraycopy(nums1, 0, nums1, nums1.length - m, m);

        int i = nums1.length - m;
        int j = 0;
        int ci = 0;

        while (i < nums1.length && j < nums2.length) {
            nums1[ci++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }

        if (i == nums1.length) {
            System.arraycopy(nums2, j, nums1, ci, nums2.length - j);
        }

    }

    public static void main(String[] args) {
//        int[] nums1 = {1, 2, 3, 0, 0, 0};
//        int[] nums2 = {2, 5, 6};
//        new Lc088_Arrays().merge(nums1, 3, nums2, 3);
        int[] nums1 = {1,3,5,7,10,0,0,0,0,0};
        int[] nums2 = {2,4,6,8,9};
        new Lc088_Arrays().merge(nums1, 5, nums2, 5);
        System.out.println();
    }

}
