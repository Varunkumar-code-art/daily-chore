class Solution {
public String maxSumOfSquares(int num, int sum) {
if (sum > num * 9) {
return "";
}
    char[] result = new char[num];
    int i = 0;

    while (sum > 0 && i < num) {
        int digit = Math.min(9, sum);
        result[i] = (char) ('0' + digit);
        sum -= digit;
        i++;
    }

    while (i < num) {
        result[i] = '0';
        i++;
    }

    return new String(result);
}
}
