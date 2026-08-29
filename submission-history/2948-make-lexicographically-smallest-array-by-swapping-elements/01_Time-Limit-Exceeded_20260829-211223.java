class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        boolean changed = true;

        while (changed) {
            changed = false;
            
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (Math.abs(nums[i] - nums[j]) <= limit && nums[j] < nums[i]) {
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                        changed = true;
                    }
                }
            }
        }
        
        return nums;
    }
}
