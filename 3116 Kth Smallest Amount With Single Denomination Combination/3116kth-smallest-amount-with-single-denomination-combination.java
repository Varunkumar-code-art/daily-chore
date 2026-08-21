class Solution {

    long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    long count(long x, int[] coins) {

        long count = 0;
        int n = coins.length;

        // Try every combination of coins
        for (int mask = 1; mask < (1 << n); mask++) {

            long value = 1;
            int bits = 0;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {
                    bits++;

                    value = lcm(value, coins[i]);

                    if (value > x) {
                        break;
                    }
                }
            }

            if (value > x)
                continue;

            if (bits % 2 == 1)
                count += x / value;
            else
                count -= x / value;
        }

        return count;
    }

    public long findKthSmallest(int[] coins, int k) {

        long low = 1;
        long high = (long) coins[0] * k;

        for (int coin : coins) {
            high = Math.min(high, (long) coin * k);
        }

        while (low < high) {

            long mid = low + (high - low) / 2;

            if (count(mid, coins) >= k)
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }
}