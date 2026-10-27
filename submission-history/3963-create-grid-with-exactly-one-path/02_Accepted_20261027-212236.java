class Solution {
    public String[] createGrid(int m, int n) {
        String[] ans = new String[m];

        for (int i = 0; i < m; i++) {
            char[] row = new char[n];

            for (int j = 0; j < n; j++) {
                row[j] = '#';
            }

            ans[i] = new String(row);
        }

        char[][] grid = new char[m][n];

        for (int i = 0; i < m; i++) {
            grid[i] = ans[i].toCharArray();
            grid[i][0] = '.';
        }

        for (int j = 0; j < n; j++) {
            grid[m - 1][j] = '.';
        }

        for (int i = 0; i < m; i++) {
            ans[i] = new String(grid[i]);
        }

        return ans;
    }
}