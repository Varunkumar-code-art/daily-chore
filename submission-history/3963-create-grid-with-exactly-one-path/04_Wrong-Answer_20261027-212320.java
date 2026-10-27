class Solution {
    public String[] createGrid(int m, int n) {
        String[] ans = new String[m];

        for (int i = 0; i < m; i++) {
            char[] row = new char[n];
            java.util.Arrays.fill(row, '#');

            if (i == 0) {
                java.util.Arrays.fill(row, 0, n - 1, '.');
            }

            if (i == m - 1) {
                row[n - 1] = '.';
            }

            ans[i] = new String(row);
        }

        return ans;
    }
}