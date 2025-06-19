package first;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class MaxSlidingWindow {
    public static void main() {
        MaxSlidingWindow window = new MaxSlidingWindow();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        window.maxSlidingWindow(nums, k);
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                window.push(nums[i]);
            } else {
                window.push(nums[i]);
                res.add(window.max());
                window.pop(nums[i - k + 1]);
            }
        }

        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }

        return arr;
    }
}

class MonotonicQueue {
    LinkedList<Integer> q = new LinkedList<>();
    public void push(int n) {
        while (!q.isEmpty() && q.getLast() < n) {
            q.pollLast();
        }
        q.addLast(n);
    }

    public int max() {
        return q.getFirst();
    }

    public void pop(int n) {
        if (n == q.getFirst()) {
            q.pollFirst();
        }
    }
}