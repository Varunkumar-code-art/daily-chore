class Solution {
    public int sumCounts(int[] nums) {
        int n = nums.length;
        long mod = 1000000007L;
        long[] sum = new long[4 * n];
        long[] square = new long[4 * n];
        long[] lazy = new long[4 * n];
        int[] last = new int[100001];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int left = last[nums[i]];
            update(1, 0, n - 1, left, i, 1, sum, square, lazy, mod);
            ans = (ans + square[1]) % mod;
            last[nums[i]] = i + 1;
        }

        return (int) ans;
    }

    void update(int node, int start, int end, int l, int r, int val,
                long[] sum, long[] square, long[] lazy, long mod) {
        if (l > end || r < start) return;

        if (l <= start && end <= r) {
            square[node] = (square[node] + 2 * sum[node] + (end - start + 1)) % mod;
            sum[node] = (sum[node] + end - start + 1) % mod;
            lazy[node]++;
            return;
        }

        push(node, start, end, sum, square, lazy, mod);

        int mid = (start + end) / 2;
        update(node * 2, start, mid, l, r, val, sum, square, lazy, mod);
        update(node * 2 + 1, mid + 1, end, l, r, val, sum, square, lazy, mod);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % mod;
        square[node] = (square[node * 2] + square[node * 2 + 1]) % mod;
    }

    void push(int node, int start, int end, long[] sum, long[] square,
              long[] lazy, long mod) {
        if (lazy[node] == 0 || start == end) return;

        int mid = (start + end) / 2;
        apply(node * 2, start, mid, lazy[node], sum, square, lazy, mod);
        apply(node * 2 + 1, mid + 1, end, lazy[node], sum, square, lazy, mod);
        lazy[node] = 0;
    }

    void apply(int node, int start, int end, long val, long[] sum,
               long[] square, long[] lazy, long mod) {
        square[node] = (square[node] + 2 * val * sum[node]
                + val * val * (end - start + 1)) % mod;
        sum[node] = (sum[node] + val * (end - start + 1)) % mod;
        lazy[node] = (lazy[node] + val) % mod;
    }
}