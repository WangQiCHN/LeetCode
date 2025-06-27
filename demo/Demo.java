package demo;

public class Demo {

    public static void main() {
        Demo d = new Demo();
        String s1 = "trinitrophenylmethylnitramine";
        String s2 = "dinitrophenylhydrazinetrinitrophenylmethylnitramine";
        System.out.println(d.checkInclusion(s1, s2 ));
    }


    private int[] count = new int[26];
    private int[] window = new int[26];
    private int size = 0;
    public boolean checkInclusion(String s1, String s2) {
        int total = 0;
        for (char c : s1.toCharArray()) {
            count[c - 'a']++;
        }
        for (int i : count) {
            if (i > 0) {
                total++;
            }
        }
        size = s1.length();

        int left = 0, right = 0;
        int correct = 0;
        int s2Len = s2.length();

        while (right < s2Len) {
            char c = s2.charAt(right);
            right++;
            window[c - 'a']++;
            if (window[c - 'a'] == count[c - 'a']) {
                correct++;
            }
            if (total == correct) {
                return true;
            }
            if (right - left >= size) {
                char d = s2.charAt(left);
                window[d - 'a']--;
                if (window[d - 'a'] - count[d - 'a'] == -1) {
                    correct--;
                }
                left++;
            }
        }

        return false;
    }

}