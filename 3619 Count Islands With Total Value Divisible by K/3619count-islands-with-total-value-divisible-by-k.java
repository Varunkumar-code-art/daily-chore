class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[] stack = new int[m * n];
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] <= 0) continue;

                int top = 0;
                stack[top++] = i * n + j;
                long sum = 0;
                grid[i][j] = -grid[i][j];

                while (top > 0) {
                    int cell = stack[--top];
                    int r = cell / n, c = cell % n;
                    sum -= grid[r][c];

                    if (r > 0 && grid[r - 1][c] > 0) {
                        grid[r - 1][c] = -grid[r - 1][c];
                        stack[top++] = (r - 1) * n + c;
                    }
                    if (r + 1 < m && grid[r + 1][c] > 0) {
                        grid[r + 1][c] = -grid[r + 1][c];
                        stack[top++] = (r + 1) * n + c;
                    }
                    if (c > 0 && grid[r][c - 1] > 0) {
                        grid[r][c - 1] = -grid[r][c - 1];
                        stack[top++] = r * n + c - 1;
                    }
                    if (c + 1 < n && grid[r][c + 1] > 0) {
                        grid[r][c + 1] = -grid[r][c + 1];
                        stack[top++] = r * n + c + 1;
                    }
                }

                if (sum % k == 0) count++;
            }
        }

        return count;
    }
}
