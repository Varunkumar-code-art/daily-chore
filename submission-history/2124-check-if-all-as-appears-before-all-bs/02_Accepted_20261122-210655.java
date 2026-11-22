class Solution {
    public boolean checkString(String s) {
        char[] original = s.toCharArray();
        char[] sorted = s.toCharArray();
        Arrays.sort(sorted);
        return Arrays.equals(original, sorted);
    }
}