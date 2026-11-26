class Solution {
    public boolean canAliceWin(int n) {
        int[] totals = {10, 19, 27, 34, 40, 45, 49, 52, 54, 55};
        int moves = 0;

        for (int total : totals) {
            if (n >= total) {
                moves++;
            } else {
                break;
            }
        }

        return (moves & 1) == 1;
    }
}