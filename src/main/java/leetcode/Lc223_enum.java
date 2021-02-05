package leetcode;

/**
 * 在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积。
 * 每个矩形由其左下顶点和右上顶点坐标表示，如图所示。
 *
 * 输入: -3, 0, 3, 4, 0, -1, 9, 2
 * 输出: 45
 *
 * @author Fenger
 * @date 2021-02-05 15:47
 */
public class Lc223_enum {

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        return (D-B)*(C-A) + (H-F)*(G-E) - ((C<=E || G<=A || H<=B || D<=F) ? 0 : (Math.min(C, G) - Math.max(A, E)) * (Math.min(D, H) - Math.max(B, F)));
    }

    public static void main(String[] args) {
        Lc223_enum lc223_enum = new Lc223_enum();
        int i = lc223_enum.computeArea(3, -1, 9, 2, -3, 0, 3, 4);
        System.out.println(i);
    }
}
