package demo;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Demo {
    private boolean isFound;

    public static void main() {
        Demo d = new Demo();
        TreeNode p = new TreeNode(4611);
        TreeNode q = new TreeNode(10604);
        d.lowestCommonAncestor(null, null, null);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        if (root.val == p.val) {
            if (find(root, q)) {
                isFound = true;
            }
            return root;
        }
        if (root.val == q.val) {
            if (find(root, p)) {
                isFound = true;
            }
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            isFound = true;
            return root;
        } else {
            if (!isFound) {
                return null;
            } else {
                return left == null ? right : left;
            }
        }
    }

    private boolean find(TreeNode node, TreeNode child) {
        if (node == null)
            return false;
        else if (node.val == child.val)
            return true;
        else
            return find(node.left, child) || find(node.right, child);
    }
}