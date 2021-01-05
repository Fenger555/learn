package leetcode;

/**
 * 最长回文子串
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * @author Fenger
 * @date 2021-01-04 17:27
 */
public class Lc005_dp {

    /**
     * dp[i][j]表示，子字符串strs[i,...j]中最长回文长度
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();

        int[][] dp = new int[chars.length][chars.length];
        int beginIndex = 0;
        int endIndex = 0;
        int maxLength = 0;

        // 将单个字符串视为回文，长度为1
        for (int i = 0; i < chars.length; i++) {
            dp[i][i]=1;
        }

        // 注意迭代顺序
        for (int i = chars.length-2; i >= 0; i--) {
            for (int j = i+1; j < chars.length; j++) {
                // 子串头尾字符相同
                // 子串去掉头尾已是回文串 或 子串长度为2
                if (chars[i]==chars[j]&&(dp[i+1][j-1]!=0||j-i==1)) {
                    dp[i][j] = dp[i+1][j-1]+2;
                    if (dp[i][j]>maxLength) {
                        beginIndex=i;
                        endIndex=j;
                        maxLength = dp[i][j];
                    }
                }
            }
            
        }

        return s.substring(beginIndex, endIndex+1);
    }

    public static void main(String[] args) {
        String aba = new Lc005_dp().longestPalindrome("fgopopopopoca");
        System.out.println(aba);
    }

}
