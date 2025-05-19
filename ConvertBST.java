public class ConvertBST {
    public static void main() {
        TreeNode node1 = new TreeNode(0);
        TreeNode node2 = new TreeNode(1);
        // TreeNode node3 = new TreeNode(6);
        // TreeNode node4 = new TreeNode(2);
        // TreeNode node5 = new TreeNode(4);
        // TreeNode node6 = new TreeNode(1);
        node1.right = node2;
        // node1.right = node3;
        // node2.left = node4;
        // node2.right = node5;
        // node4.left = node6;
        ConvertBST k = new ConvertBST();
        k.convertBST(node1);
    }
    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }
    private void traverse(TreeNode node) {
        if (node == null) return;
        traverse(node.right);
        node.val += sum;
        sum = node.val;
        traverse(node.left);
    }
}
