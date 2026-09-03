class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE, minEven = Integer.MAX_VALUE;
        int countOdd = 0, countEven = 0;
        
        for (int num : nums1) {
            if (num % 2 == 0) {
                countEven++;
                minEven = Math.min(minEven, num);
            } else {
                countOdd++;
                minOdd = Math.min(minOdd, num);
            }
        }
        
        if (countOdd == 0 || countEven == 0) {
            return true;
        }
        
        return minEven > minOdd;
    }
}