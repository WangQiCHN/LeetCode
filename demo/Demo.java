package demo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

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

    public static void main() {
        Demo d = new Demo();
        int k = 5;
        int[] nums = {2,3,1,1,1,1,1};
        int result = d.splitArray(nums, k);
        System.out.println(result); // Should print 3
    }

    public int splitArray(int[] nums, int k) {
        int min = 0, max = 0, bound = 0;
        for (int n : nums) {
            if (n > min) {
                min = n;
            }
            max += n;
        }

        while (min <= max) {
            int mid = (max + min) / 2;
            int k1 = calculate(nums, mid);
            if (k1 > k) {
                min = mid + 1;
            } else {
                if (k1 == k) {
                    bound = mid;
                }
                max = mid - 1;
            }
        }

        return bound;
    }

    private int calculate(int[] nums, int capacity) {
        int c = capacity;
        int k = 0;
        for (int n : nums) {
            c -= n;
            if (c == 0) {
                k++;
                c = capacity;
            } else if (c < 0) {
                c = capacity - n;
                k++;
            }
        }

        if (c < capacity) {
            k++;
        }

        return k;
    }
}