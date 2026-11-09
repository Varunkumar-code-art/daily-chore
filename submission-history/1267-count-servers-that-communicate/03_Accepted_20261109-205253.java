class Solution {
    private int[] parent;

    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private void union(int a, int b) {
        parent[find(a)] = find(b);
    }

    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        parent = new int[m + n];
        for (int i = 0; i < m + n; i++) parent[i] = i;

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 1) union(i, m + j);

        int[] size = new int[m + n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 1) size[find(i)]++;

        int res = 0;
        for (int s : size) if (s > 1) res += s;
        return res;
    }
}