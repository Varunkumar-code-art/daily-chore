class Solution {
    public int sumOfSortableIntegers(int[] nums) {
        int n = nums.length;
        int sum = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            int[] arr = nums.clone();

            for (int i = 0; i < n; i += k) {
                int[] temp = new int[k];

                for (int j = 0; j < k; j++) {
                    temp[j] = arr[i + j];
                }

                boolean found = false;

                for (int r = 0; r < k; r++) {
                    boolean sorted = true;

                    for (int j = 1; j < k; j++) {
                        if (temp[(j + r) % k] < temp[(j - 1 + r) % k]) {
                            sorted = false;
                            break;
                        }
                    }

                    if (sorted) {
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
                int[] rotated = nums.clone();

                for (int i = 0; i < n; i += k) {
                    int[] temp = new int[k];

                    for (int j = 0; j < k; j++) {
                        temp[j] = nums[i + j];
                    }

                    for (int r = 0; r < k; r++) {
                        boolean sorted = true;

                        for (int j = 1; j < k; j++) {
                            if (temp[(j + r) % k] < temp[(j - 1 + r) % k]) {
                                sorted = false;
                                break;
                            }
                        }

                        if (sorted) {
                            for (int j = 0; j < k; j++) {
                                rotated[i + j] = temp[(j + r) % k];
                            }
                            break;
                        }
                    }
                }

                boolean sorted = true;

                for (int i = 1; i < n; i++) {
                    if (rotated[i] < rotated[i - 1]) {
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