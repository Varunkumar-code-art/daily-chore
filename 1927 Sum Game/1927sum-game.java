class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int diff = 0;
        int q = 0;

        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?')
                q++;
            else
                diff += num.charAt(i) - '0';
        }

        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?')
                q++;
            else
                diff -= num.charAt(i) - '0';
        }

        if (q % 2 == 1)
            return true;

        int leftQ = 0;
        int rightQ = 0;

        for (int i = 0; i < n / 2; i++)
            if (num.charAt(i) == '?')
                leftQ++;

        rightQ = q - leftQ;

        return diff != (rightQ - leftQ) * 9 / 2;
    }
}