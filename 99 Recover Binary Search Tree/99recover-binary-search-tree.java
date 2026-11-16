class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode first = null, second = null, prev = null;
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                // visit curr
                if (prev != null && prev.val > curr.val) {
                    if (first == null) first = prev;
                    second = curr;
                }
                prev = curr;
                curr = curr.right;
            } else {
                // find inorder predecessor
                TreeNode pred = curr.left;
                while (pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }

                if (pred.right == null) {
                    // create thread, go left
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    // thread exists: left subtree done, remove thread, visit curr
                    pred.right = null;
                    if (prev != null && prev.val > curr.val) {
                        if (first == null) first = prev;
                        second = curr;
                    }
                    prev = curr;
                    curr = curr.right;
                }
            }
        }

        // swap values
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
}