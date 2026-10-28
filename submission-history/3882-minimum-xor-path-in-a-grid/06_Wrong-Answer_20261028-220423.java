class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long[][] dp = new long[m][n];

        dp[0][0] = 1L << grid[0][0];

        for (int j = 1; j < n; j++) {
            dp[0][j] = shift(dp[0][j - 1], grid[0][j]);
        }

        for (int i = 1; i < m; i++) {
            dp[i][0] = shift(dp[i - 1][0], grid[i][0]);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = shift(dp[i - 1][j] | dp[i][j - 1], grid[i][j]);
            }
        }

        return Long.numberOfTrailingZeros(dp[m - 1][n - 1]);
    }

    private long shift(long mask, int value) {
        long result = 0;
        for (int x = 0; x < 64; x++) {
            if ((mask & (1L << x)) != 0) {
                result |= 1L << (x ^ value);
            }
        }
        return result;
    }
}