import java.util.LinkedList;
import java.util.List;

public class CounterSmaller {
    public static void main() {
        CounterSmaller c = new CounterSmaller();
        int[] nums = new int[] {5,6,4,3,2,1};
        c.countSmaller(nums);
    }

    private class Pair {
        int val, id;
        Pair(int v, int i) {
            this.val = v;
            this.id = i;
        }
    }

    private Pair[] tmp;
    private int[] count;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        count = new int[n];
        tmp = new Pair[n];

        Pair[] arr = new Pair[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }

        sort(arr, 0, n-1);
        List<Integer> res = new LinkedList<>();
        for (int c : count) {
            res.add(c);
        }

        return res;
    }

    private void sort(Pair[] arr, int lo, int hi) {
        if (lo == hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    private void merge(Pair[] arr, int lo, int mid, int hi) {
        for (int  i = lo; i <= hi; i++) {
            tmp[i] = arr[i];
        }

        int i =  lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                arr[p] = tmp[j++];
            } else if (j == hi + 1) {
                // the right part is finished
                arr[p] = tmp[i++];
                count[arr[p].id] += j - mid - 1;
            } else if (tmp[i].val < tmp[j].val) {
                arr[p] = tmp[j++];
            } else {
                // the left > right
                arr[p] = tmp[i++];
                count[arr[p].id] += j - mid - 1;
            }
        }
    }
}
