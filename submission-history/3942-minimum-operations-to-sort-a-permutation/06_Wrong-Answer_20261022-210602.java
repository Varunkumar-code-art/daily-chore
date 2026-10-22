class Solution {
public int minOperations(int[] nums) {
int n = nums.length;
    int left = 0;
    int right = 0;

    for (int i = 0; i < n; i++) {
        if (nums[i] != i) {
            break;
        }
        left++;
    }

    if (left == n) {
        return 0;
    }

    for (int i = 0; i < n; i++) {
        if (nums[(n - i) % n] != i) {
            break;
        }
        right++;
    }

    if (right == n) {
        return 0;
    }

    int best = Integer.MAX_VALUE;

    for (int i = 0; i < n; i++) {
        if (nums[i] == 0) {
            int forward = (n - i) % n;
            if (nums[(i + 1) % n] == 1) {
                best = Math.min(best, forward);
            }
        }
    }

    int[] reversed = new int[n];
    for (int i = 0; i < n; i++) {
        reversed[i] = nums[n - 1 - i];
    }

    for (int i = 0; i < n; i++) {
        if (reversed[i] == 0) {
            int shift = (n - i) % n;
            if (reversed[(i + 1) % n] == 1) {
                best = Math.min(best, shift + 1);
            }
        }
    }

    return best == Integer.MAX_VALUE ? -1 : best;
}
}
