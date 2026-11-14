class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] p = new int[n + 1];
        for (int i = 0; i < n; i++) {
            p[i + 1] = p[i] + nums.get(i);
        }

        int best = Integer.MAX_VALUE;

        for (int end = l; end <= n; end++) {
            int cur = p[end];
            int from = end - r < 0 ? 0 : end - r;
            for (int start = from; start <= end - l; start++) {
                int d = cur - p[start];
                if (d > 0 && d < best) {
                    if (d == 1) return 1;
                    best = d;
                }
            }
        }

        return best == Integer.MAX_VALUE ? -1 : best;
    }
}