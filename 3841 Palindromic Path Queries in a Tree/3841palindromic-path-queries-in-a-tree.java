import java.util.*;

class Solution {
    int[] bit;
    int n;

    void add(int i, int v) {
        for (; i <= n; i += i & -i) bit[i] ^= v;
    }

    int get(int i) {
        int r = 0;
        for (; i > 0; i -= i & -i) r ^= bit[i];
        return r;
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        int[] head = new int[n];
        int[] nxt = new int[2 * (n - 1) + 1];
        int[] to = new int[2 * (n - 1) + 1];
        Arrays.fill(head, -1);
        int cnt = 0;
        for (int[] e : edges) {
            to[cnt] = e[1]; nxt[cnt] = head[e[0]]; head[e[0]] = cnt++;
            to[cnt] = e[0]; nxt[cnt] = head[e[1]]; head[e[1]] = cnt++;
        }

        int[] order = new int[n];
        int[] parent = new int[n];
        int[] depth = new int[n];
        parent[0] = -1;
        int qh = 0, qt = 0;
        order[qt++] = 0;
        while (qh < qt) {
            int v = order[qh++];
            for (int e = head[v]; e != -1; e = nxt[e]) {
                int w = to[e];
                if (w != parent[v]) {
                    parent[w] = v;
                    depth[w] = depth[v] + 1;
                    order[qt++] = w;
                }
            }
        }

        int[] size = new int[n];
        int[] heavy = new int[n];
        Arrays.fill(size, 1);
        Arrays.fill(heavy, -1);
        for (int i = n - 1; i > 0; i--) {
            int v = order[i];
            int p = parent[v];
            size[p] += size[v];
            if (heavy[p] == -1 || size[v] > size[heavy[p]]) heavy[p] = v;
        }

        int[] chain = new int[n];
        int[] tin = new int[n];
        chain[0] = 0;
        for (int i = 1; i < n; i++) {
            int v = order[i];
            int p = parent[v];
            chain[v] = heavy[p] == v ? chain[p] : v;
        }
        for (int i = 0; i < n; i++) {
            int v = order[i];
            int pos = tin[v] + 1;
            for (int e = head[v]; e != -1; e = nxt[e]) {
                int w = to[e];
                if (w != parent[v]) {
                    tin[w] = pos;
                    pos += size[w];
                }
            }
        }

        char[] letters = s.toCharArray();
        bit = new int[n + 2];
        for (int v = 0; v < n; v++) {
            int b = 1 << (letters[v] - 'a');
            bit[tin[v] + 1] ^= b;
            int r = tin[v] + size[v] + 1;
            if (r <= n) bit[r] ^= b;
        }
        for (int i = 1; i <= n; i++) {
            int j = i + (i & -i);
            if (j <= n) bit[j] ^= bit[i];
        }

        List<Boolean> result = new ArrayList<>();

        for (String q : queries) {
            int len = q.length();
            if (q.charAt(0) == 'u') {
                int i = 7;
                int u = 0;
                while (q.charAt(i) != ' ') {
                    u = u * 10 + (q.charAt(i) - '0');
                    i++;
                }
                char c = q.charAt(i + 1);
                if (letters[u] != c) {
                    int diff = (1 << (letters[u] - 'a')) ^ (1 << (c - 'a'));
                    add(tin[u] + 1, diff);
                    int r = tin[u] + size[u] + 1;
                    if (r <= n) add(r, diff);
                    letters[u] = c;
                }
            } else {
                int i = 6;
                int a = 0;
                while (q.charAt(i) != ' ') {
                    a = a * 10 + (q.charAt(i) - '0');
                    i++;
                }
                i++;
                int b = 0;
                while (i < len) {
                    b = b * 10 + (q.charAt(i) - '0');
                    i++;
                }
                int x = a, y = b;
                while (chain[x] != chain[y]) {
                    if (depth[chain[x]] > depth[chain[y]]) x = parent[chain[x]];
                    else y = parent[chain[y]];
                }
                int lca = depth[x] < depth[y] ? x : y;
                int mask = get(tin[a] + 1) ^ get(tin[b] + 1) ^ (1 << (letters[lca] - 'a'));
                result.add((mask & (mask - 1)) == 0);
            }
        }

        return result;
    }
}