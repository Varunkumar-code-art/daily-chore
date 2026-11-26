class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int best = -1;
        for (int candidate : nums) {
            if (candidate <= best) continue; 

            int windowsContainingIt = 0;

            for (int start = 0; start + k <= n; start++) {
                boolean found = false;
                for (int j = start; j < start + k; j++) {
                    if (nums[j] == candidate) {
                        found = true;
                        break;
                    }
                }
                if (found) windowsContainingIt++;
                if (windowsContainingIt > 1) break; 
            }

            if (windowsContainingIt == 1) {
                best = candidate;
            }
        }

        return best;
    }
}