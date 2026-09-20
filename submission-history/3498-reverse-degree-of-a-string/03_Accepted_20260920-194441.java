class Solution {
    public int reverseDegree(String s) {
        int[] reverseVal = new int[26];
        for (int i = 0; i < 26; i++) {
            reverseVal[i] = 26 - i;
        }
        
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += reverseVal[s.charAt(i) - 'a'] * (i + 1);
        }
        return sum;
    }
}