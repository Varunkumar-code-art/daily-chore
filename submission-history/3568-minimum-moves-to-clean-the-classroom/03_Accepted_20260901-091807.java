class Solution {
    int m, n, energy;
    char[][] grid;

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

        int index = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 'L') {
                    grid[i][j] = (char)('a' + index);
                    index++;
                }
            }
        }

        boolean[][][][] visited = new boolean[m][n][energy + 1][1 << litter];

        java.util.Queue<int[]> q = new java.util.LinkedList<>();

        q.offer(new int[]{sr, sc, energy, 0, 0});
        visited[sr][sc][energy][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            int r = cur[0];
            int c = cur[1];
            int e = cur[2];
            int mask = cur[3];
            int moves = cur[4];

            if(mask == (1 << litter) - 1) {
                return moves;
            }

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

                if(!visited[nr][nc][ne][nmask]) {
                    visited[nr][nc][ne][nmask] = true;
                    q.offer(new int[]{nr, nc, ne, nmask, moves + 1});
                }
            }
        }

        return -1;
    }
}
