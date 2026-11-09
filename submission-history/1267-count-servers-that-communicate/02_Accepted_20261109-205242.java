class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length, res = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                boolean found = false;
                for (int k = 0; k < n && !found; k++)
                    if (k != j && grid[i][k] == 1) found = true;
                for (int k = 0; k < m && !found; k++)
                    if (k != i && grid[k][j] == 1) found = true;
                if (found) res++;
            }
        }
        return res;
    }
}