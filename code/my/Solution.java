package code.my;

import java.util.Map;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {
        String s1 = "aaa";
        String s2 = "aaaaa";
        int n1 = 20;
        int n2 = 1;
        System.out.println(new Solution().getMaxRepetitions(s1, n1, s2, n2));
    }

    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        Map<Integer, Record> dict = new HashMap<>();
        int s1Cnt = 0, s2Cnt = 0;
        int j = 0;
        Record cnt, prev;
        while (true) {
            s1Cnt++;
            for (int i = 0; i < s1.length(); i++) {
                char c = s1.charAt(i);
                if (c == s2.charAt(j)) {
                    j++;
                    if (j == s2.length()) {
                        j = 0;
                        s2Cnt++;
                    }
                }
            }
            if (s1Cnt == n1) {
                return s2Cnt / n2;
            }

            if (dict.containsKey(j)) {
                prev = dict.get(j);
                cnt = new Record(s1Cnt - prev.s1Cnt, s2Cnt - prev.s2Cnt);
                break;
            } else {
                dict.put(j, new Record(s1Cnt, s2Cnt));
            }
        }
        int answer = prev.s2Cnt + (n1 - prev.s1Cnt) / cnt.s1Cnt * cnt.s2Cnt;
        int rest = (n1 - prev.s1Cnt) % cnt.s1Cnt;
        int k = j;
        for (int i = 0; i < rest; i++) {
            for (j = 0; j < s1.length(); j++) {
                char c = s1.charAt(j);
                if (c == s2.charAt(k)) {
                    k++;
                    if (k == s2.length()) {
                        k = 0;
                        answer++;
                    }
                }
            }
        }
        return answer / n2;
    }
}

class Record {
    int s1Cnt;
    int s2Cnt;

    public Record(int s1Cnt, int s2Cnt) {
        this.s1Cnt = s1Cnt;
        this.s2Cnt = s2Cnt;
    }
}