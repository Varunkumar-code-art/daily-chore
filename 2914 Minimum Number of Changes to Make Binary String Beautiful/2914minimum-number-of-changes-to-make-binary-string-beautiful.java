class Solution {
    public int minChanges(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i += 2) {
            answer += s.charAt(i) ^ s.charAt(i + 1);
        }

        return answer;
    }
}