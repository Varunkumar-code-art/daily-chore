class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        String result = "";

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {

                int j = i + 1;

                while (s.charAt(j) != ')') {
                    j++;
                }

                String key = s.substring(i + 1, j);

                String value = "?";

                for (int k = 0; k < knowledge.size(); k++) {

                    if (knowledge.get(k).get(0).equals(key)) {
                        value = knowledge.get(k).get(1);
                        break;
                    }
                }

                result += value;
                i = j;

            } else {
                result += s.charAt(i);
            }
        }

        return result;
    }
}