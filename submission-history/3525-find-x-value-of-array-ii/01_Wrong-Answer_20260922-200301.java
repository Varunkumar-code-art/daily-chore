class Solution {
    public int[] resultArray(int[] nums, int k, int[][] queries) {

        int[] answer = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Update the array
            nums[index] = value;

            int product = 1;
            int count = 0;

            // Check every prefix starting from 'start'
            for (int i = start; i < nums.length; i++) {

                product = (product * nums[i]) % k;

                if (product == x) {
                    count++;
                }
            }

            answer[q] = count;
        }

        return answer;
    }
}