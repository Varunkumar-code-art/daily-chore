class Solution {
    private static final int SHIFT = 18;
    private static final long MASK = (1L << SHIFT) - 1;

    private static class MinHeap {
        long[] data;
        int size;

        MinHeap(int capacity) {
            data = new long[capacity + 1];
        }

        boolean isEmpty() {
            return size == 0;
        }

        long peek() {
            return data[1];
        }

        void push(long value) {
            int i = ++size;
            while (i > 1 && data[i >> 1] > value) {
                data[i] = data[i >> 1];
                i >>= 1;
            }
            data[i] = value;
        }

        long pop() {
            long top = data[1];
            long last = data[size--];
            int i = 1;
            while ((i << 1) <= size) {
                int child = i << 1;
                if (child < size && data[child + 1] < data[child]) child++;
                if (data[child] >= last) break;
                data[i] = data[child];
                i = child;
            }
            data[i] = last;
            return top;
        }
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] result = new int[m];

        MinHeap available = new MinHeap(n);
        MinHeap working = new MinHeap(n);

        for (int i = 0; i < n; i++) {
            available.push(((long) servers[i] << SHIFT) | i);
        }

        long currentTime = 0;

        for (int i = 0; i < m; i++) {
            currentTime = Math.max(currentTime, i);

            if (available.isEmpty()) {
                currentTime = Math.max(currentTime, working.peek() >> SHIFT);
            }

            while (!working.isEmpty() && (working.peek() >> SHIFT) <= currentTime) {
                int index = (int) (working.pop() & MASK);
                available.push(((long) servers[index] << SHIFT) | index);
            }

            int index = (int) (available.pop() & MASK);
            result[i] = index;
            working.push(((currentTime + tasks[i]) << SHIFT) | index);
        }

        return result;
    }
}