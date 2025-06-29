package demo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

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

    public static void main() {
        Demo d = new Demo();
        int[] inorder  = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        TreeNode root = d.buildTree(inorder, postorder);
        System.out.println(root.val); // Should print 3
    }

    HashMap<Integer, Integer> valToIndex = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode root = createTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
        for (int i = 0; i < inorder.length; i++) {
            valToIndex.put(inorder[i], i);
        }

        return root;
    }

    private TreeNode createTree(int[] inorder, int is, int ie, int[] postorder, int ps, int pe) {
        if (is > ie)
            return null;
        int v = postorder[pe];
        TreeNode node = new TreeNode(v);

        int index = valToIndex.get(v);

        node.left = createTree(inorder, is, index - 1, postorder, ps, ps + index - is - 1);
        node.right = createTree(inorder, index + 1, ie, postorder, ps + index - is, pe - 1);

        return node;
    }
}