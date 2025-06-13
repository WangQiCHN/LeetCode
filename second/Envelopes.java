import java.util.Arrays;
import java.util.Comparator;

public class Envelopes {
    public static void main() {
        Envelopes e = new Envelopes();
        int[][] envelopes = {{4,5},{4,6},{6,7},{2,3},{1,1}};
        int result = e.maxEnvelopes(envelopes);
        System.out.println(result);
    }
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
            }
        });

        int n = envelopes.length;
        int[] heights = new int[n];
        int[] dps = new int[n];
        Arrays.fill(dps, 1);
        for (int i = 0; i < n; i++) {
            heights[i] = envelopes[i][1];
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (heights[i] < heights[j]) {
                    if (dps[i] < dps[j] + 1) {
                        dps[i] = dps[j] + 1;
                    }
                }
            }
        }

        int max = 1;
        for (int i = 0; i < n; i++) {
            if (max < dps[i]) {
                max = dps[i];
            }
        }

        return max;
    }
}