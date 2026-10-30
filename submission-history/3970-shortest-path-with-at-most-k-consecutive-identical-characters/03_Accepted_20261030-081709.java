class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        int m = edges.length;

        int[] head = new int[n + 1];
        for (int[] e : edges) {
            head[e[0] + 1]++;
        }
        for (int i = 0; i < n; i++) {
            head[i + 1] += head[i];
        }
        int[] to = new int[m];
        int[] wt = new int[m];
        int[] fill = Arrays.copyOf(head, n);
        for (int[] e : edges) {
            int p = fill[e[0]]++;
            to[p] = e[1];
            wt[p] = e[2];
        }

        char[] lab = labels.toCharArray();
        int width = k + 1;
        int shift = 22;
        long mask = (1L << shift) - 1;

        long[] dist = new long[n * width];
        Arrays.fill(dist, Long.MAX_VALUE);

        int[] minRun = new int[n];
        Arrays.fill(minRun, Integer.MAX_VALUE);

        PriorityQueue<Long> pq = new PriorityQueue<>();
        dist[1] = 0;
        pq.offer(1L);

        while (!pq.isEmpty()) {
            long top = pq.poll();
            int state = (int) (top & mask);
            long cost = top >>> shift;

            if (cost > dist[state]) {
                continue;
            }

            int node = state / width;
            int run = state % width;

            if (node == n - 1) {
                return (int) cost;
            }
            if (run >= minRun[node]) {
                continue;
            }
            minRun[node] = run;

            for (int i = head[node]; i < head[node + 1]; i++) {
                int next = to[i];
                int nextRun = lab[next] == lab[node] ? run + 1 : 1;
                if (nextRun > k || nextRun >= minRun[next]) {
                    continue;
                }

                long nextCost = cost + wt[i];
                int nextState = next * width + nextRun;
                if (nextCost < dist[nextState]) {
                    dist[nextState] = nextCost;
                    pq.offer((nextCost << shift) | nextState);
                }
            }
        }

        return -1;
    }
}