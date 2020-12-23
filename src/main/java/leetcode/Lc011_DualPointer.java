package leetcode;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 *
 * @author Fenger
 * @date 2020-12-22 18:04
 */
public class Lc011_DualPointer {

    /**
     * 头、尾指针，指向值较小指针的向对方方向移动，直到找到最大区域
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {

        int ph = 0;
        int pt = height.length - 1;

        int rs = 0;

        while (ph < pt) {

            int c = (pt - ph) * (height[pt] < height[ph] ? height[pt--] : height[ph++]);
            if (c > rs) {
                rs = c;
            }

        }

        return rs;
    }

    public static void main(String[] args) {
        int[] ints = {1,8,6,2,5,4,8,3,7};
        int rs = maxArea(ints);
        System.out.println(rs);
    }

}
