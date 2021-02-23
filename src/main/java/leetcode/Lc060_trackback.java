package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出集合[1,2,3,...,n]，其所有元素共有n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定n 和k，返回第k个排列。
 *
 * 输入：n = 4, k = 9
 * 输出："2314"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Fenger
 * @date 2021-02-22 17:54
 */
public class Lc060_trackback {

    // todo 超时需优化
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[n];
        List<String> rs = new ArrayList<>();
        dfs(rs, sb, n, k, used);
        return rs.get(k-1);
    }

    public void dfs(List<String> rs, StringBuilder sb, int n, int k, boolean[] used) {

        if (sb.length() == n) {
            rs.add(sb.toString());
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (used[i-1]) {
                continue;
            }
            used[i-1] = true;
            sb.append(i);
            dfs(rs, sb, n, k, used);
            used[i-1] = false;
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public static void main(String[] args) {
        Lc060_trackback lc060_trackback = new Lc060_trackback();
        String permutation = lc060_trackback.getPermutation(9, 4);
        System.out.println(permutation);
    }
}
