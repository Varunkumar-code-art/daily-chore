class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = -1, max = -1;
        boolean inc = true, dec = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) zero = i;
            if (nums[i] == n - 1) max = i;

            int next = nums[(i + 1) % n];

            if (next != (nums[i] + 1) % n) {
                inc = false;
            }

            if (next != (nums[i] - 1 + n) % n) {
                dec = false;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (inc) {
            ans = Math.min(ans, Math.min(zero, 3 * (n - zero)));
        }

        if (dec) {
            int a = Math.min(max, 3 * (n - max));
            int k = n - 1 - zero;
            int b = Math.min(k, 3 * (n - k));

            ans = Math.min(ans, Math.min(a, b) + 1);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}