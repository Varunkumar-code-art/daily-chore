class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                boolean unique = true;
                for (int j = 0; j + len <= n; j++) {
                    if (i == j) continue;
                    boolean same = true;
                    for (int k = 0; k < len; k++) {
                        if (nums[i + k] != nums[j + k]) {
                            same = false;
                            break;
                        }
                    }
                    if (same) {
                        unique = false;
                        break;
                    }
                }
                if (unique) return len;
            }
        }
        return n;
    }
}