package code.my;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        int[] nums = {10000,9999,9998,9997,9996,9995,9994,9993,9992,9991,9990,9989,9988,9987,9986,9985,9984,9983,9982,9981,9980,9979,9978,9977,9976,9975,9974,9973,9972,9971,9970,9969,9968,9967,9966,9965,9964,9963,9962,9961,9960,9959,9958,9957,9956,9955,9954,9953,9952,9951,9950,9949,9948,9947,9946,9945,9944,9943,9942,9941,9940,9939,9938,9937,9936,9935,9934,9933,9932,9931,9930,9929,9928,9927,9926,9925,9924,9923,9922,9921,9920,9919,9918,9917,9916,9915,9914,9913,9912,9911,9910,9909,9908,9907,9906,9905,9904,9903,9902,9901,9900,9899,9898,9897,9896,9895,9894,9893,9892,9891,9890,9889,9888,9887,9886,9885,9884,9883,9882,9881,9880,9879,9878,9877,9876,9875,9874,9873,9872,9871,9870,9869,9868,9867,9866,9865,9864,9863,9862,9861,9860,9859,9858,9857,9856,9855,9854,9853,9852,9851,9850,9849,9848,9847,9846,9845,9844,9843,9842,9841,9840,9839,9838,9837,9836,9835,9834,9833,9832,9831,9830,9829,9828,9827,9826,9825,9824,9823,9822,9821,9820,9819,9818,9817,9816,9815,9814,9813,9812,9811,9810,9809,9808,9807,9806,9805,9804,9803,9802,9801};
        int k = 50;
        System.out.println(new Solution().medianSlidingWindow(nums, k));
    }
    public double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> max = new PriorityQueue<>(); // 返回最小的
        PriorityQueue<Integer> min = new PriorityQueue<>((a, b) -> b.compareTo(a)); // 返回最大的
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            max.offer(nums[i]);
            if (max.size() - min.size() == 2) {
                int v = max.poll();
                min.offer(v);
            }
            if (min.size() > 0) {
                int v1 = max.peek();
                int v2 = min.peek();
                if (v1 < v2) {
                    max.poll();
                    min.poll();
                    max.offer(v2);
                    min.offer(v1);
                }
            }

            if (i + 1 >= k) {
                if (k % 2 == 1) {
                    result.add((double) max.peek());
                } else {
                    result.add(((double) max.peek() + (double) min.peek()) / 2.0);
                }
                // remove one element
                int removed = nums[i - k + 1];
                if (min.contains(removed)) {
                    min.remove(removed);
                } else if (max.contains(removed)) {
                    max.remove(removed);
                }
                if (min.size() > max.size()) {
                    int v = min.poll();
                    max.offer(v);
                } else if (max.size() - min.size() == 2) {
                    int v = max.poll();
                    min.offer(v);
                }
            }
        }

        double[] answer = new double[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}