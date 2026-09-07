class Solution {
    public int distinctSubseqII(String s) {
        long[] last = new long[26];
        long total = 0;

        for (char ch : s.toCharArray()) {
            int index = ch - 'a';

            long newSubsequences = (total + 1) % 1000000007;
            total = (total + newSubsequences - last[index] + 1000000007) % 1000000007;

            last[index] = newSubsequences;
        }

        return (int) total;
    }
}