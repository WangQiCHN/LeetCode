import first.TreeNode;

public class InsertBinaryTree {
    public static void main() {
        InsertBinaryTree i = new InsertBinaryTree();
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        TreeNode result = i.insertIntoBST(root, 5);
        System.out.println(result);
    }
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        traverse(root, val);
        return root;
    }

    private void traverse(TreeNode node, int val) {
        if (node.val > val) {
            if (node.left == null) {
                node.left = new TreeNode(val);
            } else {
                traverse(node.left, val);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(val);
            } else {
                traverse(node.right, val);
            }
        }
    }
}
