class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int best = Integer.MAX_VALUE;

        for (int start = 0; start < n; start++) {
            for (int end = start; end < n; end++) {
                int size = end - start + 1;
                if (size < l || size > r) continue;

                int total = 0;
                for (int k = start; k <= end; k++) {
                    total += nums.get(k);
                }

                if (total > 0 && total < best) {
                    best = total;
                }
            }
        }

        return best == Integer.MAX_VALUE ? -1 : best;
    }
}