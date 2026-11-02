class Solution {
    private final Random random = new Random();

    public int minMoves2(int[] nums) {
        int median = select(nums, nums.length / 2);

        long moves = 0;
        for (int num : nums) {
            moves += Math.abs((long) num - median);
        }

        return (int) moves;
    }

    private int select(int[] nums, int k) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo < hi) {
            int pivot = nums[lo + random.nextInt(hi - lo + 1)];
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

        return nums[k];
    }
}