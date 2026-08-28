
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

        if (canMake(halfCount, targetHalf)) {
            if (n % 2 == 1 && middle > target.charAt(halfLength)) {
                return makePalindrome(targetHalf, middle);
            }
        }

        String half = findHalf(halfCount, targetHalf);

        if (half == null) {
            return "";
        }

        return makePalindrome(half, middle);
    }

    private boolean canMake(int[] count, String target) {
        int[] temp = count.clone();

        for (char c : target.toCharArray()) {
            int index = c - 'a';

            if (temp[index] == 0) {
                return false;
            }

            temp[index]--;
        }

        return true;
    }

    private String findHalf(int[] count, String target) {
        int n = target.length();

        for (int i = n - 1; i >= 0; i--) {
            int[] remaining = count.clone();
            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int index = target.charAt(j) - 'a';

                if (remaining[index] == 0) {
                    possible = false;
                    break;
                }

                remaining[index]--;
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

    private String makePalindrome(String half, char middle) {
        StringBuilder result = new StringBuilder();

        result.append(half);

        if (middle != 0) {
            result.append(middle);
        }

        result.append(new StringBuilder(half).reverse());

        return result.toString();
    }
}

