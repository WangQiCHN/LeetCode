package code;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
String s = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        // String s = "00011";
        // String s = "1";
        int result = sol.lengthLongestPath(s);
        System.out.println(result);
    }
    public int lengthLongestPath(String input) {
        List<String> routes = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int tabNum = 0;
        boolean isFile = false;
        int result = 0;
        for (char c : input.toCharArray()) {
            if (c == '\n') {
                List<String> temp = new ArrayList<>();
                for (int i = 0; i < tabNum; i++) {
                    temp.add(routes.get(i));
                }
                temp.add(sb.toString());
                if (isFile) {
                    int len = getLength(temp);
                    result = Math.max(len, result);
                }
                routes = temp;
                tabNum = 0;
                isFile = false;
                sb = new StringBuilder();
            } else if (c == '\t') {
                tabNum++;
            } else if (c == '.') {
                isFile = true;
            } else {
                sb.append(c);
            }
        }

        return result;
    }

    private int getLength(List<String> list) {
        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            result += (list.get(i).length());
        }

        return result;
    }
}