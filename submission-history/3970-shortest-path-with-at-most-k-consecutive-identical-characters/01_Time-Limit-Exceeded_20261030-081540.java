import java.util.*;

class Solution {
    private int best = Integer.MAX_VALUE;
    private List<int[]>[] graph;
    private String labels;
    private int n, k, maxSteps;

    public int shortestPath(int n, int[][] edges, String labels, int k) {
        this.n = n;
        this.k = k;
        this.labels = labels;
        this.maxSteps = n * k;

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
        }

        explore(0, 1, 0, 0);

        return best == Integer.MAX_VALUE ? -1 : best;
    }

    private void explore(int node, int run, int cost, int steps) {
        if (cost >= best || steps > maxSteps) {
            return;
        }
        if (node == n - 1) {
            best = cost;
            return;
        }

        for (int[] edge : graph[node]) {
            int next = edge[0];
            int weight = edge[1];

            int nextRun = labels.charAt(next) == labels.charAt(node) ? run + 1 : 1;
            if (nextRun > k) {
                continue;
            }

            explore(next, nextRun, cost + weight, steps + 1);
        }
    }
}