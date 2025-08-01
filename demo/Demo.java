import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Demo {
    void main(String[] args) {
        Demo d = new Demo();
        char[][] board = {
            {'o', 'a', 'a', 'n'},
            {'e', 't', 'a', 'e'},
            {'i', 'h', 'k', 'r'},
            {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};
        List<String> result = d.findWords(board, words);
        System.out.println(result); // Output: 3
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26]; // For lowercase letters a-z
        String word = null; // Store the complete word at the end of a path
    }
    
    private char[][] board;
    private List<String> result;
    
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        this.result = new ArrayList<>();
        
        // Step 1: Build the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            insert(word, root);
        }
        
        // Step 2: Start DFS from each cell
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (root.children[board[i][j] - 'a'] != null) {
                    dfs(i, j, root);
                }
            }
        }
        
        return result;
    }
    
    // Insert a word into the Trie
    private void insert(String word, TrieNode root) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.word = word; // Mark the end of the word
    }
    
    // DFS to explore the board and match words in the Trie
    private void dfs(int row, int col, TrieNode node) {
        char c = board[row][col];
        
        // Base cases: invalid cell or no matching prefix
        if (c == '#') {
            return;
        }

        TrieNode curr = node.children[c - 'a'];
        if (curr == null) {
            return; // No matching prefix in the Trie
        }
        
        // If a word is found, add it to the result and clear it to avoid duplicates
        if (curr.word != null) {
            result.add(curr.word);
            curr.word = null;
        }
        
        // Mark the current cell as visited
        board[row][col] = '#';
        
        // Explore all four directions: up, right, down, left
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
                dfs(newRow, newCol, curr);
            }
        }
        
        // Restore the cell
        board[row][col] = c;
    }
}

// TrieNode class to represent each node in the Trie
    