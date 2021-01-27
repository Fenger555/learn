package leetcode;

/**
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 *
 * @author Fenger
 * @date 2021-01-25 17:57
 */
public class Lc004_Array {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int midIndex = (nums1.length + nums2.length - 1) / 2;
        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length && (i+j) < midIndex) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        // [1] [1,2,3,4]
        // midIndex=2
        // i=1,j=0
        // j_index = midIndex-i

        // [1,3,5,7] [2,4,6,8]
        // midIndex=4
        // i=2,j=2
        // index = minValueIndex(num1[i],num2[j])
        // index1 = index + 1

        if (i == nums1.length || j == nums2.length) {
            int rs1 = i == nums1.length ? nums2[midIndex-i] : nums1[midIndex-j];
            if ((nums1.length+nums2.length)%2==0) {
                int rs2 = i == nums1.length ? nums2[midIndex-i+1] : nums1[midIndex-j+1];
                return (rs1+rs2)/2d;
            }
            return rs1;
        }

        if ((i+j) == midIndex) {
            int rs1 = Math.min(nums1[i], nums2[j]);
            if ((nums1.length+nums2.length)%2==0) {
                int rs2 = nums1[i] < nums2[j] ? Math.min(i+1 < nums1.length?nums1[i+1]:Integer.MAX_VALUE, nums2[j]) : Math.min(nums1[i], j+1<nums2.length?nums2[j+1]:Integer.MAX_VALUE);
                return (rs1+rs2)/2d;
            }
            return rs1;
        }

        return 0;

    }

    public static void main(String[] args) {
        Lc004_Array lc004_array = new Lc004_Array();
//        int[] nums1 = {1, 3, 5, 7};
//        int[] nums2 = {2, 4, 6, 8};
        int[] nums1 = {0,1,3,4,5};
        int[] nums2 = {2};
        double medianSortedArrays = lc004_array.findMedianSortedArrays(nums2, nums1);
        System.out.println(medianSortedArrays);
    }
}
