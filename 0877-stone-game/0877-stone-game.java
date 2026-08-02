class Solution {
    private Integer[][] dp;

    private int solve(int[] piles, int l, int r) {
        if (l == r)
            return piles[l];

        if (dp[l][r] != null)
            return dp[l][r];

        int takeLeft = piles[l] - solve(piles, l + 1, r);
        int takeRight = piles[r] - solve(piles, l, r - 1);

        return dp[l][r] = Math.max(takeLeft, takeRight);
    }

    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        dp = new Integer[n][n];

        return solve(piles, 0, n - 1) > 0;
    }
}