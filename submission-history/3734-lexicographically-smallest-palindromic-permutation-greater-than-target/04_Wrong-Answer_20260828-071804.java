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

        if (canBuild(targetHalf, halfCount)) {
            if (n % 2 == 1 && middle > target.charAt(halfLength)) {
                return buildPalindrome(targetHalf, middle);
            }
        }

        String half = nextHalf(targetHalf, halfCount);

        if (half == null) {
            return "";
        }

        return buildPalindrome(half, middle);
    }

    private boolean canBuild(String str, int[] count) {
        int[] temp = count.clone();

        for (char c : str.toCharArray()) {
            int x = c - 'a';

            if (temp[x] == 0) {
                return false;
            }

            temp[x]--;
        }

        return true;
    }

    private String nextHalf(String target, int[] count) {
        int n = target.length();

        for (int pos = n - 1; pos >= 0; pos--) {
            int[] remaining = count.clone();
            boolean possible = true;

            for (int i = 0; i < pos; i++) {
                int x = target.charAt(i) - 'a';

                if (remaining[x] == 0) {
                    possible = false;
                    break;
                }

                remaining[x]--;
            }

            if (!possible) {
                continue;
            }

            int current = target.charAt(pos) - 'a';

            for (int c = current + 1; c < 26; c++) {
                if (remaining[c] > 0) {
                    StringBuilder result = new StringBuilder();

                    result.append(target.substring(0, pos));
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

    private String buildPalindrome(String half, char middle) {
        StringBuilder result = new StringBuilder();

        result.append(half);

        if (middle != 0) {
            result.append(middle);
        }

        result.append(new StringBuilder(half).reverse());

        return result.toString();
    }
}