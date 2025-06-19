import TreeNode;

package first;
public class ConstructMaximumBinaryTree {
    public static void main() {
        ConstructMaximumBinaryTree tree = new ConstructMaximumBinaryTree();
        int[] nums = {3,2,1,6,0,5};
        TreeNode node = tree.constructMaximumBinaryTree(nums);
        System.out.println(node);
    }
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode node = traverse(nums, 0, nums.length);
        return node;
    }

    private TreeNode traverse(int[] nums, int from, int to) {
        if (from >= to) return null;
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = from; i < to; i++) {
            if (max < nums[i]) {
                max = nums[i];
                maxIndex = i;
            }
        }

        TreeNode root = new TreeNode(max);
        root.left = traverse(nums, from, maxIndex);
        root.right = traverse(nums, maxIndex + 1, to);

        return root;
    }
}
