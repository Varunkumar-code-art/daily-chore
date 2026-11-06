import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        int m = logs.length;
        int[] times = new int[m];
        for (int i = 0; i < m; i++) {
            times[i] = logs[i][1];
        }

        int[] ans = new int[queries.length];
        int[] mark = new int[n + 1];
        Arrays.fill(mark, -1);

        for (int i = 0; i < queries.length; i++) {
            int left = queries[i] - x;
            int right = queries[i];

            int start = lowerBound(times, left);
            int end = lowerBound(times, right + 1);

            int busy = 0;
            for (int j = start; j < end; j++) {
                int id = logs[j][0];
                if (mark[id] != i) {
                    mark[id] = i;
                    busy++;
                }
            }

            ans[i] = n - busy;
        }

        return ans;
    }

    private int lowerBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}