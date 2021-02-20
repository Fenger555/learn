package leetcode;

/**
 * 给定一个二维的矩阵，包含'X'和'O'（字母 O）。
 *
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的'O' 用 'X' 填充。
 * 示例:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Fenger
 * @date 2021-02-20 15:37
 */
public class Lc130_trackback {

    // todo
//    public void solve(char[][] board) {
//
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//                if (board[i][j] == 'O') {
//                    System.out.println(i + " - " + j);
//                    dfs(board, i, j);
//                }
//            }
//        }
//    }
//
//    public boolean dfs(char[][] board, int px, int py) {
//
//        if (px<0 || px>=board.length || py<0 || py>=board[0].length || board[px][py] != 'X') {
//            return false;
//        }
//
////        if (board[px][py] == 'X') {
////            return true;
////        }
//
//        board[px][py] = 'X';
//        boolean dfs = dfs(board, px, py+1)
//                && dfs(board, px+1, py)
//                && dfs(board, px, py-1)
//                && dfs(board, px-1, py);
//
//        if (!dfs) {
//            board[px][py] = 'O';
//        }
//
//        return dfs;
//    }

    int n, m;

    public void solve(char[][] board) {
        n = board.length;
        if (n == 0) {
            return;
        }
        m = board[0].length;
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0);
            dfs(board, i, m - 1);
        }
        for (int i = 1; i < m - 1; i++) {
            dfs(board, 0, i);
            dfs(board, n - 1, i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
            return;
        }
        // 将与边界相连的'O'打上标记
        board[x][y] = 'A';
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }

    public static void main(String[] args) {
//        char[][] board = {
//                {'X', 'X', 'X', 'X'},
//                {'X', 'O', 'X', 'X'},
//                {'X', 'X', 'O', 'X'},
//                {'O', 'X', 'O', 'X'},
//                {'O', 'X', 'X', 'X'}
//        };
        char[][] board = {
                {'O','O','X','O','O'},
                {'O','X','O','X','O'},
                {'X','O','O','O','X'},
                {'O','X','O','X','O'},
                {'O','O','X','O','O'}
        };
        Lc130_trackback lc130_trackback = new Lc130_trackback();
        lc130_trackback.solve(board);
        System.out.println();
    }

}
