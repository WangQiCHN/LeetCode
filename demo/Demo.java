import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Demo {
    void main(String[] args) {
        Demo d = new Demo();
        d.addWord("bad");
        d.addWord("dad");
        boolean result = d.search("bad");
        System.out.println("Search 'bad': " + result);
        result = d.search(".ad");
        System.out.println("Search '.ad': " + result);
    }

    Set<String> dict = new HashSet<>();
    public Demo() {
        
    }
    
    public void addWord(String word) {
        dict.add(word);
    }
    
    public boolean search(String word) {
        int index = word.indexOf(".");
        if (index == -1) {
            return dict.contains(word);
        } else {
            char[] cs = word.toCharArray();
            int first = -1, second = -1;
            for (int i = 0; i < cs.length; i++) {
                if (cs[i] == '.') {
                    if (first == -1) first = i;
                    else {
                        second = i;
                        break;
                    }
                }
            }
            return checkDotWord(word, first, second);
        }
    }

    private boolean checkDotWord(String word, int first, int second) {
        char[] cs = word.toCharArray();
        for (int i = 0; i < 26; i++) {
            cs[first] = (char)(i + 'a');
            String v = new String(cs);
            if (dict.contains(v)) return true;
        }

        return false;
    }
}
