class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int start = 0;

        while (start < n) {
            int end = start;

            while (end + 1 < n && arr[end + 1][0] - arr[end][0] <= limit) {
                end++;
            }

            int[] values = new int[end - start + 1];
            int[] indexes = new int[end - start + 1];

            for (int i = start; i <= end; i++) {
                values[i - start] = arr[i][0];
                indexes[i - start] = arr[i][1];
            }

            Arrays.sort(indexes);

            for (int i = 0; i < values.length; i++) {
                nums[indexes[i]] = values[i];
            }

            start = end + 1;
        }

        return nums;
    }
}
