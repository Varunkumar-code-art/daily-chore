class Solution {
    public String[] createGrid(int m, int n) {
        char[][] g = new char[m][n];
        for (char[] row : g) java.util.Arrays.fill(row, '#');

        int r = 0, c = 0;
        g[0][0] = '.';
        boolean right = true;

        while (r < m - 1 || c < n - 1) {
            if ((right && c < n - 1) || r == m - 1) {
                c++;
            } else {
                r++;
            }
            right = !right;
            g[r][c] = '.';
        }

        String[] ans = new String[m];
        for (int i = 0; i < m; i++) ans[i] = new String(g[i]);
        return ans;
    }
}