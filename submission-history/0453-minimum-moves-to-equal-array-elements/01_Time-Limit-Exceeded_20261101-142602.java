class Solution {
    public int minMoves(int[] nums) {
        int moves = 0;

        while (true) {
            int maxIdx = 0;
            int minVal = nums[0];
            boolean allSame = true;

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[0]) {
                    allSame = false;
                }
                if (nums[i] > nums[maxIdx]) {
                    maxIdx = i;
                }
                if (nums[i] < minVal) {
                    minVal = nums[i];
                }
            }

            if (allSame) {
                break;
            }

            for (int i = 0; i < nums.length; i++) {
                if (i != maxIdx) {
                    nums[i]++;
                }
            }

            moves++;
        }

        return moves;
    }
}