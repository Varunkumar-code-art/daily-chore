class Solution {
    public int numOfSubarrays(int[] arr) {
        int n = arr.length;
        int parity = 0;
        int odd = 0;

        for (int i = 0; i < n; i++) {
            parity ^= arr[i] & 1;
            odd += parity;
        }

        long even = n + 1 - odd;
        return (int) ((long) odd * even % 1000000007);
    }
}