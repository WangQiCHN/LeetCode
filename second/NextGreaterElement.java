import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {
    public static void main() {
        NextGreaterElement n = new NextGreaterElement();
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        n.nextGreaterElement(nums1, nums2);
    }
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] tmp = calculate(nums2);
        int[] result = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    result[i] = tmp[j];
                    break;
                }
            }
        }

        return result;
    }

    private int[] calculate(int[] nums) {
        Stack<Node2> s = new Stack<>();
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            while (!s.isEmpty()) {
                Node2 top = s.peek();
                if (top.val < v) {
                    result[top.index] = v;
                    s.pop();
                } else {
                    break;
                }
            }
            s.push(new Node2(i, v));
        }
        return result;
    }
}

class Node2 {
    public int val;
    public int index;
    public Node2(int index, int val) {
        this.index = index;
        this.val = val;
    }
}