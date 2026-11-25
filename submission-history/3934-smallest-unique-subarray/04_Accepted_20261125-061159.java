import java.util.*;

class Solution {
    private static final long MOD_A = 1_000_000_007L;
    private static final long MOD_B = 998_244_353L;
    private static final long BASE_A = 100_003L;
    private static final long BASE_B = 131_071L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        long[] preA = new long[n + 1];
        long[] preB = new long[n + 1];
        long[] powA = new long[n + 1];
        long[] powB = new long[n + 1];
        powA[0] = 1;
        powB[0] = 1;
        for (int i = 0; i < n; i++) {
            preA[i + 1] = (preA[i] * BASE_A + nums[i]) % MOD_A;
            preB[i + 1] = (preB[i] * BASE_B + nums[i]) % MOD_B;
            powA[i + 1] = powA[i] * BASE_A % MOD_A;
            powB[i + 1] = powB[i] * BASE_B % MOD_B;
        }

        int low = 1, high = n;
        while (low < high) {
            int mid = (low + high) / 2;
            if (hasUnique(n, mid, preA, preB, powA, powB)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean hasUnique(int n, int len, long[] preA, long[] preB, long[] powA, long[] powB) {
        Map<Long, Integer> seen = new HashMap<>();
        for (int i = 0; i + len <= n; i++) {
            long a = (preA[i + len] - preA[i] * powA[len] % MOD_A + MOD_A) % MOD_A;
            long b = (preB[i + len] - preB[i] * powB[len] % MOD_B + MOD_B) % MOD_B;
            long key = a * MOD_B + b;
            seen.merge(key, 1, Integer::sum);
        }
        for (int count : seen.values()) {
            if (count == 1) return true;
        }
        return false;
    }
}