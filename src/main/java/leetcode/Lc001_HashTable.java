package leetcode;

import java.util.HashMap;

/**
 * @Author gaoxing
 * @Date 2020-12-04 10:02
 */
public class Lc001_HashTable {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashTable = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (hashTable.containsKey(target-nums[i])) {
                return new int[]{hashTable.get(target-nums[i]), i};
            }
            hashTable.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 16};
        int[] ints = new Lc001_HashTable().twoSum(nums, 9);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
    }

}
