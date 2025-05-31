package second;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Permutations {
    public static void main() {
        Permutations p = new Permutations();
        int[] nums = {1,2,3};
        List<List<Integer>> result = p.permute(nums);
        System.out.println(result);
    }
    private HashSet<Integer> used = new HashSet<>();
    private LinkedList<Integer> tmp = new LinkedList<>();
    private List<List<Integer>> result = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        calculate(nums);
        return result;
    }

    private void calculate(int[] nums) {
        if (used.size() == nums.length) {
            result.add(new LinkedList<>(tmp));
        }
        int sz = nums.length;
        for (int i = 0; i < sz; i++) {
            if (used.contains(nums[i])) {
                continue;
            } else {
                int n = nums[i];
                used.add(n);
                tmp.addLast(n);
                calculate(nums);
                tmp.removeLast();
                used.remove(n);
            }
        }
    }
}
