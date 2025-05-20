import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

class PermuteUnique {
    private List<List<Integer>> res = new LinkedList<>();
    private List<Integer> track = new LinkedList<>();
    private boolean[] used;

    public static void main() {
        PermuteUnique p = new PermuteUnique();
        int[] nums = {1,1,2};
        p.permuteUnique(nums);
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        used = new boolean[nums.length];
        backtrack(nums);
        return res;
    }

    private void backtrack(int[] nums) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<Integer>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            // 这边的!used[i - 1]表示前面已经分析过一样的数字了
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            track.addLast(nums[i]);
            used[i] = true;
            backtrack(nums);
            track.removeLast();
            used[i] = false;
        }
    }
}