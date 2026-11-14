class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        int best = Integer.MAX_VALUE;

        for (int size = l; size <= r; size++) {
            for (int start = 0; start + size <= n; start++) {
                int total = prefix[start + size] - prefix[start];
                if (total > 0 && total < best) {
                    best = total;
                }
            }
        }

        return best == Integer.MAX_VALUE ? -1 : best;
    }
}