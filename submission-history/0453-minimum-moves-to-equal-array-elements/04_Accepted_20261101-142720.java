class Solution {
    public int minMoves(int[] nums) {
        long total = 0;
        int smallest = Integer.MAX_VALUE;

        for (int num : nums) {
            total += num;
            if (num < smallest) {
                smallest = num;
            }
        }

        return (int) (total - (long) nums.length * smallest);
    }
}