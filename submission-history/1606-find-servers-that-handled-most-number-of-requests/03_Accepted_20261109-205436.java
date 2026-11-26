class Solution {
    private long[] tree;
    private int k;

    private void update(int node, int l, int r, int idx, long val) {
        if (l == r) { tree[node] = val; return; }
        int mid = (l + r) / 2;
        if (idx <= mid) update(2 * node, l, mid, idx, val);
        else update(2 * node + 1, mid + 1, r, idx, val);
        tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]);
    }

    // first index >= from with endTime <= t, or -1
    private int query(int node, int l, int r, int from, long t) {
        if (r < from || tree[node] > t) return -1;
        if (l == r) return l;
        int mid = (l + r) / 2;
        int res = query(2 * node, l, mid, from, t);
        if (res != -1) return res;
        return query(2 * node + 1, mid + 1, r, from, t);
    }

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        this.k = k;
        tree = new long[4 * k];
        int[] count = new int[k];

        for (int i = 0; i < arrival.length; i++) {
            int s = query(1, 0, k - 1, i % k, arrival[i]);
            if (s == -1) s = query(1, 0, k - 1, 0, arrival[i]);
            if (s == -1) continue;

            count[s]++;
            update(1, 0, k - 1, s, (long) arrival[i] + load[i]);
        }

        int max = Arrays.stream(count).max().getAsInt();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) if (count[i] == max) res.add(i);
        return res;
    }
}