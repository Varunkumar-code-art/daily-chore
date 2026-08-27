 class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        String result = null;
        for (int breakPos = 0; breakPos < n; breakPos++) {
            int[] tempFreq = freq.clone();
            StringBuilder candidate = new StringBuilder();
            boolean valid = true;
            
            for (int i = 0; i < breakPos; i++) {
                char c = target.charAt(i);
                if (tempFreq[c - 'a'] > 0) {
                    candidate.append(c);
                    tempFreq[c - 'a']--;
                } else {
                    valid = false;
                    break;
                }
            }
            
            if (!valid) {
                break;
            }
            
            char targetChar = target.charAt(breakPos);
            boolean placed = false;
            
            for (int i = targetChar - 'a' + 1; i < 26; i++) {
                if (tempFreq[i] > 0) {
                    candidate.append((char) ('a' + i));
                    tempFreq[i]--;
                    placed = true;
                    break;
                }
            }
            
            if (!placed) {
                continue; 
            }
            
            for (int i = breakPos + 1; i < n; i++) {
                for (int j = 0; j < 26; j++) {
                    if (tempFreq[j] > 0) {
                        candidate.append((char) ('a' + j));
                        tempFreq[j]--;
                        break;
                    }
                }
            }
            
            String candidateStr = candidate.toString();
            if (result == null || candidateStr.compareTo(result) < 0) {
                result = candidateStr;
            }
        }
        
        return result == null ? "" : result;
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        System.out.println("Test 1: " + sol.lexGreaterPermutation("abc", "bba"));
        System.out.println("Test 2: " + sol.lexGreaterPermutation("leet", "code"));
        System.out.println("Test 3: " + sol.lexGreaterPermutation("baba", "bbaa"));
        System.out.println("Test 4: " + sol.lexGreaterPermutation("dcba", "dcba"));
    }
}