class Solution {
    static final long MOD = 1000000007L;
    long[] sum, sq, lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int l = last[nums[i]];
            update(1, 0, n - 1, l, i);
            ans = (ans + sq[1]) % MOD;
            last[nums[i]] = i + 1;
        }

        return (int) ans;
    }

    void update(int node, int start, int end, int l, int r) {
        if (l <= start && end <= r) {
            apply(node, end - start + 1);
            return;
        }

        push(node, start, end);

        int mid = (start + end) >>> 1;

        if (l <= mid)
            update(node * 2, start, mid, l, r);

        if (r > mid)
            update(node * 2 + 1, mid + 1, end, l, r);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    void apply(int node, int len) {
        long v = lazy[node];
        sq[node] = (sq[node] + 2 * v % MOD * sum[node]
                    + v * v % MOD * len) % MOD;
        sum[node] = (sum[node] + v * len) % MOD;

        v = 1;
        sq[node] = (sq[node] + 2 * sum[node] % MOD * v
                    - (long) len + MOD) % MOD;
        sum[node] = (sum[node] + len) % MOD;
        lazy[node] = (lazy[node] + 1) % MOD;
    }

    void push(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) return;

        int mid = (start + end) >>> 1;
        applyLazy(node * 2, mid - start + 1, lazy[node]);
        applyLazy(node * 2 + 1, end - mid, lazy[node]);
        lazy[node] = 0;
    }

    void applyLazy(int node, int len, long v) {
        sq[node] = (sq[node] + 2 * v % MOD * sum[node]
                    + v * v % MOD * len) % MOD;
        sum[node] = (sum[node] + v * len) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    }
}