class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int sum1 = 0;

            for (int j = i; j < n; j++) {
                sum1 += arr[j];

                if (sum1 == target) {
                    int firstLength = j - i + 1;

                    for (int k = j + 1; k < n; k++) {
                        int sum2 = 0;

                        for (int l = k; l < n; l++) {
                            sum2 += arr[l];

                            if (sum2 == target) {
                                int secondLength = l - k + 1;
                                answer = Math.min(answer, firstLength + secondLength);
                            }
                        }
                    }
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}