class Solution {
    public int minAddToMakeValid(String s) {
        int count = 0;

        while (s.contains("()")) {
            s = s.replace("()", "");
        }

        for (int i = 0; i < s.length(); i++) {
            count++;
        }

        return count;
    }
}