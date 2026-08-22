class Solution 
{
    public int maxSubArray(int[] arr) 
    {
        int max_sum=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++)
        {
            int sum=0;
            for(int j=i;j<arr.length;j++)
            {
                sum=sum+arr[j];
                if(sum>max_sum)
                {
                    max_sum=sum;
                }
            }
        }
        return max_sum;
    }
}