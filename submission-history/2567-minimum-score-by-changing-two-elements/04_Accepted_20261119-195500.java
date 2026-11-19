class Solution {
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = Integer.MAX_VALUE;

        for (int left = 0; left <= 2; left++) {
            int right = n - 1 - (2 - left);
            best = Math.min(best, nums[right] - nums[left]);
        }

        return best;
    }
}