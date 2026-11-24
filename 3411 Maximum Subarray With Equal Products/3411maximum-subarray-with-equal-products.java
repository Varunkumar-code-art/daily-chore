import java.math.BigInteger;

class Solution {
    private int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }

    public int maxLength(int[] nums) {
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                BigInteger prod = BigInteger.ONE;
                int g = 0, l = 1;
                for (int k = i; k <= j; k++) {
                    prod = prod.multiply(BigInteger.valueOf(nums[k]));
                    g = gcd(g, nums[k]);
                    l = l / gcd(l, nums[k]) * nums[k];
                }
                if (prod.equals(BigInteger.valueOf((long) l * g))) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
}