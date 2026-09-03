class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        boolean odd = true;
        boolean even = true;

        for (int i = 0; i < n; i++) {
            boolean canOdd = false;
            boolean canEven = false;

            if (nums1[i] % 2 == 0)
                canEven = true;
            else
                canOdd = true;

            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;

                int x = nums1[i] - nums1[j];

                if (x > 0) {
                    if (x % 2 == 0)
                        canEven = true;
                    else
                        canOdd = true;
                }
            }

            if (!canOdd)
                odd = false;

            if (!canEven)
                even = false;
        }

        return odd || even;
    }
}