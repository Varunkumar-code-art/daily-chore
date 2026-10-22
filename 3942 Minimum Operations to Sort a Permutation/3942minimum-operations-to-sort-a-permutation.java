class Solution {
public int minOperations(int[] nums) {
int n = nums.length;
int zero = 0;

    for (int i = 0; i < n; i++) {
        if (nums[i] == 0) {
            zero = i;
            break;
        }
    }

    boolean inc = true;
    boolean dec = true;
    int right = zero;
    int left = zero;

    for (int i = 0; i < n; i++) {
        if (nums[right] != i) {
            inc = false;
        }

        if (nums[left] != i) {
            dec = false;
        }

        right++;
        if (right == n) {
            right = 0;
        }

        left--;
        if (left < 0) {
            left = n - 1;
        }

        if (!inc && !dec) {
            return -1;
        }
    }

    int ans = Integer.MAX_VALUE;

    if (inc) {
        ans = Math.min(zero, n - zero + 2);
    }

    if (dec) {
        ans = Math.min(ans, Math.min(zero + 2, n - zero));
    }

    return ans == Integer.MAX_VALUE ? -1 : ans;
}

}
