class Solution {
    public int distinctSubseqII(String s) {
        HashSet<String> set = new HashSet<>();
        findSubsequences(s, 0, "", set);
        return set.size();
    }

    public void findSubsequences(String s, int index, String str, HashSet<String> set) {
        if (index == s.length()) {
            if (str.length() > 0) {
                set.add(str);
            }
            return;
        }

        findSubsequences(s, index + 1, str, set);
        findSubsequences(s, index + 1, str + s.charAt(index), set);
    }
}