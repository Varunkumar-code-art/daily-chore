class Solution {

    int index = 0;

    public List<String> braceExpansionII(String expression) {

        Set<String> result = parse(expression);

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        return answer;
    }

    Set<String> parse(String s) {

        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();

        current.add("");

        while (index < s.length() && s.charAt(index) != '}') {

            if (s.charAt(index) == ',') {

                result.addAll(current);
                current.clear();
                current.add("");

                index++;
            }

            else {

                Set<String> next;

                if (s.charAt(index) == '{') {

                    index++;

                    next = parse(s);

                    index++; // skip '}'

                } else {

                    next = new HashSet<>();

                    next.add(String.valueOf(s.charAt(index)));

                    index++;
                }

                current = multiply(current, next);
            }
        }

        result.addAll(current);

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