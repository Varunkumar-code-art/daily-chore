class Solution {
    public int minMoves2(int[] nums) {
        int n = nums.length;
        int k = n >> 1;
        int lo = 0;
        int hi = n - 1;

        while (lo < hi) {
            int a = nums[lo];
            int b = nums[(lo + hi) >>> 1];
            int c = nums[hi];
            int pivot = a < b ? (b < c ? b : (a < c ? c : a)) : (a < c ? a : (b < c ? c : b));

            int i = lo;
            int j = hi;

            while (i <= j) {
                while (nums[i] < pivot) i++;
                while (nums[j] > pivot) j--;
                if (i <= j) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i++;
                    j--;
                }
            }

            if (k <= j) {
                hi = j;
            } else if (k >= i) {
                lo = i;
            } else {
                break;
            }
        }

        long median = nums[k];
        long left = 0;
        long right = 0;

        for (int i = 0; i < k; i++) {
            left += nums[i];
        }
        for (int i = k + 1; i < n; i++) {
            right += nums[i];
        }

        return (int) ((right - median * (n - k - 1)) + (median * k - left));
    }
}