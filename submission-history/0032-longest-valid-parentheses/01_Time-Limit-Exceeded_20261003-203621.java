class Solution {
    public int longestValidParentheses(String s) {
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j += 2) {
                if (isValid(s, i, j)) {
                    max = Math.max(max, j - i);
                }
            }
        }

        return max;
    }

    public boolean isValid(String s, int start, int end) {
        int count = 0;

        for (int i = start; i < end; i++) {
            if (s.charAt(i) == '(')
                count++;
            else
                count--;

            if (count < 0)
                return false;
        }

        return count == 0;
    }
}