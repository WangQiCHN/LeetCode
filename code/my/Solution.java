package code.my;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 1, 1, 1, 1 };
        System.out.println(new Solution().findSubsequences(nums));
    }

    List<List<Integer>> result = new ArrayList<>();
    Set<String> set = new HashSet<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        LinkedList<Integer> list = new LinkedList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                list.add(nums[i]);
                bfs(nums, i + 1, list);
                list.removeLast();
            }
        }
        return result;
    }

    private void bfs(int[] nums, int start, LinkedList<Integer> list) {
        if (list.size() >= 2) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.addAll(list);
            String key = getKey(arrayList);
            if (!set.contains(key)) {
                result.add(arrayList);
                set.add(key);
            }
        }
        int n = nums.length;
        for (int i = start; i < n; i++) {
            if (nums[i] >= list.peekLast() && (i == start || nums[i] != nums[i - 1])) {
                list.add(nums[i]);
                bfs(nums, i + 1, list);
                list.removeLast();
            }
        }
    }

    private String getKey(ArrayList<Integer> arrayList) {
        StringBuffer sb = new StringBuffer();
        for (int v : arrayList) {
            sb.append(v);
            sb.append('#');
        }

        return sb.toString();
    }
}