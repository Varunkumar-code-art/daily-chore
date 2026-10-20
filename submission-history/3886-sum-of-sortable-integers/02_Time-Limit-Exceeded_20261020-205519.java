class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int sum = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            int[] arr = nums.clone();

            for (int i = 0; i < n; i += k) {
                int[] temp = new int[k];

                for (int j = 0; j < k; j++) {
                    temp[j] = nums[i + j];
                }

                boolean found = false;

                for (int r = 0; r < k; r++) {
                    boolean valid = true;

                    for (int j = 1; j < k; j++) {
                        if (temp[(j + r) % k] < temp[(j - 1 + r) % k]) {
                            valid = false;
                            break;
                        }
                    }

                    if (valid) {
                        for (int j = 0; j < k; j++) {
                            arr[i + j] = temp[(j + r) % k];
                        }
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    arr = null;
                    break;
                }
            }

            if (arr != null) {
                boolean sorted = true;

                for (int i = 1; i < n; i++) {
                    if (arr[i] < arr[i - 1]) {
                        sorted = false;
                        break;
                    }
                }

                if (sorted) sum += k;
            }
        }

        return sum;
    }
}