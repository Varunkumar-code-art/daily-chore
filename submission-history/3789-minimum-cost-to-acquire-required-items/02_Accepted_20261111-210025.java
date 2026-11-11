class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long ans = Long.MAX_VALUE;
        int[] ks = {0, Math.min(need1, need2), Math.max(need1, need2)};

        for (int k : ks) {
            long cur = (long) k * costBoth
                     + (long) Math.max(0, need1 - k) * cost1
                     + (long) Math.max(0, need2 - k) * cost2;
            ans = Math.min(ans, cur);
        }

        return ans;
    }
}