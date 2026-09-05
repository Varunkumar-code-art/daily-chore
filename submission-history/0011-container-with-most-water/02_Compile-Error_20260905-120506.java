class Solution {
    public int maxArea(int[] arr) {
        int maxwater=0;
        int left=0;
        int right=length-1;
        while(left<right)
        {
            int width=right-left;
            int height=Math.min(arr[left],arr[right]);
            int area=width*height;
            maxwater=Math.max(area,maxwater);
            if(arr[left]<arr[right])
            {
                left++;
            }
            else
            {
                right--;
            }
        }
    }
    return maxwater;
    }
}