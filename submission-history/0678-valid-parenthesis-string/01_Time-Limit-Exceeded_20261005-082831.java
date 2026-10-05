class Solution {
    public boolean checkValidString(String s) {
        return solve(s, 0, 0);
    }

    public boolean solve(String s, int i, int count) {
        if (count < 0)
            return false;

        if (i == s.length())
            return count == 0;

        if (s.charAt(i) == '(')
            return solve(s, i + 1, count + 1);

        if (s.charAt(i) == ')')
            return solve(s, i + 1, count - 1);

        return solve(s, i + 1, count + 1) ||
               solve(s, i + 1, count - 1) ||
               solve(s, i + 1, count);
    }
}