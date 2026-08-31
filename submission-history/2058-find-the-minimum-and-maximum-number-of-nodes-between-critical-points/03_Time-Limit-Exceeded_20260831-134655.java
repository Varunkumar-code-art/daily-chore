/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode prev = head;
        ListNode curr = head.next;
        int position = 1;

        while (curr.next != null) {
            ListNode next = curr.next;

            if ((curr.val > prev.val && curr.val > next.val) ||
                (curr.val < prev.val && curr.val < next.val)) {
                list.add(position);
            }

            prev = curr;
            curr = next;
            position++;
        }

        if (list.size() < 2) {
            return new int[]{-1, -1};
        }

        int minDistance = Integer.MAX_VALUE;
        int maxDistance = 0;

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int distance = list.get(j) - list.get(i);

                minDistance = Math.min(minDistance, distance);
                maxDistance = Math.max(maxDistance, distance);
            }
        }

        return new int[]{minDistance, maxDistance};
    }
}