public class DiameterOfBinaryTree {
    public static int maxDepth = 0;

    public static void main() {
        TreeNode root = createTree();
        int depth = diameterOfBinaryTree(root);
        System.out.println(depth);
    }

    public static TreeNode createTree() {
        TreeNode left = new TreeNode(2, null, null);
        TreeNode root = new TreeNode(1, left, null);
        return root;
    }

    // public static int diameterOfBinaryTree(TreeNode root) {
    //     Queue<TreeNode> q = new LinkedList<TreeNode>();
    //     q.offer(root);
    //     while (!q.isEmpty()) {
    //         int sz = q.size();
    //         for (int i = 0; i < sz; i++) {
    //             TreeNode cur = q.poll();
    //             int curCount = count(cur);
    //             if (curCount > maxDepth) {
    //                 maxDepth = curCount;
    //             }
    //             if (cur != null) {
    //                 q.offer(cur.left);
    //                 q.offer(cur.right);
    //             }
    //         }
    //     }
    //     return maxDepth;
    // }

    // public static int count(TreeNode root) {
    //     if (root == null)
    //         return 0;
    //     int left = count(root.left);
    //     int right = count(root.right);
    //     return left + right + 1;
    // }
    public static int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return maxDepth;
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left); 
        int right = maxDepth(root.right);
        int diameter = left + right;
        maxDepth = Math.max(maxDepth, diameter);
        return 1 + Math.max(left, right);
    }
}