package demo;

import java.util.HashMap;

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

public class Demo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        demo.recoverTree(root);
        System.out.println("Recovered tree root value: " + root.val); // Should print
    }

    private TreeNode first; // First node with incorrect value
    private TreeNode second; // Second node with incorrect value
    private TreeNode prev; // Previous node in inorder traversal

    public void recoverTree(TreeNode root) {
        // Initialize variables
        first = null;
        second = null;
        prev = null;

        // Perform inorder traversal to find the two swapped nodes
        inorder(root);

        // Swap the values of the two nodes
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorder(TreeNode root) {
        if (root == null)
            return;

        // Traverse left subtree
        inorder(root.left);

        // Process current node
        if (prev != null && prev.val > root.val) {
            // If this is the first violation
            if (first == null) {
                first = prev;
                second = root;
            }
            // If this is the second violation
            else {
                second = root;
            }
        }
        prev = root;

        // Traverse right subtree
        inorder(root.right);
    }
}