import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Subsets {
    public static void main() {
        Subsets s = new Subsets();
        int[] nums = {1,2,3};
        s.subsets(nums);
    }
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> item = new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        result.add(new LinkedList<>(item));
        traverse(nums, 0);

        return result;
    }

    private void traverse(int[] nums, int start) {
        int n = nums.length;
        for (int i = start; i < n; i++) {
            item.addLast(nums[i]);
            result.add(new LinkedList<>(item));
            traverse(nums, i + 1);
            item.removeLast();
        }
    }
}
