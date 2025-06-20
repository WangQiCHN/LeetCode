package demo;

import java.util.Map;
import java.util.Stack;
import java.util.HashMap;

public class Demo {
    public static void main() {
        Demo d = new Demo();
        int[] temperatures = {89,62,70,58,47,47,46,76,100,70};
        d.dailyTemperatures(temperatures);
    }
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result  = new int[temperatures.length];
        Stack<Integer> s = new Stack<>();
        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && temperatures[s.peek()] < temperatures[i]) {
                s.pop();
            }
            if (s.isEmpty()) {
                result[i] = 0;
            } else {
                result[i] = s.peek() - i;
            }
            
            s.push(i);
        }

        return result;
    }
}
