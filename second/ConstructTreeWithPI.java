public class ConstructTreeWithPI {
    public static void main() {
        ConstructTreeWithPI c = new ConstructTreeWithPI();
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        c.buildTree(preorder, inorder);
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = calculate(preorder, 0, preorder.length, inorder, 0, inorder.length);

        return root;
    }

    private TreeNode calculate(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
        if (ps == pe) {
            return null;
        }
        int rVal = preorder[ps];
        TreeNode root = new TreeNode(rVal);
        int leftLen = 0;
        for (int i = is; i < ie; i++) {
            if (inorder[i] == rVal) {
                leftLen = i;
                break;
            }
        }
        root.left = calculate(preorder, ps + 1, leftLen - is + ps + 1, inorder, is, leftLen);
        root.right = calculate(preorder, leftLen - is + ps + 1, pe, inorder, leftLen + 1, ie);

        return root;
    }
}
