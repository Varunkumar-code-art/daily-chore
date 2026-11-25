import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int[] sa = buildSuffixArray(nums);

        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        int[] lcp = new int[n];
        int h = 0;
        for (int i = 0; i < n; i++) {
            if (rank[i] > 0) {
                int j = sa[rank[i] - 1];
                while (i + h < n && j + h < n && nums[i + h] == nums[j + h]) {
                    h++;
                }
                lcp[rank[i]] = h;
                if (h > 0) h--;
            } else {
                h = 0;
            }
        }

        int best = n;
        for (int r = 0; r < n; r++) {
            int need = lcp[r];
            if (r + 1 < n) need = Math.max(need, lcp[r + 1]);
            need++;
            if (sa[r] + need <= n) {
                best = Math.min(best, need);
            }
        }
        return best;
    }

    private int[] buildSuffixArray(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        for (int v : nums) maxVal = Math.max(maxVal, v);

        int m = maxVal + 1;
        int[] sa = new int[n];
        int[] x = new int[n];
        int[] y = new int[n];
        int[] cnt = new int[Math.max(m, n) + 1];

        for (int i = 0; i < n; i++) {
            x[i] = nums[i];
            cnt[x[i]]++;
        }
        for (int i = 1; i < m; i++) cnt[i] += cnt[i - 1];
        for (int i = n - 1; i >= 0; i--) sa[--cnt[x[i]]] = i;

        for (int k = 1; k <= n; k <<= 1) {
            int p = 0;
            for (int i = n - k; i < n; i++) y[p++] = i;
            for (int i = 0; i < n; i++) {
                if (sa[i] >= k) y[p++] = sa[i] - k;
            }

            Arrays.fill(cnt, 0, m, 0);
            for (int i = 0; i < n; i++) cnt[x[y[i]]]++;
            for (int i = 1; i < m; i++) cnt[i] += cnt[i - 1];
            for (int i = n - 1; i >= 0; i--) sa[--cnt[x[y[i]]]] = y[i];

            int[] swap = x;
            x = y;
            y = swap;

            p = 0;
            x[sa[0]] = 0;
            for (int i = 1; i < n; i++) {
                int a = sa[i - 1];
                int b = sa[i];
                int nextA = a + k < n ? y[a + k] : -1;
                int nextB = b + k < n ? y[b + k] : -1;
                if (y[a] != y[b] || nextA != nextB) p++;
                x[b] = p;
            }
            if (p == n - 1) break;
            m = p + 1;
        }
        return sa;
    }
}