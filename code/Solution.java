package code;

import java.util.*;

class Solution {
    void main() {
        int n = 17;
        int[][] languages = {{4,7,2,14,6},{15,13,6,3,2,7,10,8,12,4,9},{16},{10},{10,3},{4,12,8,1,16,5,15,17,13},{4,13,15,8,17,3,6,14,5,10},{11,4,13,8,3,14,5,7,15,6,9,17,2,16,12},{4,14,6},{16,17,9,3,11,14,10,12,1,8,13,4,5,6},{14},{7,14},{17,15,10,3,2,12,16,14,1,7,9,6,4}};
        int[][] friendships = {{4,11},{3,5},{7,10},{10,12},{5,7},{4,5},{3,8},{1,5},{1,6},{7,8},{4,12},{2,4},{8,9},{3,10},{4,7},{5,12},{4,9},{1,4},{2,8},{1,2},{3,4},{5,10},{2,7},{1,7},{1,8},{8,10},{1,9},{1,10},{6,7},{3,7},{8,12},{7,9},{9,11},{2,5},{2,3}};
        System.out.println(minimumTeachings(n, languages, friendships));
    }
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        List<int[]> problems = new ArrayList<>();
        for (int[] friend : friendships) {
            int first = friend[0] - 1;
            int second = friend[1] - 1;
            if (!canCommunicate(languages, first, second)) {
                problems.add(friend);
            }
        }

        if (problems.isEmpty()) return 0;

        int min = Integer.MAX_VALUE;
        for (int lan = 1; lan <= n; lan++) {
            Set<Integer> tmp = new HashSet<>();
            for (int[] p : problems) {
                int first = p[0] - 1;
                int second = p[1] - 1;
                if (noLan(languages, first, lan)) {
                    tmp.add(first);
                }
                if (noLan(languages, second, lan)) {
                    tmp.add(second);
                }
            }
            min = Math.min(min, tmp.size());
        }

        return min;
    }

    private boolean canCommunicate(int[][] languages, int first, int second) {
        int[] fLan = languages[first];
        int[] sLan = languages[second];

        Set<Integer> s = new HashSet<>();
        for (int l : fLan) {
            s.add(l);
        }
        for (int l : sLan) {
            s.add(l);
        }

        return s.size() < (fLan.length + sLan.length);
    }

    private boolean noLan(int[][] languages, int person, int newLan) {
        int[] lans = languages[person];
        for (int l : lans) {
            if (l == newLan) return true;
        }
        return false;
    }
}