public class KthSmallest {
    private int res;
    public static void main() {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        KthSmallest k = new KthSmallest();
        int result = k.kthSmallest(node1, 3);
        System.out.println(result);
    }
    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return res;
    }

    private int traverse(TreeNode node, int k) {
        if (node == null) return 0;
        int size = traverse(node.left, k);
        if (size == k - 1) {
            res = node.val;
            return size + 1;
        } else if (size > k - 1) {
            return size + 1;
        }
        return traverse(node.right, k - size - 1) + size + 1;
    }
}
