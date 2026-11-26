
class Solution {
    long[][] c = new long[1001][1001];
    long mod = 1000000007;

    public int numOfWays(int[] nums) {
        for (int i = 0; i <= nums.length; i++) {
            c[i][0] = c[i][i] = 1;
            for (int j = 1; j < i; j++)
                c[i][j] = (c[i-1][j-1] + c[i-1][j]) % mod;
        }
        return (int)((solve(nums) - 1 + mod) % mod);
    }

    long solve(int[] a) {
        if (a.length <= 2) return 1;
        java.util.List<Integer> l = new java.util.ArrayList<>();
        java.util.List<Integer> r = new java.util.ArrayList<>();
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[0]) l.add(a[i]);
            else r.add(a[i]);
        }
        int[] left = l.stream().mapToInt(Integer::intValue).toArray();
        int[] right = r.stream().mapToInt(Integer::intValue).toArray();
        return c[a.length-1][left.length] * solve(left) % mod * solve(right) % mod;
    }
}
