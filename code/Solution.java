package code;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3,8,7,8,7,5};
        int k = 2;
        int x = 2;
        long[] result = sol.findXSum(nums, k, x);
        for (long r : result) {
            System.out.println(r);
        }
    }
    private static final long OCC_KEY = 1000000001L;
    public long[] findXSum(int[] nums, int k, int x) {
        Map<Integer, Integer> occurence = new HashMap<>();
        TreeSet<Long> min = new TreeSet<>();
        TreeSet<Long> max = new TreeSet<>((a, b) -> b.compareTo(a));
        long total = 0;

        List<Long> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!occurence.containsKey(num)) {
                occurence.put(num, 1);
                long key = OCC_KEY + num;
                if (min.size() < x) {
                    min.add(key);
                    total += ((long)num);
                } else {
                    max.add(key);
                }
            } else {
                int cnt = occurence.get(num);
                occurence.put(num, cnt + 1);
                long key = OCC_KEY * cnt + num;
                if (min.contains(key)) {
                    min.remove(key);
                    min.add(key + OCC_KEY);
                    total += ((long)num);
                } else {
                    max.remove(key);
                    max.add(key + OCC_KEY);
                }
            }

            if (i >= k) {
                num = nums[i - k];
                int cnt = occurence.get(num);
                long key = OCC_KEY * cnt + num;
                if (cnt == 1) {
                    occurence.remove(num);
                } else {
                    occurence.put(num, cnt - 1);
                }
                if (min.contains(key)) {
                    min.remove(key);
                    if (cnt != 1) {
                        min.add(key - OCC_KEY);
                    }
                    total -= num;
                } else {
                    max.remove(key);
                    if (cnt != 1) {
                        max.add(key - OCC_KEY);
                    }
                }
            }

            if (min.size() == x - 1 && max.size() > 0) {
                long key = max.first();
                min.add(key);
                max.remove(key);
                total += (key % OCC_KEY) * (key / OCC_KEY);
            } else if (min.size() == x && max.size() > 0) {
                long maxKey = max.first();
                long minKey = min.first();
                if (maxKey > minKey) {
                    total -= (minKey % OCC_KEY) * (minKey / OCC_KEY);
                    total += (maxKey % OCC_KEY) * (maxKey / OCC_KEY);
                    max.remove(maxKey);
                    min.remove(minKey);
                    max.add(minKey);
                    min.add(maxKey);
                }
            }
            if (i + 1 >= k) {
                result.add(total);
            }
        }

        return toLong(result);
    }

    private long[] toLong(List<Long> result) {
        long[] answer = new long[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}