class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0;
        int litter = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(classroom[i].charAt(j) == 'S') {
                    sr = i;
                    sc = j;
                }
                if(classroom[i].charAt(j) == 'L') {
                    litter++;
                }
            }
        }

        int[][] id = new int[m][n];

        for(int i = 0; i < m; i++) {
            java.util.Arrays.fill(id[i], -1);
        }

        int index = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(classroom[i].charAt(j) == 'L') {
                    id[i][j] = index++;
                }
            }
        }

        int full = (1 << litter) - 1;

        boolean[][][][] visited = new boolean[m][n][energy + 1][1 << litter];

        java.util.Queue<int[]> q = new java.util.ArrayDeque<>();

        q.offer(new int[]{sr, sc, energy, 0});
        visited[sr][sc][energy][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while(!q.isEmpty()) {
            int size = q.size();

            while(size-- > 0) {
                int[] cur = q.poll();

                int r = cur[0];
                int c = cur[1];
                int e = cur[2];
                int mask = cur[3];

                if(mask == full) {
                    return moves;
                }

                if(e == 0) {
                    continue;
                }

                for(int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    if(nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    char ch = classroom[nr].charAt(nc);

                    if(ch == 'X') {
                        continue;
                    }

                    int ne = e - 1;
                    int nmask = mask;

                    if(ch == 'R') {
                        ne = energy;
                    }

                    if(ch == 'L') {
                        nmask |= 1 << id[nr][nc];
                    }

                    if(!visited[nr][nc][ne][nmask]) {
                        visited[nr][nc][ne][nmask] = true;
                        q.offer(new int[]{nr, nc, ne, nmask});
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}