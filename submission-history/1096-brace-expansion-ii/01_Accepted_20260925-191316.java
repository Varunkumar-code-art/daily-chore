class Solution {

    public List<String> braceExpansionII(String expression) {

        Set<String> result = solve(expression);

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        return answer;
    }

    Set<String> solve(String s) {

        Set<String> result = new HashSet<>();

        int i = 0;

        while (i < s.length()) {

            Set<String> current = new HashSet<>();

            if (s.charAt(i) == '{') {

                int start = i;
                int count = 0;

                while (i < s.length()) {

                    if (s.charAt(i) == '{') {
                        count++;
                    }

                    if (s.charAt(i) == '}') {
                        count--;
                    }

                    if (count == 0) {
                        break;
                    }

                    i++;
                }

                String inside = s.substring(start + 1, i);

                current = solveParts(inside);

            } else {

                current.add(String.valueOf(s.charAt(i)));
            }

            if (result.isEmpty()) {
                result.addAll(current);
            } else {
                result = multiply(result, current);
            }

            i++;
        }

        return result;
    }

    Set<String> solveParts(String s) {

        Set<String> result = new HashSet<>();

        int count = 0;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '{') {
                count++;
            }

            else if (s.charAt(i) == '}') {
                count--;
            }

            else if (s.charAt(i) == ',' && count == 0) {

                String part = s.substring(start, i);

                result.addAll(solve(part));

                start = i + 1;
            }
        }

        String part = s.substring(start);

        result.addAll(solve(part));

        return result;
    }

    Set<String> multiply(Set<String> a, Set<String> b) {

        Set<String> result = new HashSet<>();

        for (String x : a) {

            for (String y : b) {

                result.add(x + y);
            }
        }

        return result;
    }
}