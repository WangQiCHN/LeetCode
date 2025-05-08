import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

class MinWindow {
    public static void main() {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        // String s = "a";
        // String t = "aa";
        String v = minWindow(s, t);
        System.out.println(v);
    }
    public static String minWindow(String s, String t) {
        Map<String, Integer> tEncode = encodeStr(t);
        int sz = s.length();
        boolean hasAnswer = false;
        int start = 0, end = 1;
        int minStart = 0, minEnd = sz;
        Map<String, Integer> sEncode = new HashMap<String, Integer>();
        char c = s.charAt(start);
        sEncode.put(c + "", 1);
        for (;end <= sz;) {
            if (contain(sEncode, tEncode)) {
                if (!hasAnswer) {
                    minStart = start;
                    minEnd = end;
                    hasAnswer = true;
                } else {
                    if (end - start < minEnd - minStart) {
                        minStart = start;
                        minEnd = end;
                    }
                }
                c = s.charAt(start);
                sEncode.put(c + "", sEncode.get(c + "") - 1);
                start++;
                continue;
            } else {
                if (end == sz) {
                    break;
                }
                c = s.charAt(end);
                Integer num = sEncode.get(c + "");
                if (num == null) {
                    sEncode.put(c + "", 1);
                } else {
                    sEncode.put(c + "", num + 1);
                }
                end++;
            }
        }

        return hasAnswer ? s.substring(minStart, minEnd) : "";
    }

    private static Map<String, Integer> encodeStr(String t) {
        Map<String, Integer> value = new HashMap<String, Integer>();
        char[] characters = t.toCharArray();
        for (char c : characters) {
            Integer num = value.get(c + "");
            if (num == null) {
                value.put(c + "", 1);
            } else {
                value.put(c + "", num + 1);
            }
        }

        return value;
    }

    private static boolean contain(Map<String, Integer> a, Map<String, Integer> b) {
        Iterator<String> iter  = b.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            Integer value = b.get(key);

            Integer aValue = a.get(key);
            if (aValue == null || aValue < value) {
                return false;
            }
        }

        return true;
    }
}