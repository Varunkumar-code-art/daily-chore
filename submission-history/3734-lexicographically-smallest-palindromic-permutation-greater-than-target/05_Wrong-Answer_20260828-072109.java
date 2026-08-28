class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();

        if (n == 1) {
            return s.compareTo(target) > 0 ? s : "";
        }

        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        String oddChar = "";

        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 != 0) {
                if (!oddChar.isEmpty()) {
                    return "";
                }
                oddChar = String.valueOf((char) ('a' + i));
            }

            cnt[i] /= 2;
        }

        StringBuilder prefix = new StringBuilder();

        for (int i = 0; i < n / 2; i++) {
            boolean found = false;

            for (int j = 0; j < 26; j++) {
                if (cnt[j] == 0) {
                    continue;
                }

                cnt[j]--;

                if (check(prefix.toString(), (char) ('a' + j), cnt, oddChar, target)) {
                    prefix.append((char) ('a' + j));
                    found = true;
                    break;
                }

                cnt[j]++;
            }

            if (!found) {
                return "";
            }

            if (prefix.charAt(i) > target.charAt(i)) {
                StringBuilder left = new StringBuilder(prefix);

                for (int j = 0; j < 26; j++) {
                    while (cnt[j] > 0) {
                        left.append((char) ('a' + j));
                        cnt[j]--;
                    }
                }

                return left + oddChar + left.reverse();
            }
        }

        String ans = prefix + oddChar + new StringBuilder(prefix).reverse();

        return ans.compareTo(target) > 0 ? ans : "";
    }

    private boolean check(String prefix, char c, int[] cnt, String oddChar, String target) {
        StringBuilder left = new StringBuilder(prefix);
        left.append(c);

        for (int i = 25; i >= 0; i--) {
            while (cnt[i] > 0) {
                left.append((char) ('a' + i));
                cnt[i]--;
            }
        }

        String palindrome = left + oddChar + left.reverse();

        for (int i = 0; i < 26; i++) {
            cnt[i] = 0;
        }

        return palindrome.compareTo(target) > 0;
    }
}