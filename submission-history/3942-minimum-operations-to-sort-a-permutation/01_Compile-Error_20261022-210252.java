import java.util.*;

class Solution {
public int minOperations(int[] nums) {
int n = nums.length;

    int[] sorted = new int[n];
    for (int i = 0; i < n; i++) {
        sorted[i] = i;
    }

    String target = Arrays.toString(sorted);
    String start = Arrays.toString(nums);

    if (start.equals(target)) {
        return 0;
    }

    Queue<int[]> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();

    queue.offer(nums.clone());
    visited.add(start);

    int steps = 0;

    while (!queue.isEmpty()) {
        int size = queue.size();
        steps++;

        for (int i = 0; i < size; i++) {
            int[] current = queue.poll();

            int[] reversed = current.clone();

            for (int j = 0; j < n / 2; j++) {
                int temp = reversed[j];
                reversed[j] = reversed[n - 1 - j];
                reversed[n - 1 - j] = temp;
            }

            String reverseKey = Arrays.toString(reversed);

            if (reverseKey.equals(target)) {
                return steps;
            }

            if (visited.add(reverseKey)) {
                queue.offer(reversed);
            }

            int[] rotated = new int[n];

            for (int j = 0; j < n - 1; j++) {
                rotated[j] = current[j + 1];
            }

            rotated[n - 1] = current[0];

            String rotateKey = Arrays.toString(rotated);

            if (rotateKey.equals(target)) {
                return steps;
            }

            if (visited.add(rotateKey)) {
                queue.offer(rotated);
            }