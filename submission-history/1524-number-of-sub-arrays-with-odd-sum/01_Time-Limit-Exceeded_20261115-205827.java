class Solution {
    public int numOfSubarrays(int[] arr) {
        int mod = 1000000007;
        int n = arr.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                if (sum % 2 == 1) {
                    count = (count + 1) % mod;
                }
            }
        }

        return count;
    }
}