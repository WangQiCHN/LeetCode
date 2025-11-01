package code;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = { 1, 1, 2, 2, 3, 3, 4, 4, 10, 4, 4, 3, 3, 2, 2, 1, 1 };
        boolean isTrue = s.isSelfCrossing(nums);
        System.out.println(isTrue);
    }

    public boolean isSelfCrossing(int[] distance) {
        int n = distance.length;
        if (n <= 3)
            return false;
        for (int i = 3; i < n; i++) {
            if (firstScenario(distance, i))
                return true;
            if (i == 4) {
                if (secondScenario(distance, i))
                    return true;
            }
            if (i >= 5) {
                if (thirdScenario(distance, i))
                    return true;
            }
        }

        return false;
    }

    private boolean firstScenario(int[] distance, int endIndex) {
        int n = distance[endIndex - 3];
        int w = distance[endIndex - 2];
        int s = distance[endIndex - 1];
        int e = distance[endIndex];

        if (n >= s && w <= e)
            return true;
        else
            return false;
    }

    private boolean secondScenario(int[] distance, int endIndex) {
        int n1 = distance[endIndex - 4];
        int w = distance[endIndex - 3];
        int s = distance[endIndex - 2];
        int e = distance[endIndex - 1];
        int n2 = distance[endIndex];

        if (n1 + n2 >= s && w == e)
            return true;
        else
            return false;
    }

    private boolean thirdScenario(int[] distance, int endIndex) {
        int n1 = distance[endIndex - 5];
        int w1 = distance[endIndex - 4];
        int s = distance[endIndex - 3];
        int e = distance[endIndex - 2];
        int n2 = distance[endIndex - 1];
        int w2 = distance[endIndex];

        if (n1 + n2 >= s && w1 + w2 >= e && w1 < e)
            return true;
        else
            return false;
    }
}