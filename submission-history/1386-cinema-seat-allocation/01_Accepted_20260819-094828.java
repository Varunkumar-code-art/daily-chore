class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMask = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col < 2 || col > 9) continue; // seats 1 and 10 never matter

            int bit = 1 << (col - 2); // map columns 2..9 to bits 0..7
            rowMask.put(row, rowMask.getOrDefault(row, 0) | bit);
        }

        // fully empty rows (not in the map) get 2 groups each
        int result = (n - rowMask.size()) * 2;

        // block masks for seats [2-5], [4-7], [6-9]
        int left = 0b00001111;
        int mid  = 0b00111100;
        int right= 0b11110000;

        for (int mask : rowMask.values()) {
            if ((mask & left) == 0) {
                result += 1;
            } else if ((mask & mid) == 0) {
                result += 1;
            } else if ((mask & right) == 0) {
                result += 1;
                continue;
            }
            // check if a second block fits after using one
            if ((mask & left) == 0 && (mask & right) == 0) {
                result += 1;
            }
        }

        return result;
    }
}