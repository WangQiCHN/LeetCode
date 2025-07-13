package demo;

class Demo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        // int[] nums = { 4,5,6,7,0,1,2 };
        int[] nums = { 1 };
        int target = 1;
        int answer = demo.searchInsert(nums, target);
        System.out.println(answer);
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return nums[left] > target ? left : left + 1;
    }
}
