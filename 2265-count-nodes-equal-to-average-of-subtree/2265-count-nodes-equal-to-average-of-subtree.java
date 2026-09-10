class Solution {

    int ans = 0;

    class Pair {
        int sum;
        int count;

        Pair(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }

    private Pair dfs(TreeNode node) {

        if (node == null) {
            return new Pair(0, 0);
        }

        Pair left = dfs(node.left);
        Pair right = dfs(node.right);

        int sum = node.val + left.sum + right.sum;
        int count = 1 + left.count + right.count;

        if (node.val == sum / count) {
            ans++;
        }

        return new Pair(sum, count);
    }
}