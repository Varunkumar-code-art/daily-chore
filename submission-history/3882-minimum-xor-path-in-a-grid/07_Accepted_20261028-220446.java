class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][] dp = new long[m * n][16];
        dp[0][grid[0][0] >> 6] = 1L << (grid[0][0] & 63);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                int idx = i * n + j;
                int prevTop = idx - n;
                int prevLeft = idx - 1;
                int v = grid[i][j];

                for (int x = 0; x < 16; x++) {
                    long bits = 0;

                    if (i > 0) bits |= dp[prevTop][x];
                    if (j > 0) bits |= dp[prevLeft][x];

                    while (bits != 0) {
                        int b = Long.numberOfTrailingZeros(bits);
                        int xor = (x << 6) | b;
                        int next = xor ^ v;
                        dp[idx][next >> 6] |= 1L << (next & 63);
                        bits &= bits - 1;
                    }
                }
            }
        }

        for (int x = 0; x < 16; x++) {
            if (dp[m * n - 1][x] != 0) {
                return (x << 6) + Long.numberOfTrailingZeros(dp[m * n - 1][x]);
            }
        }

        return 0;
    }
}