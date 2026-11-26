class Solution {
    public String digitSum(String s, int k) {
        int n = s.length();
        if (n <= k) return s;

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = s.charAt(i) - '0';
        }

        while (n > k) {
            int w = 0;

            for (int i = 0; i < n; i += k) {
                int end = Math.min(i + k, n);
                int sum = 0;

                for (int j = i; j < end; j++) {
                    sum += a[j];
                }

                if (sum >= 100) {
                    a[w++] = sum / 100;
                    a[w++] = (sum / 10) % 10;
                    a[w++] = sum % 10;
                } else if (sum >= 10) {
                    a[w++] = sum / 10;
                    a[w++] = sum % 10;
                } else {
                    a[w++] = sum;
                }
            }

            n = w;
        }

        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            res[i] = (char) (a[i] + '0');
        }

        return new String(res);
    }
}