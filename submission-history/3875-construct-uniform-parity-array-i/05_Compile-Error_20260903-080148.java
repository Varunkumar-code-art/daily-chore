import java.util.*;

class Solution {

    public boolean isPossible(int[] nums1) {
        int n = nums1.length;
        int[] nums2 = new int[n];

        return backtrack(nums1, nums2, 0);
    }

    public boolean backtrack(int[] nums1, int[] nums2, int index) {

        if (index == nums1.length) {
            return checkParity(nums2);
        }

        int n = nums1.length;
        nums2[index] = nums1[index];

        if (backtrack(nums1, nums2, index + 1)) {
            return true;
        }

        for (int j = 0; j < n; j++) {

            if (j == index) {
                continue;
            }

            nums2[index] = nums1[index] - nums1[j];

            if (backtrack(nums1, nums2, index + 1)) {
                return true;
            }
        }

        return false;
    }
    
    public boolean checkParity(int[] nums2) {

        boolean firstEven = nums2[0] % 2 == 0;

        for (int i = 1; i < nums2.length; i++) {

            boolean currentEven = nums2[i] % 2 == 0;

            if (currentEven != firstEven) {
                return false;
            }
        }

        return true;
    }
}