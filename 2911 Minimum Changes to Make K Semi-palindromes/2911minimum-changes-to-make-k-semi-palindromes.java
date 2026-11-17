class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] c = s.toCharArray();
        int INF = 1000000;

        int[][] cost = new int[n][n];
        for (int[] row : cost) {
            Arrays.fill(row, INF);
        }

        int[][] chain = new int[n][n];
        for (int d = 1; d <= n / 2; d++) {
            for (int gap = d; gap < n; gap += d) {
                for (int a = 0; a + gap < n; a++) {
                    int b = a + gap;
                    int val = c[a] != c[b] ? 1 : 0;
                    if (gap > 2 * d) {
                        val += chain[a + d][b - d];
                    }
                    chain[a][b] = val;
                }
            }

            for (int len = 2 * d; len <= n; len += d) {
                int gap = len - d;
                for (int i = 0; i + len <= n; i++) {
                    int j = i + len - 1;
                    int total = 0;
                    for (int g = 0; g < d; g++) {
                        total += chain[i + g][i + g + gap];
                    }
                    if (total < cost[i][j]) {
                        cost[i][j] = total;
                    }
                }
            }
        }

        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int j = 1; j <= k; j++) {
            int hi = n - 2 * (k - j);
            for (int i = 2 * j; i <= hi; i++) {
                int best = INF;
                for (int p = 2 * (j - 1); p <= i - 2; p++) {
                    int prev = dp[j - 1][p];
                    int cur = cost[p][i - 1];
                    if (prev != INF && cur != INF && prev + cur < best) {
                        best = prev + cur;
                    }
                }
                dp[j][i] = best;
            }
        }

        return dp[k][n];
    }
}