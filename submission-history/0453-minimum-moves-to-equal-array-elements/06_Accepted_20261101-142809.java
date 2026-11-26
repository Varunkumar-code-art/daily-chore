class Solution {
    public int minMoves(int[] nums) {
        int low = nums[0];
        int total = 0;

        for (int x : nums) {
            total += x;
            if (x < low) low = x;
        }

        return total - nums.length * low;
    }
}