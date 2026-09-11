class Solution {
    public int totalNumbers(int[] digits) {

        int count = 0;

        for (int num = 100; num <= 998; num++) {

            if (num % 2 != 0) {
                continue;
            }

            int a = num / 100;
            int b = (num / 10) % 10;
            int c = num % 10;

            int[] used = new int[10];

            used[a]++;
            used[b]++;
            used[c]++;

            int[] available = new int[10];

            for (int digit : digits) {
                available[digit]++;
            }

            boolean possible = true;

            for (int i = 0; i < 10; i++) {
                if (used[i] > available[i]) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                count++;
            }
        }

        return count;
    }
}