import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;

public class Demo {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // Convert wordList to a set for O(1) lookup
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();
        
        // If endWord is not in wordList, no solution exists
        if (!dict.contains(endWord)) {
            return result;
        }
        
        // Map to store the shortest distance from beginWord to each word
        Map<String, Integer> distance = new HashMap<>();
        // Map to store the neighbors (previous words) for each word in the path
        Map<String, List<String>> neighbors = new HashMap<>();
        // BFS queue
        Queue<String> queue = new LinkedList<>();
        
        // Initialize BFS
        distance.put(beginWord, 0);
        queue.offer(beginWord);
        boolean found = false;
        
        // BFS to build the shortest path graph
        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                int currDistance = distance.get(curr);
                
                // Get all possible next words by changing one character
                List<String> nextWords = getNextWords(curr, dict);
                for (String next : nextWords) {
                    // Initialize neighbors list if not present
                    neighbors.computeIfAbsent(next, k -> new ArrayList<>());
                    
                    // If this is the first time visiting the word or at the same distance
                    if (!distance.containsKey(next)) {
                        distance.put(next, currDistance + 1);
                        queue.offer(next);
                        neighbors.get(next).add(curr);
                        if (next.equals(endWord)) {
                            found = true;
                        }
                    } else if (distance.get(next) == currDistance + 1) {
                        neighbors.get(next).add(curr);
                    }
                }
            }
        }
        
        // If endWord was not reached, return empty result
        if (!distance.containsKey(endWord)) {
            return result;
        }
        
        // DFS to construct all shortest paths
        List<String> path = new ArrayList<>();
        path.add(endWord);
        dfs(endWord, beginWord, neighbors, path, result);
        
        return result;
    }
    
    // Helper function to get all valid next words by changing one character
    private List<String> getNextWords(String word, Set<String> dict) {
        List<String> nextWords = new ArrayList<>();
        char[] chars = word.toCharArray();
        
        for (int i = 0; i < chars.length; i++) {
            char original = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != original) {
                    chars[i] = c;
                    String newWord = new String(chars);
                    if (dict.contains(newWord)) {
                        nextWords.add(newWord);
                    }
                }
            }
            chars[i] = original; // Restore original character
        }
        return nextWords;
    }
    
    // DFS to build all shortest paths from endWord to beginWord
    private void dfs(String curr, String beginWord, Map<String, List<String>> neighbors, 
                    List<String> path, List<List<String>> result) {
        if (curr.equals(beginWord)) {
            // Reverse the path and add to result
            List<String> newPath = new ArrayList<>(path);
            Collections.reverse(newPath);
            result.add(newPath);
            return;
        }
        
        // Explore all previous neighbors
        for (String prev : neighbors.getOrDefault(curr, new ArrayList<>())) {
            path.add(prev);
            dfs(prev, beginWord, neighbors, path, result);
            path.remove(path.size() - 1); // Backtrack
        }
    }
}