package leetcode;

/**
 * @author Fenger
 * @date 2020-12-22 18:04
 */
public class Lc011_DualPointer {

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
