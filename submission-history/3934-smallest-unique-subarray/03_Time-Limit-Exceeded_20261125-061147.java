import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        for (int len = 1; len <= n; len++) {
            Map<String, Integer> seen = new HashMap<>();
            for (int i = 0; i + len <= n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int k = i; k < i + len; k++) {
                    sb.append(nums[k]).append(',');
                }
                seen.merge(sb.toString(), 1, Integer::sum);
            }
            for (int count : seen.values()) {
                if (count == 1) return len;
            }
        }
        return n;
    }
}