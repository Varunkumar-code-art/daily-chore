class Solution {
public int minOperations(int[] nums) {
int n = nums.length;
int zero = -1;
boolean inc = true, dec = true;

    for (int i = 0; i < n; i++) {
        if (nums[i] == 0) {
            zero = i;
        }

        int next = nums[(i + 1) % n];

        if (next != (nums[i] + 1) % n) {
            inc = false;
        }

        if (next != (nums[i] - 1 + n) % n) {
            dec = false;
        }
    }

    int ans = Integer.MAX_VALUE;

    if (inc) {
        ans = Math.min(ans, zero);
        ans = Math.min(ans, n - zero + 2);
    }

    if (dec) {
        ans = Math.min(ans, zero + 2);
        ans = Math.min(ans, n - zero);
    }

    return ans == Integer.MAX_VALUE ? -1 : ans;
}

}
