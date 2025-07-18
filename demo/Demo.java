package demo;

import java.util.LinkedList;

class Demo {
    public static void main(String[] args) {
        String s = "/home/user/Documents//../Pictures/";
        Demo demo = new Demo();
        String result = demo.simplifyPath(s);
        System.out.println(result);  // Output: /home/user/Pictures
    }

    public String simplifyPath(String path) {
        String[] strs = path.split("/");
        LinkedList<String> q = new LinkedList<>();

        for (String s : strs) {
            if (s.equals("..")) {
                q.removeLast();
            } else {
                q.addLast(s);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            String v = q.pollFirst();
            sb.append("/" + v);
        }

        return sb.toString();


    }
}