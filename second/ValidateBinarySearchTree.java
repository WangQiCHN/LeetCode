import first.TreeNode;

public class ValidateBinarySearchTree {
    public static void main() {
        TreeNode node1 = new TreeNode(45);
        TreeNode node2 = new TreeNode(42);
        TreeNode node3 = new TreeNode(44);
        TreeNode node4 = new TreeNode(43);
        TreeNode node5 = new TreeNode(41);
        node1.left = node2;
        node2.right = node3;
        node3.left = node4;
        node4.left = node5;

        ValidateBinarySearchTree v = new ValidateBinarySearchTree();
        boolean result = v.isValidBST(node1);
        System.out.println(result);
    }
    public boolean isValidBST(TreeNode root) {
        return checkValid(root, null, null);
    }

    private boolean checkValid(TreeNode node, TreeNode min, TreeNode max) {
        if (node == null) return true;
        if (min != null && node.val <= min.val) return false;
        if (max != null && node.val >= max.val) return false;

        return checkValid(node.left, min, node) && checkValid(node.right, node, max);
    }
}
