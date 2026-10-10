class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1 + k2;
        int n = nums1.length;
        int[] diff = new int[n];
        int max = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            max = Math.max(max, diff[i]);
        }

        if (k >= 0) {
            long total = 0;
            for (int d : diff) {
                total += d;
            }
            if (k >= total) {
                return 0;
            }
        }

        int left = 0, right = max;

        while (left < right) {
            int mid = left + (right - left) / 2;
            long operations = 0;

            for (int d : diff) {
                if (d > mid) {
                    operations += d - mid;
                }
            }

            if (operations <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        long sum = 0;
        long operations = 0;

        for (int d : diff) {
            if (d > left) {
                operations += d - left;
                d = left;
            }
            sum += (long) d * d;
        }

        long remaining = k - operations;

        for (int i = 0; i < n && remaining > 0; i++) {
            if (diff[i] == left && left > 0) {
                sum -= (long) left * left;
                sum += (long) (left - 1) * (left - 1);
                remaining--;
            }
        }

        return sum;
    }
}