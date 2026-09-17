class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n];
        
        int left = 0;
        int sum = 0;
        int shortest = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            
            if (sum == target) {
                int length = right - left + 1;
                
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    shortest = Math.min(shortest, best[left - 1] + length);
                }
            }
            
            if (right == 0) {
                best[right] = Integer.MAX_VALUE;
            } else {
                best[right] = best[right - 1];
            }
            
            if (sum == target) {
                best[right] = Math.min(best[right], right - left + 1);
            }
        }
        
        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }
}