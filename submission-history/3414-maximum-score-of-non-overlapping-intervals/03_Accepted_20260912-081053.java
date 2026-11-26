class Solution {

    class Node {
        long score;
        List<Integer> indices;

        Node(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    int n;
    int[][] arr;
    int[] next;
    Node[][] dp;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        n = intervals.size();
        arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        next = new int[n];

        for (int i = 0; i < n; i++) {

            int low = i + 1;
            int high = n;

            while (low < high) {
                int mid = low + (high - low) / 2;

                if (arr[mid][0] > arr[i][1]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            next[i] = low;
        }

        dp = new Node[n + 1][5];

        Node answer = solve(0, 4);

        int[] result = new int[answer.indices.size()];

        for (int i = 0; i < answer.indices.size(); i++) {
            result[i] = answer.indices.get(i);
        }

        return result;
    }

    private Node solve(int i, int count) {

        if (i == n || count == 0) {
            return new Node(0, new ArrayList<>());
        }

        if (dp[i][count] != null) {
            return dp[i][count];
        }

        // Skip current interval
        Node skip = solve(i + 1, count);

        // Take current interval
        Node nextNode = solve(next[i], count - 1);

        List<Integer> list = new ArrayList<>(nextNode.indices);
        list.add(arr[i][3]);

        Collections.sort(list);

        Node take = new Node(
            arr[i][2] + nextNode.score,
            list
        );

        dp[i][count] = better(take, skip);

        return dp[i][count];
    }

    private Node better(Node a, Node b) {

        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        for (int i = 0; i < Math.min(a.indices.size(), b.indices.size()); i++) {
            if (!a.indices.get(i).equals(b.indices.get(i))) {
                return a.indices.get(i) < b.indices.get(i) ? a : b;
            }
        }

        return a.indices.size() <= b.indices.size() ? a : b;
    }
}