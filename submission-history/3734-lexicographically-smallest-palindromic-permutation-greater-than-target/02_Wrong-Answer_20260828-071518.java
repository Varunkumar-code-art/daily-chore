
class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        int halfLength = n / 2;
        int[] halfCount = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        String targetHalf = target.substring(0, halfLength);
        String half = findHalf(halfCount, targetHalf);

        if (half == null) {
            return "";
        }

        StringBuilder ans = new StringBuilder();
        ans.append(half);

        if (n % 2 != 0) {
            ans.append(middle);
        }

        ans.append(new StringBuilder(half).reverse());

        return ans.toString();
    }

    private String findHalf(int[] count, String target) {
        int n = target.length();

        for (int i = 0; i < n; i++) {
            int[] remaining = count.clone();
            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';

                if (remaining[c] == 0) {
                    possible = false;
                    break;
                }

                remaining[c]--;
            }

            if (!possible) {
                continue;
            }

            int current = target.charAt(i) - 'a';

            for (int c = current + 1; c < 26; c++) {
                if (remaining[c] > 0) {
                    StringBuilder result = new StringBuilder();

                    result.append(target.substring(0, i));
                    result.append((char) ('a' + c));

                    remaining[c]--;

                    for (int j = 0; j < 26; j++) {
                        while (remaining[j] > 0) {
                            result.append((char) ('a' + j));
                            remaining[j]--;
                        }
                    }

                    return result.toString();
                }
            }
        }

        return null;
    }
}

