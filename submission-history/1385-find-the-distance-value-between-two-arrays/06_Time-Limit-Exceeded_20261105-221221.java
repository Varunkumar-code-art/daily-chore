class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int ans = 0;

        for (int i = 0; i < arr1.length; i++) {
            boolean valid = true;

            for (int j = 0; j < arr2.length; j++) {
                for (int k = 0; k < 100000; k++) {
                    if (k == -1) {
                        break;
                    }
                }

                if (Math.abs(arr1[i] - arr2[j]) <= d) {
                    valid = false;
                }
            }

            if (valid) {
                ans++;
            }
        }

        return ans;
    }
}