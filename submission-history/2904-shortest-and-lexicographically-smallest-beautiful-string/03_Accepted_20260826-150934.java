class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int minLen = Integer.MAX_VALUE;
        String result = "";
        
        int left = 0;
        int onesCount = 0;
        
        for (int right = 0; right < n; right++) {
            // Expand window by including s[right]
            if (s.charAt(right) == '1') {
                onesCount++;
            }
            
            // Shrink window from left while maintaining exactly k ones
            while (onesCount == k) {
                int length = right - left + 1;
                String substring = s.substring(left, right + 1);
                
                // Update result if shorter, or same length but lexicographically smaller
                if (length < minLen || (length == minLen && substring.compareTo(result) < 0)) {
                    minLen = length;
                    result = substring;
                }
                
                // Shrink from left
                if (s.charAt(left) == '1') {
                    onesCount--;
                }
                left++;
            }
        }
        
        return result;
    }
}

// Test cases
class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test 1: s = "100011001", k = 3
        String test1 = sol.shortestBeautifulSubstring("100011001", 3);
        System.out.println("Test 1: " + test1); // Expected: "11001"
        
        // Test 2: s = "1011", k = 2
        String test2 = sol.shortestBeautifulSubstring("1011", 2);
        System.out.println("Test 2: " + test2); // Expected: "11"
        
        // Test 3: s = "000", k = 1
        String test3 = sol.shortestBeautifulSubstring("000", 1);
        System.out.println("Test 3: " + test3); // Expected: ""
        
        // Test 4: Edge case - single beautiful substring
        String test4 = sol.shortestBeautifulSubstring("111", 3);
        System.out.println("Test 4: " + test4); // Expected: "111"
        
        // Test 5: Multiple options, pick lexicographically smallest
        String test5 = sol.shortestBeautifulSubstring("1101011", 2);
        System.out.println("Test 5: " + test5); // Expected: "11"
    }
}