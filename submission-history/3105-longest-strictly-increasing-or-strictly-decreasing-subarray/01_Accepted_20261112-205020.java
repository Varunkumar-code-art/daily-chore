class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int ans = 1;

        for (int i = 0; i < nums.length; i++) {
            int j = i;
            while (j + 1 < nums.length && nums[j] < nums[j + 1]) {
                j++;
            }
            ans = Math.max(ans, j - i + 1);

            j = i;
            while (j + 1 < nums.length && nums[j] > nums[j + 1]) {
                j++;
            }
            ans = Math.max(ans, j - i + 1);
        }

        return ans;
    }
}