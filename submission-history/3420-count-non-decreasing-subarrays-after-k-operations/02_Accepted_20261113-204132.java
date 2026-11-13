class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        Deque<Integer> stack = new ArrayDeque<>();
        long cost = 0;
        long answer = 0;
        int right = n - 1;

        for (int left = n - 1; left >= 0; left--) {
            while (!stack.isEmpty() && nums[stack.peekFirst()] <= nums[left]) {
                int idx = stack.pollFirst();
                int end = stack.isEmpty() ? right : stack.peekFirst() - 1;
                cost -= segmentCost(nums, prefix, idx, end);
            }

            int end = stack.isEmpty() ? right : stack.peekFirst() - 1;
            cost += segmentCost(nums, prefix, left, end);
            stack.offerFirst(left);

            while (cost > k) {
                int last = stack.peekLast();
                cost -= nums[last] - nums[right];
                if (last == right) {
                    stack.pollLast();
                }
                right--;
            }

            answer += right - left + 1;
        }

        return answer;
    }

    private long segmentCost(int[] nums, long[] prefix, int from, int to) {
        long length = to - from + 1;
        return (long) nums[from] * length - (prefix[to + 1] - prefix[from]);
    }
}