class Solution {

    public int maxPalindromes(String s, int k) {

        int n = s.length();
        boolean[][] palindrome = new boolean[n][n];
        for (int length = 1; length <= n; length++) {

            for (int start = 0; start + length <= n; start++) {

                int end = start + length - 1;

                if (s.charAt(start) == s.charAt(end)) {

                    if (length <= 2) {
                        palindrome[start][end] = true;
                    } else {
                        palindrome[start][end] =
                            palindrome[start + 1][end - 1];
                    }
                }
            }
        }

        int[] dp = new int[n + 1];

        for (int end = 1; end <= n; end++) {
            dp[end] = dp[end - 1];

            for (int start = 0; start <= end - k; start++) {

                if (palindrome[start][end - 1]) {
                    dp[end] = Math.max(dp[end], dp[start] + 1);
                }
            }
        }

        return dp[n];
    }
}