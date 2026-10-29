import java.util.*;

class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        int[] parent = new int[n];
        int[] depth = new int[n];
        boolean[] seen = new boolean[n];
        Arrays.fill(parent, -1);

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        seen[0] = true;
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            for (int nxt : graph.get(cur)) {
                if (!seen[nxt]) {
                    seen[nxt] = true;
                    parent[nxt] = cur;
                    depth[nxt] = depth[cur] + 1;
                    stack.push(nxt);
                }
            }
        }

        char[] letters = s.toCharArray();
        List<Boolean> result = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int node = Integer.parseInt(parts[1]);
                letters[node] = parts[2].charAt(0);
                continue;
            }

            int a = Integer.parseInt(parts[1]);
            int b = Integer.parseInt(parts[2]);
            int mask = 0;

            while (a != b) {
                if (depth[a] >= depth[b]) {
                    mask ^= 1 << (letters[a] - 'a');
                    a = parent[a];
                } else {
                    mask ^= 1 << (letters[b] - 'a');
                    b = parent[b];
                }
            }

            mask ^= 1 << (letters[a] - 'a');

            result.add((mask & (mask - 1)) == 0);
        }

        return result;
    }
}