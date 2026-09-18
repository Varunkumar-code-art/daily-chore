class Solution {

    public List<String> maxNumOfSubstrings(String s) {

        List<String> list = new ArrayList<>();

        int n = s.length();

        for (int i = 0; i < n; i++) {

            int left = i;
            int right = i;

            boolean valid = true;

            while (right < n) {

                char c = s.charAt(right);

                int first = s.indexOf(c);
                int last = s.lastIndexOf(c);

                if (first < left) {
                    valid = false;
                    break;
                }

                if (last > right) {
                    right = last;
                }

                right++;
            }

            if (valid) {
                list.add(s.substring(left, right));
            }
        }

        // Remove overlapping substrings
        list.sort((a, b) -> a.length() - b.length());

        List<String> answer = new ArrayList<>();

        int lastEnd = -1;

        for (String str : list) {

            int start = s.indexOf(str);

            if (start > lastEnd) {
                answer.add(str);
                lastEnd = start + str.length() - 1;
            }
        }

        return answer;
    }
}