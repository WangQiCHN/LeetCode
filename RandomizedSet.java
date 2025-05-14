import java.util.ArrayList;
import java.util.HashMap;

public class RandomizedSet {
    private HashMap<Integer, Integer> valToIndex = new HashMap<>();
    private ArrayList<Integer> nums = new ArrayList<>();

    public static void main() {
        RandomizedSet rs = new RandomizedSet();
        rs.insert(0);
        rs.insert(1);
        rs.remove(0);
        rs.insert(2);
        rs.remove(1);
        int result = rs.getRandom();
        System.out.println(result);
    }

    public RandomizedSet() {
        
    }
    
    public boolean insert(int val) {
        if (valToIndex.containsKey(val)) {
            return false;
        }

        nums.add(val);
        valToIndex.put(val, nums.size() - 1);

        return true;
    }
    
    public boolean remove(int val) {
        if (valToIndex.containsKey(val)) {
            int index = valToIndex.get(val);
            int removed = nums.get(index);
            valToIndex.remove(removed);
            if (index != nums.size() - 1) {
                int updated = nums.get(nums.size() - 1);
                nums.set(index, updated);
                valToIndex.put(updated, index);
            }
            nums.remove(nums.size() - 1);
            return true;
        }

        return false;
    }
    
    public int getRandom() {
        return nums.get((int)(Math.random() * nums.size()));
    }
}
