class Solution {

    class Pair {
        long score;
        List<Integer> list;

        Pair(long score, List<Integer> list) {
            this.score = score;
            this.list = list;
        }
    }

    int n;
    int[][] intervals;
    int[] next;
    Pair[][] dp;

    public int[] maximumWeight(List<List<Integer>> input) {

        n = input.size();
        intervals = new int[n][4];

        for (int i = 0; i < n; i++) {
            intervals[i][0] = input.get(i).get(0);
            intervals[i][1] = input.get(i).get(1);
            intervals[i][2] = input.get(i).get(2);
            intervals[i][3] = i;
        }

        // Sort according to starting time
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[3], b[3]);
        });

        // Find the next interval that does not overlap
        next = new int[n];

        for (int i = 0; i < n; i++) {

            int left = i + 1;
            int right = n;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (intervals[mid][0] > intervals[i][1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            next[i] = left;
        }

        dp = new Pair[n + 1][5];

        Pair answer = solve(0, 4);

        int[] result = new int[answer.list.size()];

        for (int i = 0; i < answer.list.size(); i++) {
            result[i] = answer.list.get(i);
        }

        return result;
    }

    private Pair solve(int index, int left) {

        if (index >= n || left == 0) {
            return new Pair(0, new ArrayList<>());
        }

        if (dp[index][left] != null) {
            return dp[index][left];
        }
        Pair skip = solve(index + 1, left);
        Pair nextResult = solve(next[index], left - 1);

        List<Integer> takeIndices = new ArrayList<>(nextResult.list);
        takeIndices.add(intervals[index][3]);

        Collections.sort(takeIndices);

        Pair take = new Pair(
            intervals[index][2] + nextResult.score,
            takeIndices
        );

        dp[index][left] = chooseBetter(take, skip);

        return dp[index][left];
    }

    private Pair chooseBetter(Pair a, Pair b) {

        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        int size = Math.min(a.list.size(), b.list.size());

        for (int i = 0; i < size; i++) {
            if (!a.list.get(i).equals(b.list.get(i))) {
                return a.list.get(i) < b.list.get(i) ? a : b;
            }
        }

        return a.list.size() <= b.list.size() ? a : b;
    }
}