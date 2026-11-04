import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int[] vals = Arrays.stream(nums).distinct().sorted().toArray();
        int m = vals.length;
        long[] bc = new long[m + 1], bs = new long[m + 1];
        int top = Integer.highestOneBit(m);
        int target = (x + 1) / 2;
        long[] cost = new long[n - x + 1];
        long total = 0;

        for (int r = 0; r < n; r++) {
            int p = Arrays.binarySearch(vals, nums[r]) + 1;
            for (int i = p; i <= m; i += i & -i) { bc[i]++; bs[i] += nums[r]; }
            total += nums[r];
            if (r >= x) {
                int q = Arrays.binarySearch(vals, nums[r - x]) + 1;
                for (int i = q; i <= m; i += i & -i) { bc[i]--; bs[i] -= nums[r - x]; }
                total -= nums[r - x];
            }
            if (r >= x - 1) {
                // find target-th smallest by binary descent
                int pos = 0, rem = target;
                for (int pw = top; pw > 0; pw >>= 1)
                    if (pos + pw <= m && bc[pos + pw] < rem) { pos += pw; rem -= bc[pos]; }
                int rank = pos + 1;           // 1-indexed rank of median value
                long med = vals[pos];
                long cntLE = 0, sumLE = 0;
                for (int i = rank; i > 0; i -= i & -i) { cntLE += bc[i]; sumLE += bs[i]; }
                cost[r - x + 1] = med * cntLE - sumLE + (total - sumLE) - med * (x - cntLE);
            }
        }

        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[k + 1][n + 1];
        for (int j = 1; j <= k; j++) Arrays.fill(dp[j], INF);
        for (int j = 1; j <= k; j++)
            for (int i = x; i <= n; i++) {
                dp[j][i] = dp[j][i - 1];
                if (dp[j - 1][i - x] < INF)
                    dp[j][i] = Math.min(dp[j][i], dp[j - 1][i - x] + cost[i - x]);
            }
        return dp[k][n];
    }
}