class Solution {
public int sortPermutation(int[] nums) {
int n = nums.length;

    boolean sorted = true;

    for (int i = 0; i < n; i++) {
        if (nums[i] != i) {
            sorted = false;
            break;
        }
    }

    if (sorted) {
        return 0;
    }

    for (int k = n - 1; k >= 0; k--) {
        int[] arr = nums.clone();
        boolean change = true;

        while (change) {
            change = false;

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if ((arr[i] & arr[j]) == k && arr[i] > arr[j]) {
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        change = true;
                    }
                }
            }
        }

        boolean flag = true;

        for (int i = 0; i < n; i++) {
            if (arr[i] != i) {
                flag = false;
                break;
            }
        }

        if (flag) {
            return k;
        }
    }

    return 0;
}

}
