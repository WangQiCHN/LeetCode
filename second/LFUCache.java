import java.util.*;

class LFUCache {
    public static void main() {
        // [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
        LFUCache a = new LFUCache(2);
        a.put(1,1);
        a.put(2,2);
        System.out.println(a.get(1));
        a.put(3,3);
        System.out.println(a.get(2));
        System.out.println(a.get(3));
        a.put(4,4);
        System.out.println(a.get(1));
        System.out.println(a.get(3));
        System.out.println(a.get(4));
    }
    private List<Node> list = new ArrayList<>();
    private int capacity;
    private HashMap<Integer, Node> cache = new HashMap<>();
    private static int COUNT = 1;
    public LFUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (cache.containsKey(key)) {
            Node n = cache.get(key);
            n.times += 1;
            n.update = LFUCache.getCount();
            sort();
            return n.value;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node n = cache.get(key);
            n.times += 1;
            n.value = value;
            n.update = LFUCache.getCount();
        } else {
            Node n = new Node(key, value);
            cache.put(key, n);
            if (list.size() < capacity) {
                list.add(n);
            } else {
                Node last = list.get(capacity - 1);
                cache.remove(last.key);
                list.add(capacity - 1, n);
            }
        }
        sort();
    }

    public static int getCount() {
        return COUNT++;
    }

    private void sort() {
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node s1, Node s2) {
                if (s1.times > s2.times) {
                    return -1;
                } else if (s1.times < s2.times) {
                    return 1;
                } else {
                    if (s1.update > s2.update) {
                        return -1;
                    } else if (s1.update < s2.update) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        });
    }
}

class Node {
    public int key;
    public int value;
    public int times;
    public int update;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.times = 1;
        this.update = LFUCache.getCount();
    }
}