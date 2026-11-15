class Solution {
    public int numOfSubarrays(int[] arr) {
        int odd = 0;
        int even = 1;
        int parity = 0;
        long count = 0;

        for (int num : arr) {
            parity ^= num & 1;
            if (parity == 1) {
                count += even;
                odd++;
            } else {
                count += odd;
                even++;
            }
        }

        return (int) (count % 1000000007);
    }
}