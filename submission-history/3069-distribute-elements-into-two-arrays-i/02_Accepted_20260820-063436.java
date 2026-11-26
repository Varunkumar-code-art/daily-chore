class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int i1 = 0, i2 = n - 1;

        result[i1++] = nums[0];
        result[i2--] = nums[1];

        int last1 = nums[0], last2 = nums[1];

        for (int i = 2; i < n; i++) {
            if (last1 > last2) {
                result[i1++] = nums[i];
                last1 = nums[i];
            } else {
                result[i2--] = nums[i];
                last2 = nums[i];
            }
        }

        int[] arr2 = new int[n - i1];
        for (int j = 0; j < arr2.length; j++) {
            arr2[j] = result[n - 1 - j];
        }
        System.arraycopy(arr2, 0, result, i1, arr2.length);

        return result;
    }
}