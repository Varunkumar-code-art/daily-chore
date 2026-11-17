class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = getCost(s.substring(i, j + 1));
            }
        }

        int INF = 1000000;
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = INF;
            }
        }
        dp[0][0] = 0;

        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                for (int p = 0; p < i; p++) {
                    if (i - p >= 2 && dp[p][j - 1] != INF) {
                        dp[i][j] = Math.min(dp[i][j], dp[p][j - 1] + cost[p][i - 1]);
                    }
                }
            }
        }

        return dp[n][k];
    }

    private int getCost(String t) {
        int len = t.length();
        int best = Integer.MAX_VALUE;

        for (int d = 1; d < len; d++) {
            if (len % d != 0) {
                continue;
            }

            int changes = 0;
            for (int start = 0; start < d; start++) {
                int left = start;
                int right = start + (len / d - 1) * d;

                while (left < right) {
                    if (t.charAt(left) != t.charAt(right)) {
                        changes++;
                    }
                    left += d;
                    right -= d;
                }
            }

            best = Math.min(best, changes);
        }

        return best;
    }
}