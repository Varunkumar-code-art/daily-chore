class Solution
{
    public long[] resultArray(int[] nums, int k)
    {

        int n = nums.length;
        long[] result = new long[k];

        for (int i = 0; i < n; i++) 
        {

            long product = 1;

            for (int j = i; j < n; j++) 
            {

                product = (product * nums[j]) % k;

                int remainder = (int) product;

                result[remainder]++;
            }
        }

        return result;
    }
}