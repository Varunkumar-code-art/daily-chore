class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int low = nums[0];
        int total = nums[0];

        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            total += cur;
            if (cur < low) {
                low = cur;
            }
        }

        return total - n * low;
    }
}