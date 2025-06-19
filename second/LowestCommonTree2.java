import first.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class LowestCommonTree2 {
    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        LowestCommonTree2 lct = new LowestCommonTree2();
        TreeNode p = root.left; // 5
        TreeNode q = root.right; // 4
        TreeNode lca = lct.lowestCommonAncestor(root, p, q);
        System.out.println("Lowest Common Ancestor: " + lca.val); // Should print 5
    }
    private boolean isOK = false;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode result = findNode(root, p, q);
        if (isOK) return result;
        else return null;
    }

    private TreeNode findNode(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val == p.val) {
            if (!isOK)
            isOK = findAnother(root, q);
            return root;
        }
        if (root.val == q.val) {
            if (!isOK)
            isOK = findAnother(root, p);
            return root;
        }

        TreeNode left = findNode(root.left, p, q);
        TreeNode right = findNode(root.right, p, q);

        if (left != null && right != null) {
            isOK = true;
            return root;
        } else {
            return left != null ? left : right;
        }
    }

    private boolean findAnother(TreeNode p, TreeNode q) {
        if (p == null) return false;
        if (p.val == q.val) return true;

        boolean result;
        result = findAnother(p.left, q) || findAnother(p.right, q);

        return result;
    }
}