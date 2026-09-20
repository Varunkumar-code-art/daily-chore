class Solution {
    public int reverseDegree(String s) {
        // ==========================================================
        // Goal: For every character, multiply
        //       (position in reversed alphabet) x (1-indexed position in string)
        //       and sum it all up.
        //
        //   Reversed alphabet position:  'a' -> 26, 'b' -> 25, ... 'z' -> 1
        //   Formula:  26 - (c - 'a')
        // ==========================================================

        int reverseDegree = 0;  // running total of all products

        // ----------------------------------------------------------
        // Walk through the string once — single pass, O(n) time
        // ----------------------------------------------------------
        for (int i = 0; i < s.length(); i++) {

            char currentChar = s.charAt(i);

            // --- Step 1: Map character to its reversed-alphabet value ---
            // 'a' -> 26, 'b' -> 25, ..., 'z' -> 1
            int reverseAlphabetValue = 26 - (currentChar - 'a');

            // --- Step 2: Get the 1-indexed position in the string ---
            int stringPosition = i + 1;

            // --- Step 3: Multiply and accumulate ---
            reverseDegree += reverseAlphabetValue * stringPosition;
        }

        // ----------------------------------------------------------
        // Final accumulated sum is the answer
        // ----------------------------------------------------------
        return reverseDegree;
    }
}