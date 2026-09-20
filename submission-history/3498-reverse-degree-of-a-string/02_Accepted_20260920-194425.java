class Solution {
    public int reverseDegree(String s) {
        char[] arr = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += (26 - (arr[i] - 'a')) * (i + 1);
        }
        return sum;
    }
}