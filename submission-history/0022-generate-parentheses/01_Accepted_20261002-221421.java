class Solution {
    List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        generate("", 2 * n);
        return ans;
    }

    void generate(String s, int n) {
        if (s.length() == n) {
            if (valid(s)) {
                ans.add(s);
            }
            return;
        }

        generate(s + "(", n);
        generate(s + ")", n);
    }

    boolean valid(String s) {
        int count = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else {
                count--;
            }

            if (count < 0) {
                return false;
            }
        }

        return count == 0;
    }
}