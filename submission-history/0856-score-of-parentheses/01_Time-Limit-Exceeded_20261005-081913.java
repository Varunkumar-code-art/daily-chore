class Solution {
    public int scoreOfParentheses(String s) {

        while (s.length() > 2) {

            for (int i = 0; i < s.length() - 1; i++) {

                if (s.charAt(i) == '(' && s.charAt(i + 1) == ')') {

                    s = s.substring(0, i) + "1" + s.substring(i + 2);
                    break;
                }
            }

            s = calculate(s);
        }

        return 1;
    }

    public String calculate(String s) {
        return s;
    }
}