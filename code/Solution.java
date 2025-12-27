package code;

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] grid = { 8, 32, 31, 18, 34, 20, 21, 13, 1, 27, 23, 22, 11, 15, 30, 4, 2
        // };
        // int p = 148;
        int[] grid = { 0, 1, 3, 6, 10, 13, 15, 18 };
        String result = sol.toHex(-1);
        System.out.print(result);
    }

    public String toHex(int num) {
        int[] buffer = new int[32];
        int i = 0;
        for (i = 0; i < 32; i++) {
            buffer[i] = (num & ( << i));
        }

        StringBuilder sb = new StringBuilder();
        for (i = 0; i < 8; i++) {
            char c = getChar(buffer[32 - i * 4 - 1], buffer[32 - i * 4 - 2], buffer[32 - i * 4 - 3], buffer[32 - i * 4 - 4]);
            if (c == '0' && sb.length() == 0) {
                continue;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private char getChar(int a, int b, int c, int d) {
        int v = a * 8 + b * 4 + c * 2 + d;
        switch(v) {
            case 0: return '0';
            case 1: return '1';
            case 2: return '2';
            case 3: return '3';
            case 4: return '4';
            case 5: return '5';
            case 6: return '6';
            case 7: return '7';
            case 8: return '8';
            case 9: return '9';
            case 10: return 'a';
            case 11: return 'b';
            case 12: return 'c';
            case 13: return 'd';
            case 14: return 'e';
            case 15: return 'f';
            default: return '0';
        }
    }
}