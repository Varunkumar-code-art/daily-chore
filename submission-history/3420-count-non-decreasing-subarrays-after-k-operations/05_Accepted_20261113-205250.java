class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] dq = new int[n];
        int head = n;
        int tail = n - 1;
        long cost = 0;
        long answer = 0;
        int right = n - 1;

        for (int left = n - 1; left >= 0; left--) {
            while (head <= tail && nums[dq[head]] <= nums[left]) {
                int idx = dq[head++];
                int nextStart = head <= tail ? dq[head] : right + 1;
                cost += (long) (nextStart - idx) * (nums[left] - nums[idx]);
            }
            dq[--head] = left;

            while (cost > k) {
                int last = dq[tail];
                cost -= nums[last] - nums[right];
                if (last == right) {
                    tail--;
                }
                right--;
            }

            answer += right - left + 1;
        }

        return answer;
    }
}