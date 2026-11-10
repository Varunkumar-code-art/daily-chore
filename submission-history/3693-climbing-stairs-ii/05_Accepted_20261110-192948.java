class Solution {
    private long[] memo;
    private int[] costs;

    public int climbStairs(int n, int[] costs) {
        this.costs = costs;
        memo = new long[n + 1];
        java.util.Arrays.fill(memo, -1);
        long[] result = new long[1];

        Thread t = new Thread(null, () -> result[0] = solve(n), "dp", 1L << 28);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return (int) result[0];
    }

    // min cost to reach step j from step 0
    private long solve(int j) {
        if (j == 0) return 0;
        if (memo[j] != -1) return memo[j];

        long INF = Long.MAX_VALUE / 4;
        long best = INF;
        for (int k = 1; k <= 3; k++) {
            int i = j - k;
            if (i < 0) break;
            best = Math.min(best, solve(i) + (long) k * k);
        }
        return memo[j] = best + costs[j - 1];
    }
}