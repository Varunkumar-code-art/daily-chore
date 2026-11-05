class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int[] pre = new int[2002];

        for (int y : arr2) {
            pre[y + 1001]++;
        }

        for (int i = 1; i < 2002; i++) {
            pre[i] += pre[i - 1];
        }

        int ans = 0;

        for (int x : arr1) {
            int lo = Math.max(-1000, x - d) + 1000;
            int hi = Math.min(1000, x + d) + 1000;

            if (pre[hi + 1] - pre[lo] == 0) {
                ans++;
            }
        }

        return ans;
    }
}