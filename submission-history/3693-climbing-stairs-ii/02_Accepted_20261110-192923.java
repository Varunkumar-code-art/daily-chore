class Solution {
    public int climbStairs(int n, int[] costs) {
        long INF = Long.MAX_VALUE / 4;
        long[] dp = new long[n + 1];
        java.util.Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int j = 1; j <= n; j++) {
            for (int k = 1; k <= 3; k++) {
                int i = j - k;
                if (i < 0) break;
                dp[j] = Math.min(dp[j], dp[i] + costs[j - 1] + (long) k * k);
            }
        }
        return (int) dp[n];
    }
}