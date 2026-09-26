class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        HashMap<String, String> map = new HashMap<>();

        for (List<String> item : knowledge) {
            map.put(item.get(0), item.get(1));
        }

        String result = "";

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {

                int j = i + 1;

                while (s.charAt(j) != ')') {
                    j++;
                }

                String key = s.substring(i + 1, j);

                if (map.containsKey(key)) {
                    result += map.get(key);
                } else {
                    result += "?";
                }

                i = j;

            } else {
                result += s.charAt(i);
            }
        }

        return result;
    }
}