class Solution {
    public boolean canAliceWin(int n) {
        int stones = n;
        int take = 10;
        boolean aliceTurn = true;

        while (true) {
            if (stones < take) {
                if (aliceTurn) {
                    return false;
                } else {
                    return true;
                }
            }
            stones = stones - take;
            take = take - 1;
            aliceTurn = !aliceTurn;
        }
    }
}