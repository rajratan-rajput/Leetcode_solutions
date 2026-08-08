class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        /*
         * dp[i] = maximum number of characters from the suffix
         * of word2 that can be matched exactly using word1[i...n-1].
         */
        int[] dp = new int[n + 1];

        int j = m - 1;

        // Build suffix information
        for (int i = n - 1; i >= 0; i--) {

            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                dp[i] = dp[i + 1] + 1;
                j--;
            } else {
                dp[i] = dp[i + 1];
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        /*
         * Greedily choose the smallest possible indices.
         */
        while (i < n && j < m) {

            // Exact match: always take it.
            if (word1.charAt(i) == word2.charAt(j)) {

                ans[j] = i;
                j++;
                i++;

            } else {

                /*
                 * Try using this index as our one mismatch.
                 *
                 * After this mismatch, the remaining characters
                 * MUST match exactly.
                 */
                int remaining = m - j - 1;

                if (dp[i + 1] >= remaining) {

                    ans[j] = i;
                    j++;

                    // Mismatch is now used.
                    i++;

                    break;
                }

                // Cannot use this index.
                i++;
            }
        }

        /*
         * The mismatch has either been used or we never needed it.
         *
         * Match the remaining characters exactly.
         */
        while (i < n && j < m) {

            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }

            i++;
        }

        /*
         * If we matched all of word2, answer is valid.
         */
        if (j == m) {
            return ans;
        }

        return new int[0];
    }
}