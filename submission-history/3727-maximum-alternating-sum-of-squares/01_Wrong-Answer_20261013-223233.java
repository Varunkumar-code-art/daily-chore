class Solution {
    public long maxAlternatingSum(int[] nums) {
        Arrays.sort(nums);
        long sum = 0;
        int n = nums.length;

        for (int i = 0; i < n / 2; i++) {
            sum -= (long) nums[i] * nums[i];
        }

        for (int i = n / 2; i < n; i++) {
            sum += (long) nums[i] * nums[i];
        }

        return sum;
    }
}