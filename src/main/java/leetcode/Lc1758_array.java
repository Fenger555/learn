package leetcode;

/**
 * 给你一个仅由字符 '0' 和 '1' 组成的字符串 s 。一步操作中，你可以将任一 '0' 变成 '1' ，或者将 '1' 变成 '0' 。
 * 交替字符串 定义为：如果字符串中不存在相邻两个字符相等的情况，那么该字符串就是交替字符串。例如，字符串 "010" 是交替字符串，而字符串 "0100" 不是。
 * 返回使 s 变成 交替字符串 所需的 最少 操作数。
 *
 * 输入：s = "0100"
 * 输出：1
 * 解释：如果将最后一个字符变为 '1' ，s 就变成 "0101" ，即符合交替字符串定义。
 *
 * @author Fenger
 * @date 2021-02-18 16:17
 */
public class Lc1758_array {

    // 1101
    // 0101
    // 1010
    public int minOperations(String s) {
        char[] chars = s.toCharArray();
        int a0 = 0;
        int a1 = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i%2==0) {
                if (chars[i] == '0') {
                    a0++;
                }
            } else {
                if (chars[i] == '1') {
                    a1++;
                }
            }
        }
        return Math.min(a0+a1, chars.length-a0-a1);
    }

    public static void main(String[] args) {
        Lc1758_array lc1758_array = new Lc1758_array();
        int i = lc1758_array.minOperations("11011101010010111");
        System.out.println(i);
    }
}
