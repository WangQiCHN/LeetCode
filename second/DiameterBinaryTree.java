public class DiameterBinaryTree {
    public static void main() {
        DiameterBinaryTree t = new DiameterBinaryTree();
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;

        int depth = t.diameterOfBinaryTree(root);
        System.out.println(depth);
    }
    int maxDepth = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        calculate(root);

        return maxDepth;
    }

    private int calculate(TreeNode node) {
        if (node == null) return 0;
        int left = calculate(node.left);
        int right = calculate(node.right);

        int depth = left + right;
        if (depth > maxDepth) {
            maxDepth = depth;
        }

        return 1 + Math.max(left, right);
    }
}