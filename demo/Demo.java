import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Demo {
    void main() {
        Demo d = new Demo();
        int[][] buildings = {
            {2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}
        };
        List<List<Integer>> result = d.getSkyline(buildings);
        for (List<Integer> r : result) {
            System.out.print(r.get(0));
            System.out.print(",");
            System.out.print(r.get(1));
            System.out.println();
        }
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        // Create a list to store all critical points (start and end of buildings)
        List<int[]> points = new ArrayList<>();
        for (int[] building : buildings) {
            // Add start point with negative height to distinguish it
            points.add(new int[]{building[0], -building[2]});
            // Add end point with positive height
            points.add(new int[]{building[1], building[2]});
        }
        
        // Sort points by x-coordinate; if x-coordinates are equal, sort by height
        // For same x, start points with higher height come first (negative height),
        // and end points with lower height come first
        Collections.sort(points, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        
        // Max heap to store active building heights (store negative to make it a max heap)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        maxHeap.offer(0); // Ground level
        List<List<Integer>> result = new ArrayList<>();
        int prevHeight = 0;
        
        // Process each point
        for (int[] point : points) {
            int x = point[0];
            int height = point[1];
            
            if (height < 0) {
                // Start of a building, add its height to the heap
                maxHeap.offer(-height);
            } else {
                // End of a building, remove its height from the heap
                maxHeap.remove(height);
            }
            
            // Get the current maximum height
            int currHeight = maxHeap.peek();
            
            // If the height changes, add a new key point to the result
            if (currHeight != prevHeight) {
                result.add(Arrays.asList(x, currHeight));
                prevHeight = currHeight;
            }
        }
        
        return result;
    }
}
