import java.util.Set;
import java.util.Queue;
import java.util.HashSet;
import java.util.LinkedList;

public class OpenLock {
    public static final String START = "0000";
    public static void main() {
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        // String[] deadends = {"8888"};
        // String target = "0009";
        int num = openLock(deadends, target);
        System.out.println(num);
    }
    public static String moveUp(String value, int index) {
        char[] nums = value.toCharArray();
        if (nums[index] == '9') nums[index] = '0';
        else {
            nums[index] += 1;
        }
        return new String(nums);
    }
    public static String moveDown(String value, int index) {
        char[] nums = value.toCharArray();
        if (nums[index] == '0') nums[index] = '9';
        else {
            nums[index] -= 1;
        }
        return new String(nums);
    }
    public static int openLock(String[] deadends, String target) {
        Set<String> visited = new HashSet<String>();
        Queue<String> q = new LinkedList<String>();
        for (String s : deadends) {
            visited.add(s);
        }

        int step = 0;
        q.offer(START);

        while(!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                if (cur.equals(target)) {
                    return step;
                }
                if (visited.contains(cur)) {
                    continue;
                } else {
                    visited.add(cur);
                }

                for (int j = 0; j < 4; j++) {
                    String up = moveUp(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                    }
                    String down = moveDown(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }
}