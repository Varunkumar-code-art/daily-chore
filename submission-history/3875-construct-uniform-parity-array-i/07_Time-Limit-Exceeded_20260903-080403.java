class Solution {

    public boolean uniformArray(int[] nums1) {
        int[] nums2 = new int[nums1.length];
        return solve(nums1, nums2, 0);
    }

    public boolean solve(int[] nums1, int[] nums2, int index) {
        if (index == nums1.length) {
            return check(nums2);
        }

        nums2[index] = nums1[index];

        if (solve(nums1, nums2, index + 1)) {
            return true;
        }

        for (int j = 0; j < nums1.length; j++) {
            if (j != index) {
                nums2[index] = nums1[index] - nums1[j];

                if (solve(nums1, nums2, index + 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean check(int[] nums2) {
        boolean even = nums2[0] % 2 == 0;

        for (int i = 1; i < nums2.length; i++) {
            if ((nums2[i] % 2 == 0) != even) {
                return false;
            }
        }

        return true;
    }
}