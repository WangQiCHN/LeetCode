// public class CanPartition {
//     public static void main() {
//         CanPartition c = new CanPartition();
//         int[] nums = {1,5,11,5};
//         boolean result = c.canPartition(nums);
//         System.out.println(result);
//     }
//     public boolean canPartition(int[] nums) {
//         int sum = 0;
//         for (int n : nums) {
//             sum += n;
//         }
//         if (sum % 2 == 1) {
//             return false;
//         }

//         return calculate(nums, sum / 2);
//     }

//     private boolean calculate(int[] nums, int total) {
//         boolean[][] dps = new boolean[nums.length + 1][total + 1];
//         for (int i = 0; i <= nums.length; i++) {
//             dps[i][0] = true;
//         }

//         for (int i = 1; i <= nums.length; i++) {
//             for (int j = 1; j <= total; j++) {
//                 if (j - nums[i - 1] < 0) {
//                     dps[i][j] = dps[i - 1][j];
//                 } else {
//                     dps[i][j] = dps[i - 1][j] || dps[i - 1][j - nums[i - 1]];
//                 }
//             }
//         }
//         return dps[nums.length][total];
//     }
// }
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;

public class CanPartition {
    public static void main() {
        CanPartition c = new CanPartition();
        // int[] nums = {2,2,3,5};
        int[] nums = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97};
        boolean result = c.canPartition(nums);
        System.out.println(result);
    }
    private HashMap<String, Integer> memo = new HashMap<>();
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 == 1) {
            return false;
        }

        Arrays.sort(nums);
        return calculate(nums, sum / 2);
    }

    private boolean calculate(int[] nums, int total) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(new Node(0, true, ""));
        queue.addLast(new Node(0, false, ""));
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            if (n.index >= nums.length) {
                continue;
            }
            int sum = 0;
            if (memo.containsKey(n.pre)) {
                sum = memo.get(n.pre);
            }
            if (sum + nums[n.index] == total) {
                return true;
            } else if (sum + nums[n.index] < total) {
                memo.put("1" + n.pre, sum + nums[n.index]);
                memo.put("0" + n.pre, sum);
                queue.addLast(new Node(n.index + 1, true, "1" + n.pre));
                queue.addLast(new Node(n.index + 1, false, "0" + n.pre));
            } else {
                continue;
            }
        }
        return false;
    }
}

class Node {
    public int index;
    public boolean has;
    public String pre;

    public Node(int index, boolean has, String pre) {
        this.index = index;
        this.has = has;
        this.pre = pre;
    }
}