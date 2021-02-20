package leetcode.jzoffer;

/**
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 * [["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]]
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 *
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 *
 * 示例 2：
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Fenger
 * @date 2021-02-07 18:18
 */
public class Jz012_trackback {

    // 剪枝+dfs
    public boolean exist(char[][] board, String word) {
        char[] wordArr = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, wordArr, i, j, 0))    return true;
            }
        }
        return false;
    }

    /**
     *
     * @param board
     * @param word
     * @param pi
     * @param pj
     * @param depth
     * @return
     */
    public boolean dfs(char[][] board, char[] word, int pi, int pj, int depth) {

        // 剪枝
        if (pi<0 || pi>=board.length || pj<0 || pj>=board[0].length || board[pi][pj] != word[depth]) {
            return false;
        }

        if (depth == word.length-1) {
            return true;
        }

        board[pi][pj] = '\0';
        boolean rs = dfs(board, word, pi+1, pj, depth+1)
                || dfs(board, word, pi, pj+1, depth+1)
                || dfs(board, word, pi, pj-1, depth+1)
                || dfs(board, word, pi-1, pj, depth+1);
        // 还原，回溯
        board[pi][pj] = word[depth];
        return rs;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'a', 'b', 'c', 'd'},
                {'e', 'f', 'g', 'h'},
                {'i', 'j', 'k', 'l'},
                {'m', 'n', 'o', 'p'}
        };

        Jz012_trackback jz012_trackback = new Jz012_trackback();
        boolean rs = jz012_trackback.exist(board, "love");
        System.out.println(rs);
    }
}
