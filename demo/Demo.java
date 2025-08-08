public class Demo {
    void main() {
        Demo d = new Demo();
        int n = 13;
        int result = d.countDigitOne(n);
        System.out.println(result);
    }
    public int countDigitOne(int n) {
        if (n <= 0) return 0;
        
        long count = 0;
        long i = 1; // represents the current digit position (1, 10, 100, ...)
        
        while (i <= n) {
            // divider is the next digit position
            long divider = i * 10;
            // count 1's in current digit position
            // (n / divider) * i counts complete sets of 1's
            // Math.min((n % divider), i) handles partial sets
            long first = (n / divider) * i;
            long second = n % divider - i + 1;
            long third = Math.min(Math.max(second, 0), i);
            count += (first + third);
            System.out.println(first + "," + second + "," + third + "," + (first + third));
            i *= 10;
        }
        System.out.println("=================");
        
        return (int) count;
    }
}
