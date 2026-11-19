class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        int best = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;

                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) continue;
                    min = Math.min(min, nums[k]);
                    max = Math.max(max, nums[k]);
                }

                best = Math.min(best, max - min);
            }
        }

        return best;
    }
}