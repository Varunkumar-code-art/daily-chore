/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    int count = 0;

    public int averageOfSubtree(TreeNode root) {

        if (root == null) {
            return 0;
        }

        check(root);

        averageOfSubtree(root.left);
        averageOfSubtree(root.right);

        return count;
    }

    void check(TreeNode root) {

        int[] result = findSumAndCount(root);

        int sum = result[0];
        int nodes = result[1];

        int average = sum / nodes;

        if (root.val == average) {
            count++;
        }
    }

    int[] findSumAndCount(TreeNode root) {

        if (root == null) {
            return new int[]{0, 0};
        }

        int[] left = findSumAndCount(root.left);
        int[] right = findSumAndCount(root.right);

        int sum = root.val + left[0] + right[0];
        int nodes = 1 + left[1] + right[1];

        return new int[]{sum, nodes};
    }
}