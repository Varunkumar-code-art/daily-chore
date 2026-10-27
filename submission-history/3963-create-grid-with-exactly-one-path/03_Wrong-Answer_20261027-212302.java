class Solution {
    public String[] createGrid(int m, int n) {
        String[] ans = new String[m];

        for (int i = 0; i < m; i++) {
            char[] row = new char[n];
            java.util.Arrays.fill(row, '#');

            if (i == 0) {
                row[0] = '.';
                for (int j = 1; j < n - 1; j++) {
                    row[j] = '.';
                }
            }

            if (i == m - 1) {
                java.util.Arrays.fill(row, '.');
            }

            ans[i] = new String(row);
        }

        return ans;
    }
}