class Solution {
    public int uniquePaths(int[][] grid) {
        final int MOD = 1_000_000_007;
        int m = grid.length;
        int n = grid[0].length;

        int[][] goingRight = new int[m][n];
        int[][] goingDown = new int[m][n];

        goingRight[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int fromLeft = goingRight[i][j];
                int fromTop = goingDown[i][j];

                if (fromLeft == 0 && fromTop == 0) continue;

                if (grid[i][j] == 0) {
                    int total = (fromLeft + fromTop) % MOD;

                    if (j + 1 < n) {
                        goingRight[i][j + 1] = (goingRight[i][j + 1] + total) % MOD;
                    }
                    if (i + 1 < m) {
                        goingDown[i + 1][j] = (goingDown[i + 1][j] + total) % MOD;
                    }
                } else {
                    if (i + 1 < m) {
                        goingDown[i + 1][j] = (goingDown[i + 1][j] + fromLeft) % MOD;
                    }
                    if (j + 1 < n) {
                        goingRight[i][j + 1] = (goingRight[i][j + 1] + fromTop) % MOD;
                    }
                }
            }
        }

        return (goingRight[m - 1][n - 1] + goingDown[m - 1][n - 1]) % MOD;
    }
}