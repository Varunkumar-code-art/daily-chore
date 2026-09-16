class Solution {

    long count = 0;

    public int numberOfSets(int n, int k) {

        int[][] segments = new int[n * n][2];
        int size = 0;

        for (int start = 0; start < n; start++) {
            for (int end = start + 1; end < n; end++) {
                segments[size][0] = start;
                segments[size][1] = end;
                size++;
            }
        }

        chooseSegments(segments, size, k, 0, 0, -1);

        return (int)(count % 1000000007);
    }

    void chooseSegments(int[][] segments, int size, int k,
                        int chosen, int index, int lastEnd) {

        if (chosen == k) {
            count++;
            return;
        }

        
        for (int i = index; i < size; i++) {

            int start = segments[i][0];
            int end = segments[i][1];

           
            if (start >= lastEnd) {

                chooseSegments(
                    segments,
                    size,
                    k,
                    chosen + 1,
                    i + 1,
                    end
                );
            }
        }
    }
}