
public class Demo {
    private DoubleList buffer;
    void main(String[] args) {
        String v = "1.2";
        String[] result = v.split("\\.");
        System.out.println(result.length);
    }
    public Demo() {
        buffer = new DoubleList();
    }
    
    public void push(int val) {
        buffer.add(val);
    }
    
    public void pop() {
        buffer.remove();
    }
    
    public int top() {
        return buffer.getLast();
    }
    
    public int getMin() {
        return buffer.getMin();
    }
}

class Node {
    int v;
    Node prev;
    Node next;

    public Node(int v) {
        this.v = v;
    }
}

class DoubleList {
    Node head;
    Node tail;
    int min;

    public DoubleList() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        min = Integer.MAX_VALUE;
    }

    public void add(int v) {
        Node n = new Node(v);
        tail.prev.next = n;
        n.prev = tail.prev;
        n.next = tail;
        tail.prev = n;
        if (min > n.v) min = n.v;
    }

    public void remove() {
        if (tail.prev.equals(head)) return;
        Node r = tail.prev;
        r.prev.next = tail;
        tail.prev = r.prev;
        r.prev = null;
        r.next = null;
        if (r.v == min) {
            Node p = head.next;
            min = Integer.MAX_VALUE;
            while (p != tail) {
                if (p.v < min) {
                    min = p.v;
                }
            }
        }
    }

    public int getLast() {
        if (tail.prev.equals(head)) return 0;
        else {
            return tail.prev.v;
        }
    }

    public int getMin() {
        return min;
    }


}