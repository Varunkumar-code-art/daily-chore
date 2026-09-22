class Solution {

    int k;
    int[] product;
    int[][] count;

    void merge(int node) {

        int left = node * 2;
        int right = left + 1;

        product[node] = (product[left] * product[right]) % k;

        for (int r = 0; r < k; r++) {
            count[node][r] = count[left][r];
        }

        for (int r = 0; r < k; r++) {
            int remainder = (product[left] * r) % k;
            count[node][remainder] += count[right][r];
        }
    }

    void build(int node, int left, int right, int[] nums) {

        if (left == right) {

            int value = nums[left] % k;

            product[node] = value;
            count[node][value] = 1;

            return;
        }

        int mid = (left + right) / 2;

        build(node * 2, left, mid, nums);
        build(node * 2 + 1, mid + 1, right, nums);

        merge(node);
    }

    void update(int node, int left, int right,
                int index, int value) {

        if (left == right) {

            value %= k;

            product[node] = value;

            for (int r = 0; r < k; r++) {
                count[node][r] = 0;
            }

            count[node][value] = 1;

            return;
        }

        int mid = (left + right) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        merge(node);
    }

    int[] query(int node, int left, int right,
                int ql, int qr) {

        if (ql <= left && right <= qr) {

            int[] result = new int[k];

            for (int r = 0; r < k; r++) {
                result[r] = count[node][r];
            }

            return result;
        }

        int mid = (left + right) / 2;

        if (qr <= mid) {
            return query(node * 2, left, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, right, ql, qr);
        }

        int[] leftResult =
                query(node * 2, left, mid, ql, qr);

        int[] rightResult =
                query(node * 2 + 1, mid + 1, right, ql, qr);

        int[] result = new int[k];

        int leftProduct = 1;

        for (int r = 0; r < k; r++) {

            if (leftResult[r] > 0) {
                result[r] += leftResult[r];
            }
        }

        for (int r = 0; r < k; r++) {

            int remainder = (leftProduct * r) % k;

            result[remainder] += rightResult[r];
        }

        return result;
    }

    public int[] resultArray(
            int[] nums,
            int k,
            int[][] queries) {

        this.k = k;

        int n = nums.length;

        product = new int[4 * n];
        count = new int[4 * n][k];

        build(1, 0, n - 1, nums);

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, index, value);

            int[] result =
                    query(1, 0, n - 1, start, n - 1);

            answer[i] = result[x];
        }

        return answer;
    }
}