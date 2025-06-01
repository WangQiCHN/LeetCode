import java.util.*;

class OpenLock {
    public static void main() {
        OpenLock o2 = new OpenLock();
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        int step = o2.openLock(deadends, target);
        System.out.println(step);
    }
    public int openLock(String[] deadends, String target) {
        Queue<String> q = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();


        for (String d : deadends) {
            visited.add(d);
        }

        int step = 0;
        q.offer("0000");
        visited.add("0000");

        while (!q.isEmpty()) {
            int sz = q.size();

            for (int i = 0; i < sz; i++) {
                String v = q.poll();
                if (v.equals(target)) {
                    return step;
                }

                for (int j = 0; j < 4; j++) {
                    String str = goUp(v, j);
                    if (!visited.contains(str)) {
                        q.offer(str);
                        visited.add(str);
                    }
                    str = goDown(v, j);
                    if (!visited.contains(str)) {
                        q.offer(str);
                        visited.add(str);
                    }
                }
            }

            step++;
        }

        return -1;
    }

    private String goUp(String s, int index) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (i != index) {
                char c = s.charAt(i);
                sb.append(c);
            } else {
                char c = s.charAt(i);
                if (c == '9') {
                    sb.append('0');
                } else {
                    c++;
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }

    private String goDown(String s, int index) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (i != index) {
                char c = s.charAt(i);
                sb.append(c);
            } else {
                char c = s.charAt(i);
                if (c == '0') {
                    sb.append('9');
                } else {
                    c--;
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }
}