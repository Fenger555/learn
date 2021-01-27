package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fenger
 * @date 2021-01-27 14:24
 */
public class Lc287_Array {

    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 2};
        Lc287_Array lc287_Array_ = new Lc287_Array();
        System.out.println(lc287_Array_.findDuplicate(ints));
    }


}
