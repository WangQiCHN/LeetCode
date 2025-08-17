public class Demo {
    void main() {
        Demo d = new Demo();
        int n = 18;
        int result = d.nthUglyNumber(n);
        System.out.println(result);
    }
    public int nthUglyNumber(int n) {
        // Array to store ugly numbers, 1-based indexing for simplicity
        long[] ugly = new long[n + 1];
        ugly[1] = 1; // First ugly number is 1
        
        // Pointers for the next multiples of 2, 3, and 5
        int i2 = 1, i3 = 1, i5 = 1;
        
        // Generate ugly numbers from 2nd to nth
        for (int i = 2; i <= n; i++) {
            // Compute next possible ugly numbers
            long next2 = ugly[i2] * 2;
            long next3 = ugly[i3] * 3;
            long next5 = ugly[i5] * 5;
            
            // Take the minimum to ensure the sequence is in order
            long nextUgly = Math.min(next2, Math.min(next3, next5));
            ugly[i] = nextUgly;
            
            // Increment the pointer(s) corresponding to the chosen multiple
            if (nextUgly == next2) i2++;
            if (nextUgly == next3) i3++;
            if (nextUgly == next5) i5++;
        }
        
        // Return the nth ugly number
        return (int) ugly[n];
    }
}
