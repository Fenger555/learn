package leetcode;

/**
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *
 * @author Fenger
 * @date 2020-12-23 17:46
 */
public class Lc231_Bitwise {

    public static boolean isPowerOfTwo(int n) {
        return n <= 0 ? false : (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(32));
        System.out.println(isPowerOfTwo(-2147483648));
        System.out.println(isPowerOfTwo(996));
    }
}
