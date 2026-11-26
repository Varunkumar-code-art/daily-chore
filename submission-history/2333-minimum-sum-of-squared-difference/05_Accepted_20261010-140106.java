class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1 + k2;
        int[] freq = new int[100001];
        int max = 0;

        for (int i = 0; i < nums1.length; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            freq[d]++;
            max = Math.max(max, d);
        }

        for (int d = max; d > 0 && k > 0; d--) {
            long count = Math.min(k, freq[d]);
            freq[d] -= (int) count;
            freq[d - 1] += (int) count;
            k -= count;
        }

        long sum = 0;

        for (int d = 1; d <= 100000; d++) {
            sum += (long) d * d * freq[d];
        }

        return sum;
    }
}