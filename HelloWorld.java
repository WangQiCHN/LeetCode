import java.util.List;
import java.util.ArrayList;

public class HelloWorld {
    public static void main() {
        int m = 5, n = 10;
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = i + m;
        }

        System.out.println(nums[n - 1]);
        // test arrarylist
        List<String> strings = new ArrayList<String>();
        strings.add("hello world");
        System.out.println(strings.get(0));
    }
}