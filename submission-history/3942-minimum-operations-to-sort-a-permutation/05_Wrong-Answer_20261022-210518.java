class Solution {
public int minOperations(int[] nums) {
int n = nums.length;
int ans = Integer.MAX_VALUE;
    for (int rev = 0; rev < 2; rev++) {
        for (int shift = 0; shift < n; shift++) {
            boolean valid = true;

            for (int i = 0; i < n; i++) {
                int idx = (i + shift) % n;

                if (rev == 0) {
                    if (nums[idx] != i) {
                        valid = false;
                        break;
                    }
                } else {
                    if (nums[n - 1 - idx] != i) {
                        valid = false;
                        break;
                    }
                }
            }

            if (valid) {
                ans = Math.min(ans, shift + rev);
            }
        }
    }

    return ans == Integer.MAX_VALUE ? -1 : ans;
}
}
