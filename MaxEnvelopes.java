import java.util.Arrays;
import java.util.Comparator;

public class MaxEnvelopes {
    public static void main() {
        int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
        MaxEnvelopes me = new MaxEnvelopes();
        int result = me.maxEnvelopes(envelopes);
        System.out.println(result);
    }
    // private int[] dps;
    public int maxEnvelopes(int[][] envelopes) {
        int maxSize = 1;
        if (envelopes.length == 1) {
            return maxSize;
        }
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] v1, int[] v2) {
                return v1[0] == v2[0] ? v2[1] - v1[1] : v1[0] - v2[0];
            }
        });

        // dps = new int[envelopes.length];
        // Arrays.fill(dps, 1);


        int[] height = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLIS(height);

        // for (int i = 1; i < envelopes.length; i++) {
        //     if (maxSize < dps[i]) {
        //         maxSize = dps[i];
        //     }
        // }

        // return maxSize;
    }

    // private void calculate(int[][] envelopes, int index) {
    //     for (int i = 0; i < index; i++) {
    //         if (envelopes[i][1] < envelopes[index][1] && envelopes[i][0] < envelopes[index][0]) {
    //             dps[index] = dps[index] > dps[i] + 1 ? dps[index] : dps[i] + 1;
    //         }
    //     }
    // }

    private int lengthOfLIS(int[] nums) {
        int[] top = new int[nums.length];
        int piles = 0;

        for (int i = 0; i < nums.length; i++) {
            int poker = nums[i];

            int left = 0, right = piles;
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] > poker) {
                    right = mid;
                } else if (top[mid] < poker) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (left == piles) piles++;
            top[left] = poker;
        }

        return piles;
    }
}