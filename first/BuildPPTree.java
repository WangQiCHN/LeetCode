import TreeNode;

package first;
class BuildPPTree {
    public static void main() {
        BuildPPTree tree = new BuildPPTree();
        int[] preorder = {1,3,2,4};
        int[] postorder = {3,4,2,1};
        TreeNode node = tree.constructFromPrePost(preorder, postorder);
        System.out.println(node);
    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        TreeNode root = buildOneTree(preorder, 0, preorder.length, postorder, 0, postorder.length);

        return root;
    }

    TreeNode buildOneTree(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd) {
        if (preStart >= preEnd) return null;

        int rootValue = preorder[preStart];
        TreeNode root = new TreeNode(rootValue);
        // the first node in preorder is the left subtree root
        if (preStart + 1 >= preEnd) {
            root.left = null;
            root.right = null;
        } else {
            int index = -1;
            int leftV = preorder[preStart + 1];
            for (int i = postStart; i < postEnd; i++) {
                if (postorder[i] == leftV) {
                    index = i;
                    break;
                }
            }
            root.left = buildOneTree(preorder, preStart + 1, preStart + 1 + index - postStart + 1, postorder, postStart, index);
            root.right = buildOneTree(preorder, preStart + 1 + index - postStart + 1, preEnd, postorder, index + 1, postEnd);
        }

        return root;
    }
}