class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        int q = queries.length;
        Integer[] order = new Integer[q];
        for (int i = 0; i < q; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> queries[a] - queries[b]);

        int[] count = new int[n + 1];
        int busy = 0;
        int l = 0;
        int r = 0;
        int[] ans = new int[q];

        for (int idx : order) {
            int right = queries[idx];
            int left = right - x;

            while (r < logs.length && logs[r][1] <= right) {
                if (count[logs[r][0]]++ == 0) {
                    busy++;
                }
                r++;
            }

            while (l < r && logs[l][1] < left) {
                if (--count[logs[l][0]] == 0) {
                    busy--;
                }
                l++;
            }

            ans[idx] = n - busy;
        }

        return ans;
    }
}