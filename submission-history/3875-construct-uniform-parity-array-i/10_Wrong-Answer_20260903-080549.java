class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;

        boolean hasOdd = false;
        boolean hasEven = false;

        for (int num : nums1) {
            if (num % 2 == 0) {
                hasEven = true;
                minEven = Math.min(minEven, num);
            } else {
                hasOdd = true;
                minOdd = Math.min(minOdd, num);
            }
        }

        if (!hasOdd || !hasEven) {
            return true;
        }

        return minEven > minOdd;
    }
}