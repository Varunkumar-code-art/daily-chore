class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long total = 0;

        for (int start = 0; start < n; start++) {
            long used = 0;
            int highest = nums[start];

            for (int end = start; end < n; end++) {
                if (nums[end] > highest) {
                    highest = nums[end];
                } else {
                    used += highest - nums[end];
                }

                if (used > k) {
                    break;
                }
                total++;
            }
        }

        return total;
    }
}