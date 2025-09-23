package code;

import java.util.*;

class Router {
    private int memoryLimit;
    private Map<Long, Packet> packets = new HashMap<>();
    private Queue<Packet> buffer = new LinkedList<>();
    private Map<Integer, List<Integer>> destTimStamps = new HashMap<>();

    public static void main() {
        // [[3],[1,4,90],[2,5,90],[1,4,90],[3,5,95],[4,5,105],[],[5,2,110],[5,100,110]]
        Router obj = new Router(3);
        System.out.println(obj.addPacket(1, 4, 90));
        System.out.println(obj.addPacket(2, 5, 90));
        System.out.println(obj.addPacket(1, 4, 90));
        System.out.println(obj.addPacket(3, 5, 95));
        System.out.println(obj.addPacket(4, 5, 105));
        System.out.println(Arrays.toString(obj.forwardPacket()));
        System.out.println(obj.addPacket(5, 2, 110));
        System.out.println(obj.getCount(5, 100, 110));
    }

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        Packet p = new Packet(source, destination, timestamp);
        if (packets.containsKey(p.getKey())) {
            return false;
        } else {
            if (buffer.size() == memoryLimit) {
                Packet old = buffer.poll();
                packets.remove(old.getKey());
                destTimStamps.get(old.destination).remove(0);
            }
            buffer.offer(p);
            packets.put(p.getKey(), p);
            destTimStamps.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);
            return true;
        }
    }

    public int[] forwardPacket() {
        if (buffer.isEmpty()) {
            return new int[] {};
        } else {
            Packet old = buffer.poll();
            packets.remove(old.getKey());
            destTimStamps.get(old.destination).remove(0);
            return old.toArray();
        }
    }

    public int getCount(int destination, int startTime, int endTime) {
        List<Integer> timeStamps = destTimStamps.get(destination);
        int lower = getLower(timeStamps, startTime);
        int upper = getUpper(timeStamps, endTime);

        return upper - lower;
    }

    private int getLower(List<Integer> list, int startTime) {
        int l = 0, r = list.size() - 1;
        int m;
        while (l <= r) {
            m = (r + l) >> 1;
            if (list.get(m) >= startTime) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return r;
    }

    private int getUpper(List<Integer> list, int endTime) {
        int l = 0, r = list.size() - 1;
        int m;
        while (l <= r) {
            m = (l + r) / 2;
            if (list.get(m) > endTime)
                r = m - 1;
            else
                l = m + 1;
        }
        return r;
    }
}

class Packet {
    int source;
    int destination;
    int timestamp;

    public Packet(int source, int destination, int timestamp) {
        this.source = source;
        this.destination = destination;
        this.timestamp = timestamp;
    }

    public long getKey() {
        long key = 0;
        key += (long) source * 100000000000000L;
        key += (long) destination * 1000000000L;
        key += (long) timestamp;

        return key;
    }

    public int[] toArray() {
        return new int[] { source, destination, timestamp };
    }
}
