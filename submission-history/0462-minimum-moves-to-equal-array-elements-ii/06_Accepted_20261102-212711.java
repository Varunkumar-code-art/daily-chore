class Solution {
    public int minMoves2(int[] nums) {
        int n = nums.length;
        int k = n / 2;
        int lo = 0;
        int hi = n - 1;

        while (lo < hi) {
            int pivot = nums[(lo + hi) >>> 1];
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

        int median = nums[k];
        long moves = 0;
        for (int i = 0; i < n; i++) {
            moves += Math.abs((long) nums[i] - median);
        }

        return (int) moves;
    }
}