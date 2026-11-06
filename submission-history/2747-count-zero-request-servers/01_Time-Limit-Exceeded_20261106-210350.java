class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int left = queries[i] - x;
            int right = queries[i];

            boolean[] seen = new boolean[n + 1];
            int busy = 0;

            for (int[] log : logs) {
                int id = log[0];
                int t = log[1];

                if (t >= left && t <= right && !seen[id]) {
                    seen[id] = true;
                    busy++;
                }
            }

            ans[i] = n - busy;
        }

        return ans;
    }
}