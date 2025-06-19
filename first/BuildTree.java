import TreeNode;

package first;
class BuildTree {
    public static void main() {
        BuildTree tree = new BuildTree();
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode node = tree.buildTree(preorder, inorder);
        System.out.println(node);
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = buildOneTree(preorder, 0, preorder.length, inorder, 0, inorder.length);
        return root;
    }

    TreeNode buildOneTree(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        if (pStart >= pEnd) return null;
        int max = preorder[pStart];
        TreeNode root = new TreeNode(max);
        int iIndex = -1;
        for (int i = iStart; i < iEnd; i++) {
            if (inorder[i] == max) {
                iIndex = i;
                break;
            }
        }
        int leftSize = iIndex - iStart;
        root.left = buildOneTree(preorder, pStart + 1, pStart + leftSize + 1, inorder, iStart, iIndex);
        root.right = buildOneTree(preorder, pStart + 1 + leftSize, pEnd, inorder, iIndex + 1, iEnd);

        return root;
    }
}