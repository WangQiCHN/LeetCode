package demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {
        Demo d = new Demo();
        int[] s = {0,1,2,2,3,0,4,2};
        int t = 2; 
        System.out.println("Original array: " + d.removeElement(s, t));
    }
    public int removeElement(int[] nums, int val) {
        int left = 0, right = 0, n = nums.length;
        for (; right < n; right++) {
            if (nums[right] == val) {
                continue;
            } else {
                if (left != right) {
                    nums[left] = nums[right];
                    left++;
                } else {
                    left++;
                }
            }
        }

        return left;
    }
}