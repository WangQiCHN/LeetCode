package code;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int c = 4;
        int[][] connections = {{4,3},{3,1},{4,2},{3,2},{4,1}};
        int[][] queries = {{2,3},{1,2},{2,4},{1,1},{2,2},{1,2},{1,2},{2,2},{1,3},{2,3},{2,4},{2,3},{2,4},{1,2},{1,1}};
        int[] result = sol.processQueries(c, connections, queries);
        System.out.println(result);
    }
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        Station[] map = new Station[c];
        for (int i = 0; i < c; i++) {
            map[i] = new Station();
        }

        for (int[] link : connections) {
            int u = link[0];
            int v = link[1];
            map[u - 1].linkedStations.add(v - 1);
            map[v - 1].linkedStations.add(u - 1);
        }

        for (int i = 0; i < c; i++) {
            List<Integer> stations = map[i].linkedStations;
            Collections.sort(stations);
        }

        List<Integer> temp = new ArrayList<>();
        for (int[] q : queries) {
            int operate = q[0];
            int id = q[1];
            if (operate == 2) {
                map[id - 1].isOnline = false;
            } else {
                int r = checkStation(map, id - 1);
                temp.add(r);
            }
        }

        int[] result = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            result[i] = temp.get(i);
        }

        return result;
    }

    private int checkStation(Station[] map, int id) {
        if (map[id].isOnline) return id + 1;
        else {
            List<Integer> stations = map[id].linkedStations;
            for (int sId : stations) {
                if (map[sId].isOnline) return sId + 1;
            }
            return -1;
        }
    }
}

class Station {
    boolean isOnline;
    List<Integer> linkedStations;

    public Station() {
        this.isOnline = true;
        this.linkedStations = new ArrayList<>();
    }
}