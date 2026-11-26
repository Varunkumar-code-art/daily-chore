class Solution {
    List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        generate(new StringBuilder(), 0, 0, n);
        return ans;
    }

    void generate(StringBuilder s, int open, int close, int n) {
        if (s.length() == 2 * n) {
            ans.add(s.toString());
            return;
        }

        if (open < n) {
            s.append('(');
            generate(s, open + 1, close, n);
            s.deleteCharAt(s.length() - 1);
        }

        if (close < open) {
            s.append(')');
            generate(s, open, close + 1, n);
            s.deleteCharAt(s.length() - 1);
        }
    }
}