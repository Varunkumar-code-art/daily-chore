class Solution {
    int ans = Integer.MAX_VALUE;
    int m, n, energy;
    char[][] grid;
    boolean[][][][] visited;

    public int minMoves(String[] classroom, int energy) {
        m = classroom.length;
        n = classroom[0].length();
        this.energy = energy;

        grid = new char[m][n];

        int sr = 0, sc = 0;
        int litter = 0;

        for(int i = 0; i < m; i++) {
            grid[i] = classroom[i].toCharArray();

            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 'S') {
                    sr = i;
                    sc = j;
                }

                if(grid[i][j] == 'L') {
                    litter++;
                }
            }
        }

        visited = new boolean[m][n][energy + 1][1 << litter];

        int index = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 'L') {
                    grid[i][j] = (char)('a' + index);
                    index++;
                }
            }
        }

        dfs(sr, sc, energy, 0, 0, litter);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    void dfs(int r, int c, int e, int mask, int moves, int total) {

        if(mask == (1 << total) - 1) {
            ans = Math.min(ans, moves);
            return;
        }

        if(moves >= ans) {
            return;
        }

        if(visited[r][c][e][mask]) {
            return;
        }

        visited[r][c][e][mask] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for(int k = 0; k < 4; k++) {

            int nr = r + dr[k];
            int nc = c + dc[k];

            if(nr < 0 || nr >= m || nc < 0 || nc >= n) {
                continue;
            }

            if(grid[nr][nc] == 'X') {
                continue;
            }

            if(e == 0) {
                continue;
            }

            int ne = e - 1;
            int nmask = mask;

            if(grid[nr][nc] == 'R') {
                ne = energy;
            }

            if(grid[nr][nc] >= 'a' && grid[nr][nc] <= 'j') {
                int id = grid[nr][nc] - 'a';
                nmask = nmask | (1 << id);
            }

            dfs(nr, nc, ne, nmask, moves + 1, total);
        }
    }
}