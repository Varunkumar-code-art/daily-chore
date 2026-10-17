
class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[] arr = nums.clone();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = digitSum(arr[i]);
                int b = digitSum(arr[j]);

                if (a > b || (a == b && arr[i] > arr[j])) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        int swaps = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (visited[i] || nums[i] == arr[i]) {
                continue;
            }

            int j = i;
            int cycle = 0;

            while (!visited[j]) {
                visited[j] = true;
                int k = 0;

                while (nums[k] != arr[j]) {
                    k++;
                }

                j = k;
                cycle++;
            }

            swaps += cycle - 1;
        }

        return swaps;
    }

    private int digitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }
}
