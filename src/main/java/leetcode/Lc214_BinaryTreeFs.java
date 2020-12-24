package leetcode;

/**
 * 给定一个非空二叉树，返回其最大路径和。
 * <p>
 * 输入：[-10,9,20,null,null,15,7]
 * <p>
 * -10
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 输出：42 (15+20+7)
 *
 * @author Fenger
 * @date 2020-12-23 17:52
 */
public class Lc214_BinaryTreeFs {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int maxPathSum(TreeNode root) {
        return maxChildTreeSum(root);
    }

    // todo bug
    public int maxChildTreeSum(TreeNode treeNode) {

        if (treeNode == null) {
            return -Integer.MAX_VALUE;
        }

        if (treeNode.left == null && treeNode.right==null) {
            return treeNode.val;
        }

        int leftSum = maxChildTreeSum(treeNode.left);
        int rightSum = maxChildTreeSum(treeNode.right);

        int childMaxSum = max(leftSum, rightSum);

        return treeNode.val < 0 && childMaxSum>treeNode.val ? childMaxSum : treeNode.val+max(leftSum, 0)+max(rightSum,0);

    }

    public int max(int a, int b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        Lc214_BinaryTreeFs treeFs = new Lc214_BinaryTreeFs();


    }

    public TreeNode buildTree(int[] nums) {

        return null;
    }

}
