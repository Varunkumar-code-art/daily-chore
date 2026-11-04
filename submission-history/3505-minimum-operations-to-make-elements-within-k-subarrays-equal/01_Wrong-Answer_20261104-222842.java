import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];
        Comparator<Integer> cmp = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : Integer.compare(a, b);
        TreeSet<Integer> low = new TreeSet<>(cmp), high = new TreeSet<>(cmp);
        long sumLow = 0, sumHigh = 0;
        int target = (x + 1) / 2;

        for (int r = 0; r < n; r++) {
            if (r >= x) {
                int l = r - x;
                if (low.remove(l)) sumLow -= nums[l];
                else { high.remove(l); sumHigh -= nums[l]; }
            }
            if (low.isEmpty() || cmp.compare(r, low.last()) <= 0) { low.add(r); sumLow += nums[r]; }
            else { high.add(r); sumHigh += nums[r]; }

            while (low.size() > target) { int t = low.pollLast(); sumLow -= nums[t]; high.add(t); sumHigh += nums[t]; }
            while (low.size() < target && !high.isEmpty()) { int t = high.pollFirst(); sumHigh -= nums[t]; low.add(t); sumLow += nums[t]; }

            if (r >= x - 1) {
                long m = nums[low.last()];
                cost[r - x + 1] = m * low.size() - sumLow + sumHigh - m * high.size();
            }
        }

        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[k + 1][n + 1];
        for (int j = 1; j <= k; j++) Arrays.fill(dp[j], INF);
        for (int j = 1; j <= k; j++)
            for (int i = 1; i <= n; i++) {
                dp[j][i] = dp[j][i - 1];
                if (i >= x && dp[j - 1][i - x] < INF)
                    dp[j][i] = Math.min(dp[j][i], dp[j - 1][i - x] + cost[i - x]);
            }
        return dp[k][n];
    }
}