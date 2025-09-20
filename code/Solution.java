package code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
  void main() {
    int n = 2;
    int[][] languages = { { 2 }, { 1 }, { 2, 1 }, { 1 }, { 1, 2 }, { 1 }, { 2 }, { 1 }, { 1 }, { 2 }, { 1, 2 },
        { 1, 2 }, { 1, 2 }, { 2, 1 }, { 1 }, { 2 }, { 1, 2 } };
    int[][] friendships = { { 15, 16 }, { 4, 13 }, { 3, 16 }, { 5, 14 }, { 1, 7 }, { 2, 11 }, { 3, 15 }, { 4, 16 },
        { 7, 9 }, { 6, 13 }, { 6, 16 }, { 4, 10 }, { 6, 9 }, { 5, 6 }, { 7, 12 }, { 6, 12 }, { 3, 7 }, { 4, 7 },
        { 8, 10 } };

    System.out.println(minimumTeachings(n, languages, friendships));
  }

  public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
    List<Set<Integer>> dict = new ArrayList<>();
    for (int[] lan : languages) {
      Set<Integer> set = new HashSet<>();
      for (int l : lan) {
        set.add(l);
      }
      dict.add(set);
    }

    List<int[]> problems = new ArrayList<>();
    for (int[] friend : friendships) {
      int l = friend[0] - 1;
      int r = friend[1] - 1;
      Set<Integer> first = dict.get(l);
      Set<Integer> second = dict.get(r);

      Set<Integer> intersection = new HashSet<>(first);
      intersection.retainAll(second); // 取交集
      if (intersection.isEmpty()) {
        problems.add(friend);
      }
    }

    if (problems.isEmpty()) {
      return 0;
    }

    int result = n;
    for (int k = 1; k <= n; k++) {
      Set<Integer> tmp = new HashSet<>();
      for (int[] p : problems) {
        int l = p[0] - 1;
        int r = p[1] - 1;
        Set<Integer> first = dict.get(l);
        Set<Integer> second = dict.get(r);

        if (!first.contains(k))
          tmp.add(l);
        if (!second.contains(k))
          tmp.add(r);
      }
      result = Math.min(result, tmp.size());
    }

    return result;
  }
}