class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] colCount = new int[n];

        for (int[] r : grid)
            for (int j = 0; j < n; j++)
                colCount[j] += r[j];

        int res = 0;
        for (int i = 0; i < m; i++) {
            int cnt = 0, lastCol = -1;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) { cnt++; lastCol = j; }
            }
            if (cnt > 1) res += cnt;
            else if (cnt == 1 && colCount[lastCol] > 1) res++;
        }
        return res;
    }
}