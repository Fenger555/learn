package leetcode;

/**
 *
 * 我们有 N 个与坐标轴对齐的矩形, 其中 N > 0, 判断它们是否能精确地覆盖一个矩形区域。
 *
 * 每个矩形用左下角的点和右上角的点的坐标来表示。例如，一个单位正方形可以表示为 [1,1,2,2]。( 左下角的点的坐标为 (1, 1) 以及右上角的点的坐标为 (2, 2) )。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-rectangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Fenger
 * @date 2021-02-05 17:31
 */
public class Lc391_enum {

    // todo
    public boolean isRectangleCover(int[][] rectangles) {
        long area = 0;
        int minx = rectangles[0][0];
        int maxx = rectangles[0][2];
        int miny = rectangles[0][1];
        int maxy = rectangles[0][3];
        for (int i = 0; i < rectangles.length; i++) {
            area += ((rectangles[i][3] - rectangles[i][1]) * (rectangles[i][2]-rectangles[i][0]));
            minx = Math.min(minx, rectangles[i][0]);
            maxx = Math.max(maxx, rectangles[i][2]);
            miny = Math.min(miny, rectangles[i][1]);
            maxy = Math.max(maxy, rectangles[i][3]);
        }
        
        return area == (maxx-minx)*(maxy-miny);
    }

    public static void main(String[] args) {
        int[][] nums = {
                {0,0,1,1},
                {0,1,3,2},
                {1,0,2,2}
        };
        Lc391_enum lc391_enum = new Lc391_enum();
        boolean rectangleCover = lc391_enum.isRectangleCover(nums);
        System.out.println(rectangleCover);
    }

}
