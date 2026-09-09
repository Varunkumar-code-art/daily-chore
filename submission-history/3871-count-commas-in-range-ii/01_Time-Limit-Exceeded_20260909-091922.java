class Solution {
    public long countCommas(long n) {
        long ans = 0;

        for (long i = 1; i <= n; i++) {
            String s = String.valueOf(i);

            if (s.length() >= 4) {
                ans += (s.length() - 1) / 3;
            }
        }

        return ans;
    }
}