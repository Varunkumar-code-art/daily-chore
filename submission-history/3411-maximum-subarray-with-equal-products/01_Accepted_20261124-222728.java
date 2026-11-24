class Solution {
    private int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }

    public int maxLength(int[] nums) {
        int left = 0, ans = 2;
        for (int right = 0; right < nums.length; right++) {
            for (int k = left; k < right; k++) {
                if (gcd(nums[k], nums[right]) > 1) left = k + 1;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}