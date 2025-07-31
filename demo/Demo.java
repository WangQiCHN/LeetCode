import java.util.HashMap;
import java.util.Map;

public class Demo {
    void main(String[] args) {
        String result = new Demo().fractionToDecimal(1,2);
        System.out.println("Maximum points on a line: " + result);
    }

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        
        StringBuilder result = new StringBuilder();
        // Handle sign: negative if exactly one of numerator or denominator is negative
        if (numerator < 0 ^ denominator < 0) result.append("-");
        
        // Convert to long to avoid overflow
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        
        // Integer part
        result.append(num / den);
        long remainder = num % den;
        if (remainder == 0) return result.toString();
        
        // Fractional part
        result.append(".");
        Map<Long, Integer> remainderMap = new HashMap<>();
        while (remainder != 0) {
            if (remainderMap.containsKey(remainder)) {
                // Repeating decimal found
                result.insert(remainderMap.get(remainder), "(");
                result.append(")");
                break;
            }
            remainderMap.put(remainder, result.length());
            remainder *= 10;
            result.append(remainder / den);
            remainder %= den;
        }
        
        return result.toString();
    }
}
