import java.util.*;

class Solution {
    private long[] bc, bs;
    private int m;

    private void upd(int p, int c, long v) {
        for (int i = p; i <= m; i += i & -i) { bc[i] += c; bs[i] += v; }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int[] vals = Arrays.stream(nums).distinct().sorted().toArray();
        m = vals.length;
        bc = new long[m + 1]; bs = new long[m + 1];
        int top = Integer.highestOneBit(m), target = (x + 1) / 2;
        long[] cost = new long[n - x + 1];
        long total = 0;

        for (int r = 0; r < n; r++) {
            upd(Arrays.binarySearch(vals, nums[r]) + 1, 1, nums[r]);
            total += nums[r];
            if (r >= x) {
                upd(Arrays.binarySearch(vals, nums[r - x]) + 1, -1, -nums[r - x]);
                total -= nums[r - x];
            }
            if (r >= x - 1) {
                int pos = 0, rem = target;
                for (int pw = top; pw > 0; pw >>= 1)
                    if (pos + pw <= m && bc[pos + pw] < rem) { pos += pw; rem -= bc[pos]; }
                long med = vals[pos];
                long cntLE = 0, sumLE = 0;
                for (int i = pos + 1; i > 0; i -= i & -i) { cntLE += bc[i]; sumLE += bs[i]; }
                cost[r - x + 1] = med * cntLE - sumLE + (total - sumLE) - med * (x - cntLE);
            }
        }

        // forward DP: cur[i] = min cost with j segments chosen within first i elements
        long INF = Long.MAX_VALUE / 4;
        long[] cur = new long[n + 1];            // j = 0 -> zeros
        for (int j = 1; j <= k; j++) {
            long[] nxt = new long[n + 1];
            Arrays.fill(nxt, INF);
            for (int i = 0; i <= n; i++) {
                if (i > 0) nxt[i] = Math.min(nxt[i], nxt[i - 1]);   // skip element i-1
                if (i + x <= n && cur[i] < INF)                      // take window [i, i+x)
                    nxt[i + x] = Math.min(nxt[i + x], cur[i] + cost[i]);
            }
            cur = nxt;
        }
        return cur[n];
    }
}