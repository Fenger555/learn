package leetcode.jzoffer;

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
 * 请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Fenger
 * @date 2021-02-08 15:17
 */
public class Jz014_1_dp {

    /**
     * 数学归纳法
     * 由算术几何均值不等式可以得到以下结论：
     * 1. 将绳子分为各段相等长度，得到的乘积最大
     * 2. 各段长度为3时，乘积最大
     *
     * 因此，得到最优切分规则：
     * 1. 将绳子切割为长度为3的各段；
     * 2. 若最后余2，3*3*2 > 4*4 > 5*3，故保留2；
     * 3. 若最后余1，3*4 > 3*3*1，故将1加到最后一段。
     *
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        if (n<=3)   return n-1;
        int mod = n%3;
        int a = n/3;
        if (mod == 1) {
            a -= 1;
            mod = 4;
        }
        if (mod == 0) {
            mod = 1;
        }
        return (int) Math.pow(3, a)*mod;
    }

    // dp todo
    public int cuttingRope1(int n) {

        return 0;
    }

    public static void main(String[] args) {
        Jz014_1_dp jz014_1_dp = new Jz014_1_dp();
        System.out.println(jz014_1_dp.cuttingRope(6));
    }

}
