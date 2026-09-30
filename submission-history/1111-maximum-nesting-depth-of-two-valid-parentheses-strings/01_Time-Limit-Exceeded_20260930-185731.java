class Solution {

    int minDepth = Integer.MAX_VALUE;
    int[] result;

    public int[] maxDepthAfterSplit(String seq) {

        int n = seq.length();
        result = new int[n];

        int[] answer = new int[n];

        solve(seq, 0, answer);

        return result;
    }

    void solve(String seq, int index, int[] answer) {

        if (index == seq.length()) {

            int depthA = findDepth(seq, answer, 0);
            int depthB = findDepth(seq, answer, 1);

            if (depthA == -1 || depthB == -1) {
                return;
            }

            int maxDepth = Math.max(depthA, depthB);

            if (maxDepth < minDepth) {

                minDepth = maxDepth;

                for (int i = 0; i < answer.length; i++) {
                    result[i] = answer[i];
                }
            }

            return;
        }

        answer[index] = 0;
        solve(seq, index + 1, answer);

        answer[index] = 1;
        solve(seq, index + 1, answer);
    }

    int findDepth(String seq, int[] answer, int group) {

        int balance = 0;
        int maxDepth = 0;

        for (int i = 0; i < seq.length(); i++) {

            if (answer[i] != group) {
                continue;
            }

            if (seq.charAt(i) == '(') {
                balance++;
                maxDepth = Math.max(maxDepth, balance);
            } else {
                balance--;

                if (balance < 0) {
                    return -1;
                }
            }
        }

        if (balance != 0) {
            return -1;
        }

        return maxDepth;
    }
}