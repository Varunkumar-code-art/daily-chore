class Solution {
    private long[] heap = new long[1 << 16];
    private int size = 0;

    private void push(long v) {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, size << 1);
        }
        int i = size++;
        while (i > 0) {
            int p = (i - 1) >>> 1;
            if (heap[p] <= v) break;
            heap[i] = heap[p];
            i = p;
        }
        heap[i] = v;
    }

    private long pop() {
        long top = heap[0];
        long last = heap[--size];
        int i = 0;
        int half = size >>> 1;
        while (i < half) {
            int c = (i << 1) + 1;
            if (c + 1 < size && heap[c + 1] < heap[c]) c++;
            if (heap[c] >= last) break;
            heap[i] = heap[c];
            i = c;
        }
        heap[i] = last;
        return top;
    }

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

        dist[1] = 0;
        push(1L);

        while (size > 0) {
            long top = pop();
            int state = (int) (top & mask);
            long cost = top >>> shift;

            if (cost > dist[state]) {
                continue;
            }

            int node = state / width;
            int run = state - node * width;

            if (node == n - 1) {
                return (int) cost;
            }
            if (run >= minRun[node]) {
                continue;
            }
            minRun[node] = run;

            char c = lab[node];
            for (int i = head[node]; i < head[node + 1]; i++) {
                int next = to[i];
                int nextRun = lab[next] == c ? run + 1 : 1;
                if (nextRun > k || nextRun >= minRun[next]) {
                    continue;
                }

                long nextCost = cost + wt[i];
                int nextState = next * width + nextRun;
                if (nextCost < dist[nextState]) {
                    dist[nextState] = nextCost;
                    push((nextCost << shift) | nextState);
                }
            }
        }

        return -1;
    }
}