class Solution {
    public int climbStairs(int n, int[] costs) {
        long INF = Long.MAX_VALUE / 4;
        long[] dp = new long[n + 1];
        java.util.Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int k = 1; k <= 3 && i + k <= n; k++) {
                int j = i + k;
                dp[j] = Math.min(dp[j], dp[i] + costs[j - 1] + (long) k * k);
            }
        }
        return (int) dp[n];
    }
}