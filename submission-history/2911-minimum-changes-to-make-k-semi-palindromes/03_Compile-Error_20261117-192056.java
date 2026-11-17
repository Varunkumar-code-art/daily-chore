class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        List<Integer>[] divs = new List[n + 1];
        for (int len = 1; len <= n; len++) {
            divs[len] = new ArrayList<>();
            for (int d = 1; d < len; d++) {
                if (len % d == 0) {
                    divs[len].add(d);
                }
            }
        }

        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int best = Integer.MAX_VALUE;

                for (int d : divs[len]) {
                    int changes = 0;
                    for (int g = 0; g < d; g++) {
                        int l = i + g;
                        int r = i + g + (len / d - 1) * d;
                        while (l < r) {
                            if (s.charAt(l) != s.charAt(r)) {
                                changes++;
                            }
                            l += d;
                            r -= d;
                        }
                    }
                    best = Math.min(best, changes);
                }

                cost[i][j] = best;
            }
        }

        int INF = 1000000;
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int j = 1; j <= k; j++) {
            for (int i = 2 * j; i <= n; i++) {
                for (int p = 2 * (j - 1); p <= i - 2; p++) {
                    if (dp[j - 1][p] != INF) {
                        dp[j][i] = Math.min(dp[j][i], dp[j - 1][p] + cost[p][i - 1]);
                    }
                }
            }
        }
    }
}