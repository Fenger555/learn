package leetcode;

/**
 * @author Fenger
 * @date 2021-02-23 18:14
 */
public class Lc200_trackback {

    public int numIslands(char[][] grid) {

        int n = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]=='1') {
                    dfs(grid, i, j);
                    n++;
                }
            }
        }

        return n;
    }

    public void dfs(char[][] grid, int x, int y) {

        if (x>=grid.length || x<0 || y>=grid[0].length || y<0 || grid[x][y]!='1') {
            return;
        }

        grid[x][y] = '\0';
        dfs(grid, x+1, y);
        dfs(grid, x, y+1);
        dfs(grid, x-1, y);
        dfs(grid, x, y-1);

    }

    public static void main(String[] args) {

        char[][] chars = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        Lc200_trackback lc200_trackback = new Lc200_trackback();
        int i = lc200_trackback.numIslands(chars);
        System.out.println(i);
    }
}
