import java.util.*;

class Solution {
    public int minimumEdgeReversal(int[][] edges, int n, int src, int dst) {
        ArrayList<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(new int[]{v, 0});
            graph[v].add(new int[]{u, 1});
        }

        boolean[] visited = new boolean[n + 1];

        int answer = dfs(src, dst, graph, visited);

        if (answer == Integer.MAX_VALUE) {
            return -1;
        }

        return answer;
    }

    public int dfs(int node, int dst, ArrayList<int[]>[] graph, boolean[] visited) {
        if (node == dst) {
            return 0;
        }

        visited[node] = true;

        int answer = Integer.MAX_VALUE;

        for (int[] edge : graph[node]) {
            int next = edge[0];
            int cost = edge[1];

            if (!visited[next]) {
                int result = dfs(next, dst, graph, visited);

                if (result != Integer.MAX_VALUE) {
                    answer = Math.min(answer, cost + result);
                }
            }
        }

        visited[node] = false;

        return answer;
    }
}