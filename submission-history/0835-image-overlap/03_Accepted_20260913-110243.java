class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;

        int[][] count = new int[2 * n - 1][2 * n - 1];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (img1[i][j] == 0) {
                    continue;
                }

                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < n; y++) {

                        if (img2[x][y] == 0) {
                            continue;
                        }

                        int rowShift = x - i + n - 1;
                        int colShift = y - j + n - 1;

                        count[rowShift][colShift]++;

                        answer = Math.max(answer, count[rowShift][colShift]);
                    }
                }
            }
        }

        return answer;
    }
}