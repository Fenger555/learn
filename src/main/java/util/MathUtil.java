package util;

/**
 * @author Fenger
 * @date 2021-02-08 16:00
 */
public class MathUtil {

    public static int pow(int a, int b, int mod) {
        long res = 1;
        while (b>0) {
            // b为奇数
            if ((b&1) == 0) {
                res*=a;
                res%=mod;
            }
            a*=a;
            a%=mod;
            b>>=1;
        }
        return (int)res;
    }

    public static void main(String[] args) {
        System.out.println(MathUtil.pow(3, 40, 1000000007));
    }

}
