class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                int sum1 = prefix[j + 1] - prefix[i];

                if (sum1 == target) {
                    int firstLength = j - i + 1;

                    for (int k = j + 1; k < n; k++) {
                        for (int l = k; l < n; l++) {

                            int sum2 = prefix[l + 1] - prefix[k];

                            if (sum2 == target) {
                                int secondLength = l - k + 1;

                                answer = Math.min(
                                    answer,
                                    firstLength + secondLength
                                );
                            }
                        }
                    }
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}