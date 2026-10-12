class Solution {
public int maxSumSquares(int[] nums, int k) {
int mod = 1000000007;
long ans = 0;
    for (int i = 0; i < k; i++) {
        long max = 0;
        int index = -1;

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] > max) {
                max = nums[j];
                index = j;
            }
        }

        ans = (ans + max * max) % mod;
        nums[index] = 0;
    }

    return (int) ans;
}
}