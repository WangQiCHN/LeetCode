package code;

import java.util.Arrays;

public class Solution {
    void main() {
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(6);
        root.right = new TreeNode(-3);
        root.right.left = new TreeNode(-6);
        root.right.right = new TreeNode(2);
        root.right.right.left = new TreeNode(2);
        root.right.right.left.left = new TreeNode(-6);
        root.right.right.left.right = new TreeNode(-6);
        root.right.right.left.left.left = new TreeNode(-6);
        System.out.println(maxPathSum(root));
    }

    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        calculate(root);

        return max;
    }

    private int calculate(TreeNode node) {
        if (node == null)
            return 0;
        int left = calculate(node.left);
        int center = node.val;
        int right = calculate(node.right);

        int tempMax;
        if (node.left == null && node.right == null) {
            tempMax = center;
        } else if (node.left == null) {
            tempMax = Math.max(center, Math.max(center + right, right));
        } else if (node.right == null) {
            tempMax = Math.max(center, Math.max(center + left, left));
        } else {
            tempMax = getMax(left, center, right);
        }
        max = Math.max(max, tempMax);

        return Math.max(center, Math.max(center + right, center + left));
    }

    private int getMax(int a, int b, int c) {
        int[] array = new int[6];
        array[0] = a;
        array[1] = a + b;
        array[2] = a + b + c;
        array[3] = b;
        array[4] = b + c;
        array[5] = c;
        Arrays.sort(array);
        return array[5];
    }
}

class TreeNode {
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