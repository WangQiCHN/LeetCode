package demo;

public class Demo {
    public static void main(String[] args) {
        Demo d = new Demo();
        int[] nums = {2, 2, 3, 5};
        boolean result = d.canPartition(nums);
        System.out.println("Is match: " + result);
    }
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int n = nums.length;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 == 1) return false;
        sum /= 2;
        
        boolean[][] dps = new boolean[n + 1][sum + 1];
        for (int i = 0; i < n + 1; i++) {
            dps[i][0] = true;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (j < nums[i - 1]) {
                    dps[i][j] = dps[i - 1][j];
                } else {
                    dps[i][j] = dps[i - 1][j] || dps[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dps[n][sum];
    }

    // public boolean canPartition(int[] nums) {
    //     int sum = 0;
    //     int n = nums.length;
    //     for (int i : nums) {
    //         sum += i;
    //     }
    //     if (sum % 2 == 1) return false;
    //     sum /= 2;
        
    //     boolean[] dps = new boolean[sum + 1];
    //     dps[0] = true;

    //     int topJ = 0;
    //     for (int i = 0; i < n; i++) {
    //         topJ += nums[i];
    //         topJ = topJ < sum ? topJ : sum;
    //         for (int j = topJ; j >= 1; j--) {
    //             if (j < nums[i]) {
    //                 // dps[j] = dps[i - 1][j];
    //                 continue;
    //             } else {
    //                 dps[j] = dps[j] || dps[j - nums[i]];
    //             }
    //         }
    //     }

    //     return dps[sum];
    // }
}

