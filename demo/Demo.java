import java.util.ArrayList;
import java.util.HashMap;   
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        String beginWord = "red";
        String endWord = "tax";
        List<String> wordList = new ArrayList<>();
        wordList.add("ted");
        wordList.add("tex");
        wordList.add("red");
        wordList.add("tax");
        wordList.add("tad");
        wordList.add("den");
        wordList.add("rex");
        wordList.add("pee");

        List<List<String>> result = demo.findLadders(beginWord, endWord, wordList);
        for (List<String> item : result) {
            System.out.println(item);

        }


    }
    List<List<String>> result = new ArrayList<>();
    Map<String, Integer> visited = new HashMap<String, Integer>();
    Map<String, TreeNode> dict = new HashMap<String, TreeNode>();
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!testLastWord(endWord, wordList)) return result;
        TreeNode b = new TreeNode(beginWord);
        visited.put(beginWord, 0);
        dict.put(beginWord, b);
        TreeNode e = new TreeNode(endWord);
        visited.put(endWord, -1);
        dict.put(endWord, e);
        traverse(b, e, wordList);
        return result;
    }

    private boolean testLastWord(String s, List<String> wordList) {
        for (String w : wordList) {
            if (s.equals(w)) return true;
        }
        return false;
    }

    private void traverse(TreeNode b, TreeNode e, List<String> wordList) {
        if (canTransfer(b.key, e.key)) {
            // get result
            LinkedList<String> item = new LinkedList<>();
            TreeNode t = e;
            while (t != null) {
                item.addLast(t.key);
                t = t.parent;
            }
            t = b;
            while (t != null) {
                item.addFirst(t.key);
                t = t.parent;
            }
            result.add(new ArrayList<>(item));
            return ;
        }
        for (String w : wordList) {
            if (visited.containsKey(w)) {
                continue;
            } else {
                if (canTransfer(b.key, w)) {
                    TreeNode wn = new TreeNode(w);
                    wn.parent = b;
                    b.next.add(wn);
                    visited.put(w, 1);
                    dict.put(w, wn);
                }
            }
        }
        boolean has = false;
        for (String w : wordList) {
            boolean isResult = false;
            if (visited.containsKey(w)) {
                int v = visited.get(w);
                if (v == -1) continue;
                else if (v == 1) isResult = true;
            }
            boolean isTran = canTransfer(e.key, w);
            if (isTran && !isResult) {
                TreeNode wn = new TreeNode(w);
                wn.parent = e;
                e.next.add(wn);
                visited.put(w, -1);
                dict.put(w, wn);
            } else if (isTran && isResult) {
                // get result
                LinkedList<String> item = new LinkedList<>();
                TreeNode t = e;
                while (t != null) {
                    item.addLast(t.key);
                    t = t.parent;
                }
                TreeNode other = dict.get(w);
                while (other != null) {
                    item.addFirst(other.key);
                    other = other.parent;
                }
                result.add(new ArrayList<>(item));
                has = true;
            }
        }
        if (has) return;

        List<TreeNode> bn = b.next;
        List<TreeNode> en = e.next;

        if (bn.size() == 0 || en.size() == 0) return;
        else {
            for (TreeNode bx : bn) {
                for (TreeNode ex : en) {
                    traverse(bx, ex, wordList);
                    visited.remove(ex.key);
                    dict.remove(ex.key);
                }
                visited.remove(bx.key);
                dict.remove(bx.key);
            }
        }
    }

    

    private boolean canTransfer(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int n = c1.length;
        int total = 0;
        for (int i = 0; i < n; i++) {
            if (c1[i] != c2[i]) total++;
        }
        return total == 1 ? true : false;
    }
}

class TreeNode {
    String key;
    List<TreeNode> next;
    TreeNode parent;

    public TreeNode(String key) {
        this.key = key;
        this.next = new ArrayList<>();
        this.parent = null;
    }
}