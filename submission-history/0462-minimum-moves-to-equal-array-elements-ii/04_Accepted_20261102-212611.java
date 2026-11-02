class Solution {
    private final Random random = new Random();

    public int minMoves2(int[] nums) {
        int median = findKth(nums, nums.length / 2);

        long moves = 0;
        for (int num : nums) {
            moves += Math.abs((long) num - median);
        }

        return (int) moves;
    }

    private int findKth(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int pivot = nums[left + random.nextInt(right - left + 1)];

            int lt = left;
            int i = left;
            int gt = right;

            while (i <= gt) {
                if (nums[i] < pivot) {
                    swap(nums, lt++, i++);
                } else if (nums[i] > pivot) {
                    swap(nums, i, gt--);
                } else {
                    i++;
                }
            }

            if (k < lt) {
                right = lt - 1;
            } else if (k > gt) {
                left = gt + 1;
            } else {
                return pivot;
            }
        }

        return nums[k];
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}