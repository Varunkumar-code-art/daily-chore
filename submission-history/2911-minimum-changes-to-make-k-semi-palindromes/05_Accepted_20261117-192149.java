class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 1000000;

        int[][] cost = new int[n][n];
        for (int[] row : cost) {
            Arrays.fill(row, INF);
        }

        int[][] chain = new int[n][n];
        for (int d = 1; d <= n / 2; d++) {
            for (int gap = 1; gap < n; gap++) {
                for (int a = 0; a + gap < n; a++) {
                    int b = a + gap;
                    int val = s.charAt(a) != s.charAt(b) ? 1 : 0;
                    if (a + d < b - d) {
                        val += chain[a + d][b - d];
                    }
                    chain[a][b] = val;
                }
            }

            for (int len = 2 * d; len <= n; len += d) {
                for (int i = 0; i + len <= n; i++) {
                    int j = i + len - 1;
                    int total = 0;
                    for (int g = 0; g < d; g++) {
                        total += chain[i + g][j - d + 1 + g];
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
            for (int i = 2 * j; i <= n; i++) {
                for (int p = 2 * (j - 1); p <= i - 2; p++) {
                    if (dp[j - 1][p] != INF && cost[p][i - 1] != INF) {
                        dp[j][i] = Math.min(dp[j][i], dp[j - 1][p] + cost[p][i - 1]);
                    }
                }
            }
        }

        return dp[k][n];
    }
}