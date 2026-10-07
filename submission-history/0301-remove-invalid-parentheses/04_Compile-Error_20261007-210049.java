class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        int left = 0, right = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        dfs(s, 0, 0, left, right, new StringBuilder(), ans);
        return ans;
    }

    public void dfs(String s, int index, int balance, int left, int right, StringBuilder path, List<String> ans) {
        if (index == s.length()) {
            if (balance == 0 && left == 0 && right == 0) {
                ans.add(path.toString());
            }
            return;
        }

        char c = s.charAt(index);

        if (c == '(' && left > 0) {
            if (index == 0 || s.charAt(index - 1) != '(' || index > 0 && s.charAt(index - 1) == '(' && path.length() > 0) {
                dfs(s, index + 1, balance, left - 1, right, path, ans);
            }
        }

        if (c == ')' && right > 0) {
            dfs(s, index + 1, balance, left, right - 1, path, ans);
        }

        path.append(c);

        if (c == '(') {
            dfs(s, index + 1, balance + 1, left, right, path, ans);
        } else if (c == ')' && balance > 0) {
            dfs(s, index + 1, balance - 1, left, right, path, ans);
        } else if (c != ')') {
            dfs(s, index + 1, balance, left, right, path, ans);
        }

        path.deleteChar(path.length() - 1);
    }
}