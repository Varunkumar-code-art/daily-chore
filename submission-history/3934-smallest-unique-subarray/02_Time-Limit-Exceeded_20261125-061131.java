import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int low = 1, high = nums.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (hasUnique(nums, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean hasUnique(int[] nums, int len) {
        Map<String, Integer> seen = new HashMap<>();
        for (int i = 0; i + len <= nums.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int k = i; k < i + len; k++) {
                sb.append(nums[k]).append(',');
            }
            seen.merge(sb.toString(), 1, Integer::sum);
        }
        for (int count : seen.values()) {
            if (count == 1) return true;
        }
        return false;
    }
}