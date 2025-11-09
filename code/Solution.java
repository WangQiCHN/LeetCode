package code;

import java.util.Map;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 8;
        long result = sol.minimumOneBitOperations(n);
        System.out.println(result);
    }
    // 备忘录，缓存两个函数的计算结果
    private Map<Integer, Integer> memo_zero;
    private Map<Long, Integer> memo_power;

    public int minimumOneBitOperations(int n) {
        memo_zero = new HashMap<>();
        memo_power = new HashMap<>();
        
        // 初始化 base case
        memo_zero.put(0, 0);
        memo_zero.put(1, 1);
        
        return convert_to_zero(n);
    }

    // 将数字 num 转换为 0 的最小操作数
    private int convert_to_zero(int num) {
        // 计算过
        if (memo_zero.containsKey(num)) {
            return memo_zero.get(num);
        }

        // 找到 num 的最高有效位 (MSB) 的位置 k
        int highest_bit_pos = 31 - Integer.numberOfLeadingZeros(num);
        // 去掉最高位
        int num_prime = num ^ (1 << highest_bit_pos);
        
        // 将 num 的低位 num_prime 转换到 2^(k-1) 的状态
        // 翻转第 k 位
        // 将 2^(k-1) 转换到 0
        int res1 = convert_to_2power(num_prime, highest_bit_pos - 1);
        int res2 = convert_to_zero(1 << (highest_bit_pos - 1)); 
        int res = res1 + 1 + res2;
        
        // 记忆化
        memo_zero.put(num, res);
        return res;
    }

    // 将数字 num 转换为 2^m 的最小操作数
    private int convert_to_2power(int num, int m) {
        // 递归边界
        if (m < 0) {
            return 0;
        }
        // 目标是 2^0 = 1
        if (m == 0) {
            return (num & 1) ^ 1;
        }
        // 计算过
        // 将两个 int 参数打包成一个 long 作为 key
        long key = ((long) num << 32) | m;
        if (memo_power.containsKey(key)) {
            return memo_power.get(key);
        }
        
        int res;
        // num 的第 m 位是 1
        if (((num >> m) & 1) == 1) {
            // 目标是 2^m，只需将 num 的低位部分变为 0 即可
            int num_prime = num ^ (1 << m);
            res = convert_to_zero(num_prime);
        } else {
            // 第 m 位是 0，目标是 2^m，必须翻转第 m 位
            // 先达到 2^(m-1)，然后翻转第 m 位，再将低位部分变为0
            res = convert_to_2power(num, m - 1) + 1 +
                  convert_to_zero(1 << (m - 1));
        }
        
        // 记忆化
        memo_power.put(key, res);
        return res;
    }
}
