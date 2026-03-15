package code.my;

import java.util.*;

class Solution {
  public static void main(String[] args) {
    int[] nums = { 0, 4, 6, 0, 3, 2, 6, 9, 4, 1 };
    int k = 3;
    double[] result = new Solution().medianSlidingWindow(nums, k);
    System.out.println(result);
  }

  public double[] medianSlidingWindow(int[] nums, int k) {
    PriorityQueue<Integer> max = new PriorityQueue<>(); // 返回最小的
    PriorityQueue<Integer> min = new PriorityQueue<>((a, b) -> b.compareTo(a)); // 返回最大的
    double[] result = new double[nums.length - k + 1];
    int[] sizes = new int[2]; // maxSize - sizes[0], minSize - sizes[1]
    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      if (sizes[0] == 0 || num >= max.peek()) {
        sizes[0]++;
        max.offer(num);
      } else {
        sizes[1]++;
        min.offer(num);
      }
      // make balance
      if (sizes[0] - sizes[1] == 2) {
        min.offer(max.poll());
        sizes[1]++;
        sizes[0]--;
      } else if (sizes[0] - sizes[1] == -1) {
        max.offer(min.poll());
        sizes[0]++;
        sizes[1]--;
      }

      if (i + 1 >= k) {
        if (k % 2 == 1) {
          result[i + 1 - k] = (double) max.peek();
        } else {
          result[i + 1 - k] = ((double) max.peek() + (double) min.peek()) / 2.0;
        }
        // remove one element
        int removed = nums[i - k + 1];
        if (min.contains(removed)) {
          min.remove(removed);
          sizes[1]--;
        } else if (max.contains(removed)) {
          max.remove(removed);
          sizes[0]--;
        }
        // make balance
        if (sizes[0] - sizes[1] == 2) {
          min.offer(max.poll());
          sizes[1]++;
          sizes[0]--;
        } else if (sizes[0] - sizes[1] == -1) {
          max.offer(min.poll());
          sizes[0]++;
          sizes[1]--;
        }
      }
    }

    return result;
  }
}