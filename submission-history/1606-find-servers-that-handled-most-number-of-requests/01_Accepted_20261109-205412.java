class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] count = new int[k];
        TreeSet<Integer> free = new TreeSet<>();
        for (int i = 0; i < k; i++) free.add(i);
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i])
                free.add((int) busy.poll()[1]);

            if (free.isEmpty()) continue;

            Integer s = free.ceiling(i % k);
            if (s == null) s = free.first();
            free.remove(s);
            count[s]++;
            busy.offer(new long[]{(long) arrival[i] + load[i], s});
        }

        int max = Arrays.stream(count).max().getAsInt();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) if (count[i] == max) res.add(i);
        return res;
    }
}