class Solution {
    public int climbStairs(int n, int[] costs) {
        long INF = Long.MAX_VALUE / 4;
        long p1 = 0;     // dp[j-1]
        long p2 = INF;   // dp[j-2]
        long p3 = INF;   // dp[j-3]

        for (int j = 1; j <= n; j++) {
            long best = Math.min(p1 + 1, Math.min(p2 + 4, p3 + 9));
            long cur = best + costs[j - 1];
            p3 = p2;
            p2 = p1;
            p1 = cur;
        }
        return (int) p1;
    }
}