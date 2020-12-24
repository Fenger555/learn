package leetcode;

/**
 * 给定一个非空二叉树，返回其最大深度。
 * <p>
 * 输入：[-10,9,20,null,null,15,7]
 * <p>
 * -10
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 输出：3
 *
 * @author Fenger
 * @date 2020-12-23 17:52
 */
public class Lc104_BinaryTree {

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

    public int maxDepth(TreeNode root) {
        if (root==null) {
            return 0;
        }
        return 1 + max(maxDepth(root.left), maxDepth(root.right));
    }

    public int max(int a, int b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        Lc104_BinaryTree treeFs = new Lc104_BinaryTree();

    }

}
