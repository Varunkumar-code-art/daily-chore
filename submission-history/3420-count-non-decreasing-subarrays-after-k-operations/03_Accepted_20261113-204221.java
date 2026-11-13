class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> chain = new ArrayDeque<>();
        long cost = 0;
        long answer = 0;
        int right = n - 1;

        for (int left = n - 1; left >= 0; left--) {
            while (!chain.isEmpty() && nums[chain.peekFirst()] <= nums[left]) {
                int idx = chain.pollFirst();
                int nextStart = chain.isEmpty() ? right + 1 : chain.peekFirst();
                cost += (long) (nextStart - idx) * (nums[left] - nums[idx]);
            }
            chain.offerFirst(left);

            while (cost > k) {
                int last = chain.peekLast();
                cost -= nums[last] - nums[right];
                if (last == right) {
                    chain.pollLast();
                }
                right--;
            }

            answer += right - left + 1;
        }

        return answer;
    }
}