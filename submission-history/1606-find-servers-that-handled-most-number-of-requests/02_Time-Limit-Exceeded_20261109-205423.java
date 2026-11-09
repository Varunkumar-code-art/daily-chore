class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        long[] endTime = new long[k];
        int[] count = new int[k];

        for (int i = 0; i < arrival.length; i++) {
            for (int j = 0; j < k; j++) {
                int s = (i + j) % k;
                if (endTime[s] <= arrival[i]) {
                    endTime[s] = (long) arrival[i] + load[i];
                    count[s]++;
                    break;
                }
            }
        }

        int max = Arrays.stream(count).max().getAsInt();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) if (count[i] == max) res.add(i);
        return res;
    }
}