class Solution {
    private static final int MOD = 1_000_000_007;
    private int m, n;
    private int[][] grid;

    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;

        return (walk(0, 1, 0) + walk(1, 0, 1)) % MOD;
    }

    private int walk(int i, int j, int dir) {
        if (i >= m || j >= n) return 0;

        if (grid[i][j] == 1) {
            if (dir == 0) return walk(i + 1, j, 1);
            return walk(i, j + 1, 0);
        }

        if (i == m - 1 && j == n - 1) return 1;

        return (walk(i, j + 1, 0) + walk(i + 1, j, 1)) % MOD;
    }
}