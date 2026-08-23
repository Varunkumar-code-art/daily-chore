class Solution 
{
    public int thirdMax(int[] arr) 
    {
        int first = Long.MIN_VALUE;
        int second = Long.MIN_VALUE;
        int third=Long.MIN_VALUE;
        for(int i = 0;i < arr.length;i++)
        {
            if(arr[i] > first)
            {
                third=second;
                second=first;
                first=arr[i];
            }
            else if(arr[i]>second && arr[i]<first)
            {
                second = arr[i];
            }
            else if(arr[i]>third && arr[i]<second)
            {
                third=arr[i];
            }
        }
        if(third == Integer.MIN_VALUE)
        {
            return first;
        }
        return third;
    }
}