import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        Comparator<Integer> asc = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : Integer.compare(a, b);
        PriorityQueue<Integer> low = new PriorityQueue<>(asc.reversed());
        PriorityQueue<Integer> high = new PriorityQueue<>(asc);
        byte[] side = new byte[n];               // 0 = low, 1 = high
        int lowSize = 0, highSize = 0;
        long sumLow = 0, sumHigh = 0;
        int target = (x + 1) / 2;
        long[] cost = new long[n - x + 1];

        for (int r = 0; r < n; r++) {
            int s = r - x + 1;                   // window start
            if (s >= 1) {
                int o = s - 1;                   // outgoing index (lazy removal)
                if (side[o] == 0) { lowSize--; sumLow -= nums[o]; }
                else { highSize--; sumHigh -= nums[o]; }
            }
            while (!high.isEmpty() && high.peek() < s) high.poll();
            while (!low.isEmpty() && low.peek() < s) low.poll();

            if (highSize > 0 && asc.compare(r, high.peek()) > 0) {
                high.add(r); side[r] = 1; highSize++; sumHigh += nums[r];
            } else {
                low.add(r); side[r] = 0; lowSize++; sumLow += nums[r];
            }

            while (lowSize > target) {
                while (low.peek() < s) low.poll();
                int t = low.poll();
                side[t] = 1; lowSize--; sumLow -= nums[t]; highSize++; sumHigh += nums[t]; high.add(t);
            }
            while (lowSize < target && highSize > 0) {
                while (high.peek() < s) high.poll();
                int t = high.poll();
                side[t] = 0; highSize--; sumHigh -= nums[t]; lowSize++; sumLow += nums[t]; low.add(t);
            }

            if (r >= x - 1) {
                while (low.peek() < s) low.poll();
                long m = nums[low.peek()];
                cost[s] = m * lowSize - sumLow + sumHigh - m * highSize;
            }
        }

        long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];           // j = 0 -> all zeros
        long[] cur = new long[n + 1];
        for (int j = 1; j <= k; j++) {
            cur[0] = INF;
            for (int i = 1; i <= n; i++) {
                cur[i] = cur[i - 1];
                if (i >= x && prev[i - x] < INF)
                    cur[i] = Math.min(cur[i], prev[i - x] + cost[i - x]);
            }
            long[] tmp = prev; prev = cur; cur = tmp;
        }
        return prev[n];
    }
}