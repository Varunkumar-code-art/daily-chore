class Solution {
    public int sumCounts(int[] nums) {
        int n = nums.length;
        long ans = 0;
        int mod = 1000000007;

        for (int i = 0; i < n; i++) {
            java.util.HashSet<Integer> set = new java.util.HashSet<>();

            for (int j = i; j < n; j++) {
                set.add(nums[j]);
                long count = set.size();
                ans = (ans + count * count) % mod;
            }
        }

        return (int) ans;
    }
}