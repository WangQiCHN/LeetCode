package code;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> stack = new ArrayList<>();
        
        // Process each number in the array
        for (int num : nums) {
            stack.add(num);
            // Check and replace non-coprime pairs
            while (stack.size() >= 2) {
                int n1 = stack.get(stack.size() - 1); // Top
                int n2 = stack.get(stack.size() - 2); // Second from top
                int gcd = gcd(n1, n2);
                if (gcd == 1) break; // Coprime, no replacement needed
                // Replace with LCM
                stack.remove(stack.size() - 1);
                stack.remove(stack.size() - 1);
                stack.add(lcm(n1, n2, gcd));
            }
        }
        
        // Final pass to ensure no adjacent non-coprime numbers remain
        for (int i = stack.size() - 2; i >= 0; i--) {
            int n1 = stack.get(i);
            int n2 = stack.get(i + 1);
            int gcd = gcd(n1, n2);
            if (gcd != 1) {
                // Replace with LCM
                stack.set(i, lcm(n1, n2, gcd));
                stack.remove(i + 1);
                // Re-check from the modified position
                i = Math.min(i + 1, stack.size() - 2);
            }
        }
        
        return stack;
    }
    
    // Compute GCD using Euclidean algorithm
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    // Compute LCM using GCD
    private int lcm(int a, int b, int gcd) {
        return (int) ((long) a * b / gcd);
    }
}