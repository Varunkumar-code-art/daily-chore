class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long ans = Long.MAX_VALUE;
        int limit = Math.max(need1, need2);

        for (int i = 0; i <= limit; i++) {
            long left1 = Math.max(0, need1 - i);
            long left2 = Math.max(0, need2 - i);

            long cur = (long) i * costBoth + left1 * cost1 + left2 * cost2;
            ans = Math.min(ans, cur);
        }

        return ans;
    }
}