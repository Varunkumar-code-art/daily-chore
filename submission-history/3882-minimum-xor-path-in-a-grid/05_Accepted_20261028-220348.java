class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][][] seen = new boolean[m][n][1024];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0, grid[0][0]});
        seen[0][0][grid[0][0]] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];
            int xor = curr[2];

            if (row == m - 1 && col == n - 1) {
                for (int value = 0; value < 1024; value++) {
                    if (seen[row][col][value]) {
                        return value;
                    }
                }
            }

            if (col + 1 < n) {
                int nextXor = xor ^ grid[row][col + 1];
                if (!seen[row][col + 1][nextXor]) {
                    seen[row][col + 1][nextXor] = true;
                    queue.offer(new int[]{row, col + 1, nextXor});
                }
            }

            if (row + 1 < m) {
                int nextXor = xor ^ grid[row + 1][col];
                if (!seen[row + 1][col][nextXor]) {
                    seen[row + 1][col][nextXor] = true;
                    queue.offer(new int[]{row + 1, col, nextXor});
                }
            }
        }

        return 0;
    }
}