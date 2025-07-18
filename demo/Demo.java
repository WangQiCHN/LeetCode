package demo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class Demo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        int n = 2;
        List<Integer> result = demo.grayCode(n);
        System.out.println(result);  // Output: /home/user/Pictures
    }

    private Set<Integer> visited = new HashSet<>();
    private LinkedList<Integer> list = new LinkedList<>();
    public List<Integer> grayCode(int n) {
        visited.add(0);
        list.addLast(0);
        findNext(0, n);

        return list;
    }

    private boolean findNext(int v, int n) {
        for (int i = 0; i < n; i++) {
            int newV = (v ^ (1 << i));
            if (visited.contains(newV)) {
                continue;
            } else {
                visited.add(newV);
                list.addLast(newV);
                if (findNext(newV, n)) {
                    return true;
                }
                visited.remove(newV);
                list.removeLast();
            }
        }

        return false;
    }
}