package leetcode;

/**
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 *
 * @author Fenger
 * @date 2021-01-25 17:57
 */
public class Lc004_Array {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int midIndex1, midIndex2;
        midIndex1 = (nums1.length + nums2.length) / 2;
        midIndex2 = (nums1.length + nums2.length) % 2 == 0 ? midIndex1 + 1 : -1;

        int i = 0, j = 0;
        while (i< nums1.length) {

        }

        return 0;
    }
}
