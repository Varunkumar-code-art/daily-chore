class Solution {
    public int minMoves(int[] nums) {
        int smallest = nums[0];
        for (int num : nums) {
            if (num < smallest) {
                smallest = num;
            }
        }

        int moves = 0;
        for (int num : nums) {
            moves += num - smallest;
        }

        return moves;
    }
}