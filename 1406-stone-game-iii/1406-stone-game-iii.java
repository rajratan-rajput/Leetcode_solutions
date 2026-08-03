class Solution {

    private final int Alice = 1;
    private final int Bob = 0;
    private int n;

    private int miniMax(int[] stoneValue, int player, int i, Integer[][] dp) {
        if (i >= n)
            return 0;

        if (dp[player][i] != null)
            return dp[player][i];

        int result = (player == Alice) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int stones = 0;

        for (int j = i; j < Math.min(i + 3, n); j++) {
            if (player == Alice) {
                stones += stoneValue[j];
                result = Math.max(result,
                        stones + miniMax(stoneValue, Bob, j + 1, dp));
            } else {
                stones -= stoneValue[j];
                result = Math.min(result,
                        stones + miniMax(stoneValue, Alice, j + 1, dp));
            }
        }

        dp[player][i] = result;
        return result;
    }

    public String stoneGameIII(int[] stoneValue) {
        n = stoneValue.length;

        Integer[][] dp = new Integer[2][n + 1];

        int diff = miniMax(stoneValue, Alice, 0, dp);

        if (diff > 0)
            return "Alice";
        else if (diff < 0)
            return "Bob";
        else
            return "Tie";
    }
}