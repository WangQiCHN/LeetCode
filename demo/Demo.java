package demo;


public class Demo {
    public static void main() {
        Demo d = new Demo();
        int nums[] = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(d.lengthOfLIS(nums));
    }

    public int lengthOfLIS(int[] nums) {
        int piles = 0;
        int n = nums.length;
        int[] tops = new int[n];

        for (int i = 0; i < n; i++) {
            int v = nums[i];
            int left = 0, right = piles;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tops[mid] > v) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            if (left == piles) {
                tops[piles] = v;
                piles++;
            } else {
                tops[left] = v;
            }
        }

        return piles;
    }
}