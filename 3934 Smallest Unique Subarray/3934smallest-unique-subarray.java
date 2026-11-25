class Solution {
    private int[] len, link, cnt, head, edgeTo, edgeNext, edgeChar, slot;
    private long[] keys;
    private int edgeCount;
    private static final int MASK = (1 << 20) - 1;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int[] freq = new int[100002];
        for (int v : nums) freq[v]++;
        for (int v : nums) {
            if (freq[v] == 1) return 1;
        }

        int maxStates = 2 * n + 2;
        int maxEdges = 3 * n + 8;
        len = new int[maxStates];
        link = new int[maxStates];
        cnt = new int[maxStates];
        head = new int[maxStates];
        edgeTo = new int[maxEdges];
        edgeNext = new int[maxEdges];
        edgeChar = new int[maxEdges];
        slot = new int[1 << 20];
        keys = new long[1 << 20];
        java.util.Arrays.fill(head, -1);
        java.util.Arrays.fill(keys, -1L);

        int size = 1;
        int last = 0;
        link[0] = -1;

        for (int c : nums) {
            int cur = size++;
            len[cur] = len[last] + 1;
            cnt[cur] = 1;
            int p = last;
            while (p != -1 && find(p, c) == -1) {
                addEdge(p, c, cur);
                p = link[p];
            }
            if (p == -1) {
                link[cur] = 0;
            } else {
                int q = edgeTo[find(p, c)];
                if (len[p] + 1 == len[q]) {
                    link[cur] = q;
                } else {
                    int clone = size++;
                    len[clone] = len[p] + 1;
                    link[clone] = link[q];
                    for (int e = head[q]; e != -1; e = edgeNext[e]) {
                        addEdge(clone, edgeChar[e], edgeTo[e]);
                    }
                    while (p != -1) {
                        int e = find(p, c);
                        if (e == -1 || edgeTo[e] != q) break;
                        edgeTo[e] = clone;
                        p = link[p];
                    }
                    link[q] = clone;
                    link[cur] = clone;
                }
            }
            last = cur;
        }

        int[] bucket = new int[n + 2];
        for (int v = 0; v < size; v++) bucket[len[v]]++;
        for (int i = 1; i <= n; i++) bucket[i] += bucket[i - 1];
        int[] order = new int[size];
        for (int v = size - 1; v >= 0; v--) order[--bucket[len[v]]] = v;

        for (int i = size - 1; i > 0; i--) {
            int v = order[i];
            cnt[link[v]] += cnt[v];
        }

        int best = n;
        for (int v = 1; v < size; v++) {
            if (cnt[v] == 1) {
                best = Math.min(best, len[link[v]] + 1);
            }
        }
        return best;
    }

    private int find(int state, int c) {
        long key = ((long) state << 17) | c;
        int h = (int) ((key * 0x9E3779B97F4A7C15L) >>> 44);
        while (keys[h] != -1L) {
            if (keys[h] == key) return slot[h];
            h = (h + 1) & MASK;
        }
        return -1;
    }

    private void addEdge(int state, int c, int target) {
        int e = edgeCount++;
        edgeTo[e] = target;
        edgeChar[e] = c;
        edgeNext[e] = head[state];
        head[state] = e;
        long key = ((long) state << 17) | c;
        int h = (int) ((key * 0x9E3779B97F4A7C15L) >>> 44);
        while (keys[h] != -1L) {
            h = (h + 1) & MASK;
        }
        keys[h] = key;
        slot[h] = e;
    }
}