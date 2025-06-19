package first;
import java.util.*;

import LFUCache;
class LFUCache {
    private int cap;
    private HashMap<Integer, Integer> cache = new HashMap<>();
    private HashMap<Integer, Integer> freq = new HashMap<>();
    private HashMap<Integer, LinkedHashSet<Integer>> freqToKeys = new HashMap<Integer, LinkedHashSet<Integer>>();

    public static void main() {
        LFUCache cache = new LFUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        cache.get(1);
        cache.put(3, 3);
        cache.get(2);
        cache.get(3);
        cache.put(4, 4);
        cache.get(1);
        cache.get(3);
        int v = cache.get(4);
        System.out.println(v);
    }

    public LFUCache(int capacity) {
        this.cap = capacity;
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        } else {
            Integer v = cache.get(key);

            updateFrequent(key);

            return v;
        }
    }
    
    public void put(int key, int value) {
        if (cap == cache.size() && !cache.containsKey(key)) {
            removeLeastKey();
        }
        cache.put(key, value);
        updateFrequent(key);
    }

    private void removeLeastKey() {
        Iterator<Integer> iter = freqToKeys.keySet().iterator();
        int min = Integer.MAX_VALUE;
        while (iter.hasNext()) {
            Integer f = iter.next();
            if (f < min) {
                min = f;
            }
        }
        if (min == Integer.MAX_VALUE) {
            return;
        }
        LinkedHashSet<Integer> keys = freqToKeys.get(min);
        int delKey = keys.iterator().next();
        keys.remove(delKey);
        if (keys.isEmpty()) {
            freqToKeys.remove(min);
        }
        cache.remove(delKey);
        freq.remove(delKey);
    }

    private void updateFrequent(int key) {
        Integer f = freq.get(key);
        if (f == null) {
            f = 1;
        } else {
            f++;
        }
        freq.put(key, f);

        LinkedHashSet<Integer> keys = null;
        if (f != 1) {
            keys = freqToKeys.get(f - 1);
        }
        if (keys != null) {
            keys.remove(key);
            if (keys.isEmpty()) {
                freqToKeys.remove(f - 1);
            }
        }
        if (!freqToKeys.containsKey(f)) {
            freqToKeys.put(f, new LinkedHashSet<Integer>());
        }
        LinkedHashSet<Integer> newKeys = freqToKeys.get(f);
        newKeys.add(key);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */