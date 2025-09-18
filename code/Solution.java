package code;

class Solution {
    void main() {
        int num1 = 112577768;
        int num2 = -501662198;
        System.out.println(makeTheIntegerZero(num1, num2));
    }
    // public int makeTheIntegerZero(int num1, int num2) {
    //     // Handle edge cases where num1 cannot be reduced to zero
    //     if (num1 < num2) return -1;
        
    //     // Iterate over possible number of operations (k)
    //     for (int k = 0; k <= 60; k++) {
    //         long t = (long) num1 - (long) k * num2; // Compute num1 - k * num2
    //         if (t < 0) break; // If t becomes negative, further k values will also be negative
            
    //         // Count number of 1s in binary representation of t
    //         int popcount = Long.bitCount(t);
            
    //         // Check if t can be represented as sum of k powers of 2
    //         // Conditions: popcount <= k (can combine powers of 2) and t >= k (sum of k powers of 2 is at least k)
    //         if (popcount <= k && t >= k) {
    //             return k; // k is the minimum number of operations
    //         }
    //     }
        
    //     return -1; // No valid k found
    // }

    public int makeTheIntegerZero(int num1, int num2) {
        if (num1 < num2) return -1;

        for (int k = 0; k <= 60; k++) {
            // long t = (long)num1 - (long)(k * num2);
            long t = (long) num1 - (long) k * num2; // Compute num1 - k * num2
            if (t < 0) break;
            long count = Long.bitCount(t);

            if (count <= k && k <= t) {
                return k;
            }
        }

        return -1;
    }
}