class Solution {
    public int minChanges(String s) {
        int n = s.length();
        int[] best = new int[n + 1];

        for (int i = 2; i <= n; i += 2) {
            best[i] = Integer.MAX_VALUE;

            for (int start = i - 2; start >= 0; start -= 2) {
                int ones = 0;
                int zeros = 0;

                for (int k = start; k < i; k++) {
                    if (s.charAt(k) == '1') {
                        ones++;
                    } else {
                        zeros++;
                    }
                }

                int cost = best[start] + Math.min(ones, zeros);
                if (cost < best[i]) {
                    best[i] = cost;
                }
            }
        }

        return best[n];
    }
}