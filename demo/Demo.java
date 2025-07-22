import java.util.ArrayList;
import java.util.HashMap;   
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        String beginWord = "cat";
        String endWord = "fin";
        List<String> wordList = new ArrayList<>();
       wordList.add("ion");wordList.add("rev");wordList.add("che");wordList.add("ind");wordList.add("lie");wordList.add("wis");wordList.add("oct");wordList.add("ham");wordList.add("jag");wordList.add("ray");wordList.add("nun");wordList.add("ref");wordList.add("wig");wordList.add("jul");wordList.add("ken");wordList.add("mit");wordList.add("eel");wordList.add("paw");wordList.add("per");wordList.add("ola");wordList.add("pat");wordList.add("old");wordList.add("maj");wordList.add("ell");wordList.add("irk");wordList.add("ivy");wordList.add("beg");wordList.add("fan");wordList.add("rap");wordList.add("sun");wordList.add("yak");wordList.add("sat");wordList.add("fit");wordList.add("tom");wordList.add("fin");wordList.add("bug");wordList.add("can");wordList.add("hes");wordList.add("col");wordList.add("pep");wordList.add("tug");wordList.add("ump");wordList.add("arc");wordList.add("fee");wordList.add("lee");wordList.add("ohs");wordList.add("eli");wordList.add("nay");wordList.add("raw");wordList.add("lot");wordList.add("mat");wordList.add("egg");wordList.add("cat");wordList.add("pol");wordList.add("fat");wordList.add("joe");wordList.add("pis");wordList.add("dot");wordList.add("jaw");wordList.add("hat");wordList.add("roe");wordList.add("ada");wordList.add("mac");

        int result = demo.ladderLength(beginWord, endWord, wordList);
        System.out.println("The length of the shortest transformation sequence is: " + result);
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return findLadders(beginWord, endWord, wordList);
    }
    
    public int findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, TreeNode> beginDict = new HashMap<>();
        Map<String, TreeNode> endDict = new HashMap<>();
        LinkedList<TreeNode> beginList = new LinkedList<>();
        LinkedList<TreeNode> endList = new LinkedList<>();
        if (!isInList(endWord, wordList)) {
            return 0;
        }

        TreeNode beginNode = new TreeNode(beginWord);
        beginDict.put(beginWord, beginNode);
        TreeNode endNode = new TreeNode(endWord);
        endDict.put(endWord, endNode);
        beginList.addLast(beginNode);
        endList.addLast(endNode);

        boolean hasResult = false;
        String sweet = null;
        while (!beginList.isEmpty() && !endList.isEmpty()) {
            if (hasResult) break;
            int sz = beginList.size();
            for (int i = 0; i < sz; i++) {
                if (hasResult) break;
                TreeNode n = beginList.pollFirst();
                for (String w : wordList) {
                    if (beginDict.containsKey(w)) {
                        continue;
                    } else {
                        if (canTransfer(n.key, w)) {
                            if (endDict.containsKey(w)) {
                                hasResult = true;
                            }
                            TreeNode wn = new TreeNode(w);
                            wn.parent = n;
                            beginDict.put(w, wn);
                            beginList.addLast(wn);
                            if (hasResult) {
                                sweet = w;
                                break;
                            }
                        }
                    }
                }
            }
            if (hasResult) break;
            sz = endList.size();
            for (int i = 0; i < sz; i++) {
                if (hasResult) break;
                TreeNode n = endList.pollFirst();
                for (String w : wordList) {
                    if (endDict.containsKey(w)) {
                        continue;
                    } else {
                        if (canTransfer(n.key, w)) {
                            if (beginDict.containsKey(w)) {
                                hasResult = true;
                            }
                            TreeNode wn = new TreeNode(w);
                            wn.parent = n;
                            endDict.put(w, wn);
                            endList.addLast(wn);
                            if (hasResult) {
                                sweet = w;
                                break;
                            }
                        }
                    }
                }
            }
        }
        int result = 0;
        if (sweet != null) {
            TreeNode sn = beginDict.get(sweet);
            while (sn != null) {
                result++;
                sn = sn.parent;
            }
            sn = endDict.get(sweet);
            while (sn != null) {
                result++;
                sn = sn.parent;
            }
            result--;
        }

        return result;
    }

    private boolean canTransfer(String p, String q) {
        char[] pc = p.toCharArray();
        char[] qc = q.toCharArray();
        int total = 0;
        int n = pc.length;
        for (int i = 0; i < n; i++) {
            if (pc[i] != qc[i]) total++;
        }

        return total == 1;
    }

    private boolean isInList(String s, List<String> list) {
        for (String l : list) {
            if (l.equals(s)) return true;
        }
        return false;
    }
}

class TreeNode {
    String key;
    TreeNode parent;

    public TreeNode(String key) {
        this.key = key;
        this.parent = null;
    }
}