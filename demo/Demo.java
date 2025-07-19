package demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class Demo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(1); 
        demo.recoverTree(root);
    }

    private Map<Integer, TreeNode> nodes = new HashMap<Integer, TreeNode>();
    private List<TreeNode> list = new ArrayList<>();

    public void recoverTree(TreeNode root) {
        validateTree(root, null, null);
        if (list.size() == 2) {
            TreeNode p = list.get(0);
            TreeNode q = list.get(1);
            int temp = p.val;
            p.val = q.val;
            q.val = temp;
        } else if (list.size() == 3) {
            TreeNode p = list.get(1);
            TreeNode q = list.get(2);
            int temp = p.val;
            p.val = q.val;
            q.val = temp;
        }
    }

    private void validateTree(TreeNode root, TreeNode max, TreeNode min) {
        if (root == null)
            return;
        else {
            if (max == null && min == null) {
                validateTree(root.left, root, null);
                validateTree(root.right, null, root);
            } else if (max == null) {
                if (min.val > root.val) {
                    if (!nodes.containsKey(min.val)) {
                        list.add(min);
                    }
                    nodes.put(root.val, root);
                    list.add(root);
                    list.add(min);
                } else {
                    validateTree(root.left, root, min);
                    validateTree(root.right, null, min);
                }
            } else if (min == null) {
                if (max.val < root.val) {
                    if (!nodes.containsKey(max.val)) {
                        list.add(max);
                    }
                    nodes.put(max.val, max);
                    nodes.put(root.val, root);
                    list.add(root);
                } else {
                    validateTree(root.left, max, null);
                    validateTree(root.right, max, root);
                }
            } else {
                if (root.val < max.val && root.val > min.val) {
                    validateTree(root.left, max, root);
                    validateTree(root.right, root, min);
                } else if (root.val > max.val) {
                    if (!nodes.containsKey(max.val)) {
                        list.add(max);
                    }
                    nodes.put(max.val, max);
                    nodes.put(root.val, root);
                    list.add(root);
                } else {
                    if (!nodes.containsKey(min.val)) {
                        list.add(min);
                    }
                    nodes.put(min.val, min);
                    nodes.put(root.val, root);
                    list.add(root);
                }
            }
        }
    }
}