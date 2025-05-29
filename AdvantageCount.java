import java.util.Arrays;

public class AdvantageCount {
    public static void main() {
        AdvantageCount a = new AdvantageCount();
        int[] nums1 = {2,7,11,15};
        int[] nums2 = {1,10,4,11};
        int[] result = a.advantageCount(nums1, nums2);
        System.out.println(result);
    }
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int sz = nums1.length;
        Pair2[] copyNums2 = new Pair2[sz];

        for (int i = 0; i < sz; i++) {
            copyNums2[i] = new Pair2(i, nums2[i]);
        }

        Arrays.sort(nums1);
        Arrays.sort(copyNums2, (o1, o2) -> o2.value - o1.value);

        int[] result = new int[copyNums2.length];
        Arrays.fill(result, -1);
        int i = sz - 1;

        for (int j = 0; j < copyNums2.length; j++) {
            if (copyNums2[j].value >= nums1[i]) {
                continue;
            } else {
                result[copyNums2[j].index] = nums1[i];
                i--;
            }
        }

        int k = 0;
        for (; i >= 0; i--) {
            while (result[k] != -1) {
                k++;
            }
            result[k] = nums1[i];
            k++;
        }

        return result;
    }
}

class Pair2 {
    public int index;
    public int value;
    public Pair2(int index, int value) {
        this.index = index;
        this.value = value;
    }
}