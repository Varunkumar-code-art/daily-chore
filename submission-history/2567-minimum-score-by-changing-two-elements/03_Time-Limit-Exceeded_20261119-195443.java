class Solution {
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int lo = 0;
                while (lo == i || lo == j) lo++;

                int hi = n - 1;
                while (hi == i || hi == j) hi--;

                best = Math.min(best, nums[hi] - nums[lo]);
            }
        }

        return best;
    }
}