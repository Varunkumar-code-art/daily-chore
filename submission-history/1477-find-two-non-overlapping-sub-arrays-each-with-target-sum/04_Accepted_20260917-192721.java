class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            best[i] = Integer.MAX_VALUE;
        }

        int left = 0;
        int sum = 0;
        int answer = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {
                int length = right - left + 1;

                if (best[left] != Integer.MAX_VALUE) {
                    answer = Math.min(answer, length + best[left]);
                }

                best[right + 1] = Math.min(best[right], length);
            } else {
                best[right + 1] = best[right];
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}