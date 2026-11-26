class Solution {
    int[][] dp;
    long[][] leftMax, rightMax;
    long[] pre;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        pre = new long[n + 1];
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + stoneValue[i];

        dp = new int[n][n];
        leftMax = new long[n][n];
        rightMax = new long[n][n];

        for (int i = 0; i < n; i++) {
            leftMax[i][i] = stoneValue[i];
            rightMax[i][i] = stoneValue[i];
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                solve(i, j);
            }
        }

        return dp[0][n - 1];
    }

    void solve(int i, int j) {
        long total = sum(i, j);

        // find last split point where left half <= right half
        int lo = i, hi = j - 1, k = i - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (sum(i, mid) * 2 <= total) {
                k = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        long best = 0;

        // left side smaller -> keep left
        if (k >= i) best = Math.max(best, leftMax[i][k]);

        // right side smaller -> keep right
        if (k + 2 <= j) best = Math.max(best, rightMax[k + 2][j]);

        // equal split at k -> Alice picks
        if (k >= i && sum(i, k) * 2 == total) {
            long rest = total - sum(i, k);
            best = Math.max(best, rest + dp[k + 1][j]);
        }

        dp[i][j] = (int) best;

        long val = total + dp[i][j];
        leftMax[i][j] = j > i ? Math.max(leftMax[i][j - 1], val) : val;
        rightMax[i][j] = i < j ? Math.max(rightMax[i + 1][j], val) : val;
    }

    long sum(int a, int b) {
        return pre[b + 1] - pre[a];
    }
}