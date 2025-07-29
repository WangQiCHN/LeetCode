import java.util.HashSet;
import java.util.Set;

public class Demo {
    void main(String[] args) {
        int result = new Demo().rangeBitwiseAnd(9,12);
        System.out.println(result); // Output: 4
    }
    public int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        while (left != right) {
            left >>= 1;  // Right shift left number
            right >>= 1; // Right shift right number
            shift++;
        }
        return left << shift; //
    }
}

