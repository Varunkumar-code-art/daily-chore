class Solution {
    int minXor = Integer.MAX_VALUE;

    public int minCost(int[][] grid) {
        findPaths(grid, 0, 0, 0);
        return minXor;
    }

    void findPaths(int[][] grid, int row, int col, int xor) {
        xor = xor ^ grid[row][col];

        if (row == grid.length - 1 && col == grid[0].length - 1) {
            minXor = Math.min(minXor, xor);
            return;
        }

        if (col + 1 < grid[0].length) {
            findPaths(grid, row, col + 1, xor);
        }

        if (row + 1 < grid.length) {
            findPaths(grid, row + 1, col, xor);
        }
    }
}