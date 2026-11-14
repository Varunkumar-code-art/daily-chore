class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        TreeMap<Integer, Integer> window = new TreeMap<>();
        int best = Integer.MAX_VALUE;

        for (int end = l; end <= n; end++) {
            window.merge(prefix[end - l], 1, Integer::sum);

            int expired = end - r - 1;
            if (expired >= 0) {
                int key = prefix[expired];
                if (window.get(key) == 1) {
                    window.remove(key);
                } else {
                    window.merge(key, -1, Integer::sum);
                }
            }

            Integer smaller = window.lowerKey(prefix[end]);
            if (smaller != null) {
                best = Math.min(best, prefix[end] - smaller);
            }
        }

        return best == Integer.MAX_VALUE ? -1 : best;
    }
}