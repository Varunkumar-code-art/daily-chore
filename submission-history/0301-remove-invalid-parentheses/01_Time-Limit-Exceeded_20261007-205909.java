class Solution {
    public List<String> removeInvalidParentheses(String s) {
        Set<String> set = new HashSet<>();
        int n = s.length();

        for (int mask = 0; mask < (1 << n); mask++) {
            StringBuilder str = new StringBuilder();

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    str.append(s.charAt(i));
                }
            }

            if (isValid(str.toString())) {
                set.add(str.toString());
            }
        }

        int max = 0;

        for (String x : set) {
            max = Math.max(max, x.length());
        }

        List<String> ans = new ArrayList<>();

        for (String x : set) {
            if (x.length() == max) {
                ans.add(x);
            }
        }

        return ans;
    }

    public boolean isValid(String s) {
        int count = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;

                if (count < 0) {
                    return false;
                }
            }
        }

        return count == 0;
    }
}