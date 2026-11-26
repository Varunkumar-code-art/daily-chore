class Solution {
    public boolean canAliceWin(int n) {
        int take = 10;
        int moves = 0;

        while (n >= take && take > 0) {
            n -= take;
            take--;
            moves++;
        }

        return moves % 2 == 1;
    }
}