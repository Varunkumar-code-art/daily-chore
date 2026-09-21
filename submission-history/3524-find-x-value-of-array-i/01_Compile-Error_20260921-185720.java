class Solution {
    public int[] resultArray(int[] nums, int k) {

        int n = nums.length;
        int[] result = new int[k];

        for (int i = 0; i < n; i++) {

            long product = 1;

            for (int j = i; j < n; j++) {

                product = (product * nums[j]) % k;

                int remainder = (int) product;

                result[remainder]++;
            }
        }

        return result;
    }
}