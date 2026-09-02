class Solution {
    public boolean constructUniformParityArray(int[] nums1) {
        int n = nums1.length;

        for (int target = 0; target <= 1; target++) {
            boolean possible = true;

            for (int i = 0; i < n; i++) {
                boolean found = false;

                if (nums1[i] % 2 == target) {
                    found = true;
                }

                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        int value = nums1[i] - nums1[j];

                        if (value % 2 == target) {
                            found = true;
                            break;
                        }
                    }
                }

                if (!found) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                return true;
            }
        }

        return false;
    }
}