class Solution {
public int sortPermutation(int[] nums) {
int ans = 0;

    for (int i = 0; i < nums.length; i++) {
        if (nums[i] != i) {
            ans |= nums[i] & i;
        }
    }

    return ans;
}
}
