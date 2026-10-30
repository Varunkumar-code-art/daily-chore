class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
        }

        int INF = Integer.MAX_VALUE;
        int[][] dist = new int[n][k + 1];
        for (int[] row : dist) {
            Arrays.fill(row, INF);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0];
            int node = cur[1];
            int run = cur[2];

            if (cost > dist[node][run]) {
                continue;
            }
            if (node == n - 1) {
                return cost;
            }

            for (int[] edge : graph[node]) {
                int next = edge[0];
                int nextCost = cost + edge[1];

                int nextRun = labels.charAt(next) == labels.charAt(node) ? run + 1 : 1;
                if (nextRun > k) {
                    continue;
                }

                if (nextCost < dist[next][nextRun]) {
                    dist[next][nextRun] = nextCost;
                    pq.offer(new int[]{nextCost, next, nextRun});
                }
            }
        }

        return -1;
    }
}