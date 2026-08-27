 class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        
        // Count frequency of each character in s
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        String result = null;
        
        // Try each position as the "break point"
        for (int breakPos = 0; breakPos < n; breakPos++) {
            int[] tempFreq = freq.clone();
            StringBuilder candidate = new StringBuilder();
            boolean valid = true;
            
            // Step 1: Match target exactly up to breakPos - 1
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
                break; // Can't match prefix, no solution exists
            }
            
            // Step 2: At breakPos, place the smallest char > target[breakPos]
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
                continue; // Try next break point
            }
            
            // Step 3: Fill remaining positions with smallest available characters
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
    
    // Test cases
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Example 1
        System.out.println("Test 1: " + sol.lexGreaterPermutation("abc", "bba"));
        // Expected: "bca"
        
        // Example 2
        System.out.println("Test 2: " + sol.lexGreaterPermutation("leet", "code"));
        // Expected: "eelt"
        
        // Example 3
        System.out.println("Test 3: " + sol.lexGreaterPermutation("baba", "bbaa"));
        // Expected: ""
        
        // Additional test
        System.out.println("Test 4: " + sol.lexGreaterPermutation("dcba", "dcba"));
        // Expected: ""
    }
}