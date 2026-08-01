package recursion.binary_tree;

import java.util.Arrays;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }
}
public class SumOfAllDepthsOfBT {

    public void sumOfDepths(TreeNode root, int[] dist, int[] count) {
        if (root == null)
            return;

        sumOfDepths(root.left, dist, count);
        sumOfDepths(root.right, dist, count);

        count[root.val] = 1;

        if (root.left != null) {
            count[root.val] += count[root.left.val];
            dist[root.val] += dist[root.left.val] + count[root.left.val];
        }

        if (root.right != null) {
            count[root.val] += count[root.right.val];
            dist[root.val] += dist[root.right.val] + count[root.right.val];
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(8);

        int[] dist = new int[9];
        int[] count = new int[9];
        Arrays.fill(count, 1);

        SumOfAllDepthsOfBT sumOfAllDepthsOfBT = new SumOfAllDepthsOfBT();

        sumOfAllDepthsOfBT.sumOfDepths(root, dist, count);
        System.out.println("Sum of all depths of the binary tree: " + dist[0]);
    }
}
