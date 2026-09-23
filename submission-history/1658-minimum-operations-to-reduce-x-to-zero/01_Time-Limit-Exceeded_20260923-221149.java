class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        for (int left = 0; left <= n; left++) {

            int sumLeft = 0;

            for (int i = 0; i < left; i++) {
                sumLeft += nums[i];
            }

            if (sumLeft > x) {
                break;
            }

            for (int right = 0; right <= n - left; right++) {

                int sumRight = 0;

                for (int j = n - right; j < n; j++) {
                    sumRight += nums[j];
                }

                if (sumLeft + sumRight == x) {
                    ans = Math.min(ans, left + right);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}