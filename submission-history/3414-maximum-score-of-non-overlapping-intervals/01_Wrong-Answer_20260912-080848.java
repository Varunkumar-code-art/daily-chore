class Solution {
    int n;
    int[][] arr;
    int[][] next;
    Long[][] dp;
    int[][][] choice;

    public int[] maximumWeight(List<List<Integer>> intervals) {
        n = intervals.size();

        arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0); // left
            arr[i][1] = intervals.get(i).get(1); // right
            arr[i][2] = intervals.get(i).get(2); // weight
            arr[i][3] = i;                       // original index
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[3], b[3]);
        });

        next = new int[n][1];

        for (int i = 0; i < n; i++) {
            int target = arr[i][1];

            int low = i + 1;
            int high = n;

            while (low < high) {
                int mid = low + (high - low) / 2;

                if (arr[mid][0] > target) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            next[i][0] = low;
        }

        dp = new Long[n + 1][5];
        choice = new int[n + 1][5][1];

        solve(0, 4);

        List<Integer> answer = new ArrayList<>();

        int i = 0;
        int k = 4;

        while (i < n && k > 0) {
            long skip = solve(i + 1, k);

            long take = arr[i][2];

            if (next[i][0] <= n) {
                take += solve(next[i][0], k - 1);
            }

            if (take > skip) {
                answer.add(arr[i][3]);
                i = next[i][0];
                k--;
            } else {
                i++;
            }
        }

        Collections.sort(answer);

        int[] result = new int[answer.size()];

        for (i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private long solve(int i, int k) {
        if (i >= n || k == 0) {
            return 0;
        }

        if (dp[i][k] != null) {
            return dp[i][k];
        }

        long skip = solve(i + 1, k);

        long take = arr[i][2] + solve(next[i][0], k - 1);

        dp[i][k] = Math.max(skip, take);

        return dp[i][k];
    }
}