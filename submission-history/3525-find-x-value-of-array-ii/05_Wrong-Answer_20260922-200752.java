class Solution {

    int k;
    int size;
    int[] product;
    int[][] count;

    void pull(int p) {

        int left = p * 2;
        int right = left + 1;

        product[p] = (product[left] * product[right]) % k;

        for (int r = 0; r < k; r++) {
            count[p][r] = count[left][r];
        }

        for (int r = 0; r < k; r++) {
            int newRemainder = (product[left] * r) % k;
            count[p][newRemainder] += count[right][r];
        }
    }

    void update(int index, int value) {

        int p = size + index;

        value %= k;

        product[p] = value;

        for (int r = 0; r < k; r++) {
            count[p][r] = 0;
        }

        count[p][value] = 1;

        p /= 2;

        while (p > 0) {
            pull(p);
            p /= 2;
        }
    }

    int[] query(int left, int right) {

        left += size;
        right += size;

        int leftProduct = 1 % k;
        int rightProduct = 1 % k;

        int[] leftCount = new int[k];
        int[] rightCount = new int[k];

        while (left <= right) {

            if ((left & 1) == 1) {

                int oldProduct = leftProduct;

                for (int r = 0; r < k; r++) {
                    int newRemainder = (oldProduct * r) % k;
                    leftCount[newRemainder] += count[left][r];
                }

                leftProduct = (oldProduct * product[left]) % k;

                left++;
            }

            if ((right & 1) == 0) {

                int oldProduct = product[right];

                int[] temp = new int[k];

                for (int r = 0; r < k; r++) {
                    temp[r] = count[right][r];
                }

                for (int r = 0; r < k; r++) {
                    int newRemainder =
                            (product[right] * r) % k;

                    temp[newRemainder] =
                            count[right][r];
                }

                for (int r = 0; r < k; r++) {

                    int newRemainder =
                            (product[right] * r) % k;

                    rightCount[newRemainder] +=
                            rightCount[r];
                }

                int oldRightProduct = product[right];

                int[] newRightCount = new int[k];

                for (int r = 0; r < k; r++) {
                    newRightCount[r] = count[right][r];
                }

                for (int r = 0; r < k; r++) {
                    int newRemainder =
                            (oldRightProduct * r) % k;

                    rightCount[newRemainder] += 0;
                }

                rightProduct =
                        (product[right] * rightProduct) % k;

                right--;
            }

            left /= 2;
            right /= 2;
        }

        int[] answer = new int[k];

        for (int r = 0; r < k; r++) {
            answer[r] = leftCount[r];
        }

        for (int r = 0; r < k; r++) {
            int newRemainder =
                    (leftProduct * r) % k;

            answer[newRemainder] += rightCount[r];
        }

        return answer;
    }

    public int[] resultArray(
            int[] nums,
            int k,
            int[][] queries) {

        this.k = k;

        int n = nums.length;

        size = 1;

        while (size < n) {
            size *= 2;
        }

        product = new int[size * 2];
        count = new int[size * 2][k];

        for (int i = 0; i < n; i++) {
            int value = nums[i] % k;

            product[size + i] = value;
            count[size + i][value] = 1;
        }

        for (int i = size - 1; i > 0; i--) {
            pull(i);
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(index, value);

            int[] result = query(start, n - 1);

            answer[i] = result[x];
        }

        return answer;
    }
}