class Solution {
    private static final int[] PRIMES = {2, 3, 5, 7};

    public int maxLength(int[] nums) {
        int[] cnt = new int[4];
        int left = 0, ans = 2;
        for (int right = 0; right < nums.length; right++) {
            while (!canAdd(nums[right], cnt)) {
                update(nums[left++], cnt, -1);
            }
            update(nums[right], cnt, 1);
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    private boolean canAdd(int x, int[] cnt) {
        for (int p = 0; p < 4; p++)
            if (x % PRIMES[p] == 0 && cnt[p] > 0) return false;
        return true;
    }

    private void update(int x, int[] cnt, int d) {
        for (int p = 0; p < 4; p++)
            if (x % PRIMES[p] == 0) cnt[p] += d;
    }
}