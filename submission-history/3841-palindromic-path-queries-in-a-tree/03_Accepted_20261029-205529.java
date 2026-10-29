import java.util.*;

class Solution {
    int[] bit;
    int size;

    void flip(int i, int val) {
        for (i++; i <= size; i += i & -i) {
            bit[i] ^= val;
        }
    }

    int get(int i) {
        int res = 0;
        for (i++; i > 0; i -= i & -i) {
            res ^= bit[i];
        }
        return res;
    }

    void rangeFlip(int l, int r, int val) {
        flip(l, val);
        flip(r + 1, val);
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        size = n + 1;
        bit = new int[size + 1];

        char[] letters = s.toCharArray();
        int LOG = 17;
        int[][] up = new int[LOG][n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] ptr = new int[n];
        int[] parent = new int[n];

        int timer = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        parent[0] = 0;
        tin[0] = timer++;

        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (ptr[v] < graph.get(v).size()) {
                int w = graph.get(v).get(ptr[v]++);
                if (v != 0 && w == parent[v]) {
                    continue;
                }
                parent[w] = v;
                depth[w] = depth[v] + 1;
                tin[w] = timer++;
                stack.push(w);
            } else {
                tout[v] = timer - 1;
                stack.pop();
            }
        }

        for (int v = 0; v < n; v++) {
            up[0][v] = parent[v];
        }
        for (int j = 1; j < LOG; j++) {
            for (int v = 0; v < n; v++) {
                up[j][v] = up[j - 1][up[j - 1][v]];
            }
        }

        for (int v = 0; v < n; v++) {
            rangeFlip(tin[v], tout[v], 1 << (letters[v] - 'a'));
        }

        List<Boolean> result = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                if (letters[u] != c) {
                    int diff = (1 << (letters[u] - 'a')) ^ (1 << (c - 'a'));
                    rangeFlip(tin[u], tout[u], diff);
                    letters[u] = c;
                }
                continue;
            }

            int a = Integer.parseInt(parts[1]);
            int b = Integer.parseInt(parts[2]);

            int x = a, y = b;
            if (depth[x] < depth[y]) {
                int t = x;
                x = y;
                y = t;
            }
            int gap = depth[x] - depth[y];
            for (int j = 0; j < LOG; j++) {
                if (((gap >> j) & 1) == 1) {
                    x = up[j][x];
                }
            }
            if (x != y) {
                for (int j = LOG - 1; j >= 0; j--) {
                    if (up[j][x] != up[j][y]) {
                        x = up[j][x];
                        y = up[j][y];
                    }
                }
                x = up[0][x];
            }
            int lca = x;

            int mask = get(tin[a]) ^ get(tin[b]) ^ (1 << (letters[lca] - 'a'));
            result.add((mask & (mask - 1)) == 0);
        }

        return result;
    }
}