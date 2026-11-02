class Solution {
    public int minMoves2(int[] nums) {
        long best = Long.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            long moves = 0;
            for (int j = 0; j < nums.length; j++) {
                moves += Math.abs(nums[j] - nums[i]);
            }
            if (moves < best) {
                best = moves;
            }
        }

        return (int) best;
    }
}