class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        int best = Integer.MAX_VALUE;

        for (int end = l; end <= n; end++) {
            int lowest = Math.max(0, end - r);
            int highest = end - l;
            int cur = prefix[end];

            for (int start = lowest; start <= highest; start++) {
                int total = cur - prefix[start];
                if (total > 0 && total < best) {
                    best = total;
                    if (best == 1) return 1;
                }
            }
        }

        return best == Integer.MAX_VALUE ? -1 : best;
    }
}