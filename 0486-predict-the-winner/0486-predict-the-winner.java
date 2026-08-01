class Solution {
    int[][] dp;

    private int solve(int[] nums, int l, int r) {
        if (l > r)
            return 0;

        if (l == r)
            return nums[l];

        if (dp[l][r] != -1)
            return dp[l][r];

        int takeLeft = nums[l] + Math.min(
                solve(nums, l + 2, r),
                solve(nums, l + 1, r - 1)
        );

        int takeRight = nums[r] + Math.min(
                solve(nums, l, r - 2),
                solve(nums, l + 1, r - 1)
        );

        return dp[l][r] = Math.max(takeLeft, takeRight);
    }

    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;

        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(dp[i], -1);
        }

        int total = 0;
        for (int num : nums) {
            total += num;
        }

        int player1 = solve(nums, 0, n - 1);
        int player2 = total - player1;

        return player1 >= player2;
    }
}