class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length, ans = 2;
        for (int i = 0; i < n; i++) {
            int used = 0;
            for (int j = i; j < n; j++) {
                int m = mask(nums[j]);
                if ((used & m) != 0) break;
                used |= m;
                ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }

    private int mask(int x) {
        int m = 0;
        if (x % 2 == 0) m |= 1;
        if (x % 3 == 0) m |= 2;
        if (x % 5 == 0) m |= 4;
        if (x % 7 == 0) m |= 8;
        return m;
    }
}