package demo;

public class Demo {
    public static void main() {
        Demo d = new Demo();
        int[] preorder = {8,6,1,9,10,3,4,7,2,5};
        int[] postorder ={9,1,6,2,7,4,3,5,10,8};
        d.constructFromPrePost(preorder, postorder);
    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        TreeNode root = createTree(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);

        return root;
    }

    private TreeNode createTree(int[] preorder, int prs, int pre, int[] postorder, int pos, int poe) {
        if (prs > pre) return null;
        int v = preorder[prs];
        TreeNode node = new TreeNode(v);

        // find the second node
        if (pos < poe) {
            int rightTop = postorder[poe - 1];
            int index = prs + 1;
            for (int i = prs + 1; i <= pre; i++) {
                if (preorder[i] == rightTop) {
                    index = i;
                    break;
                }
            }

            node.left = createTree(preorder, prs + 1, index - 1, postorder, pos, pos + index - prs - 1 - 1);
            node.right = createTree(preorder, index, pre, postorder, pos + index - prs - 1, poe - 1);
        }

        return node;
    }
}

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