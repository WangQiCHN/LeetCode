package demo;
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Demo d = new Demo();
        d.insert(1);
        d.remove(2);
        d.insert(2);
        d.getRandom();
        d.remove(1);
        d.insert(2);
        d.getRandom();
    }
    private List<Integer> cache = new ArrayList<>();
    private Map<Integer, Integer> val2Index = new HashMap<>();
    public Demo() {
        
    }
    
    public boolean insert(int val) {
        if (val2Index.containsKey(val)) {
            return false;
        } else {
            cache.add(val);
            int size = cache.size();
            val2Index.put(val, size - 1);
            return true;
        }
    }
    
    public boolean remove(int val) {
        if (val2Index.containsKey(val)) {
            int size = cache.size();
            int index = val2Index.get(val);
            if (size - 1 != index) {
                int temp = cache.get(size - 1);
                val2Index.put(temp, index);
                cache.set(index, temp);
            }
            val2Index.remove(val);
            cache.remove(size - 1);
            return true;
        } else {
            return false;
        }
    }
    
    public int getRandom() {
        Random r = new Random();
        return cache.get(r.nextInt() % cache.size());
    }
}

