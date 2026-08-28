class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Check if palindrome is possible
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        // Create frequency for first half
        int halfLen = n / 2;
        int[] halfFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        String targetHalf = target.substring(0, halfLen);

        // First try to make half exactly equal to targetHalf
        if (canMake(halfFreq, targetHalf)) {

            String half = targetHalf;

            // If odd length, check if middle character
            // can make the palindrome greater
            if (n % 2 == 1) {
                char targetMiddle = target.charAt(halfLen);

                if (middle > targetMiddle) {
                    return makePalindrome(half, middle);
                }
            }
        }

        // Otherwise find the smallest half strictly greater
        // than targetHalf
        String half = nextHalf(halfFreq, targetHalf);

        if (half == null) {
            return "";
        }

        return makePalindrome(half, middle);
    }

    private boolean canMake(int[] freq, String str) {

        int[] copy = freq.clone();

        for (char c : str.toCharArray()) {
            if (copy[c - 'a'] == 0) {
                return false;
            }

            copy[c - 'a']--;
        }

        return true;
    }

    private String nextHalf(int[] freq, String target) {

        int n = target.length();

        // Try changing from right to left
        for (int pos = n - 1; pos >= 0; pos--) {

            int[] remaining = freq.clone();

            // Use characters before pos exactly like target
            boolean possible = true;

            for (int i = 0; i < pos; i++) {

                int c = target.charAt(i) - 'a';

                if (remaining[c] == 0) {
                    possible = false;
                    break;
                }

                remaining[c]--;
            }

            if (!possible) {
                continue;
            }

            int current = target.charAt(pos) - 'a';

            // Put a character greater than target[pos]
            for (int c = current + 1; c < 26; c++) {

                if (remaining[c] > 0) {

                    StringBuilder result = new StringBuilder();

                    // Prefix
                    result.append(target.substring(0, pos));

                    // Bigger character
                    result.append((char) ('a' + c));

                    remaining[c]--;

                    // Fill rest with smallest possible characters
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
ing(0, halfLen);

        // Try to construct the smallest half greater than target's half
        String half = build(halfFreq, halfTarget, 0);

        if (half == null) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        ans.append(half);

        if (n % 2 == 1) {
            ans.append(middle);
        }

        ans.append(new StringBuilder(half).reverse());

        String result = ans.toString();

        if (result.compareTo(target) <= 0) {
            return "";
        }

        return result;
    }

    private String build(int[] freq, String target, int pos) {

        int n = target.length();

        if (pos == n) {
            return target;
        }

        int current = target.charAt(pos) - 'a';

        // Try to keep the current character same
        if (freq[current] > 0) {

            freq[current]--;

            String result = build(freq, target, pos + 1);

            if (result != null) {
                return result;
            }

            freq[current]++;
        }

        // Try a character greater than target[pos]
        for (int c = current + 1; c < 26; c++) {

            if (freq[c] > 0) {

                freq[c]--;

                StringBuilder result = new StringBuilder();

                result.append(target.substring(0, pos));
                result.append((char) ('a' + c));

                // Fill remaining positions with smallest characters
                for (int j = 0; j < 26; j++) {
                    while (freq[j] > 0) {
                        result.append((char) ('a' + j));
                        freq[j]--;
                    }
                }

                return result.toString();
            }
        }

        return null;
    }
}