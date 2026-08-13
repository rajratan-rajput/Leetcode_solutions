class Solution {

    class Node {
        char leftChar;
        char rightChar;
        int prefix;
        int suffix;
        int best;
        int length;

        Node(char ch) {
            leftChar = ch;
            rightChar = ch;
            prefix = 1;
            suffix = 1;
            best = 1;
            length = 1;
        }

        Node() {}
    }

    Node[] tree;
    char[] arr;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        int n = s.length();

        arr = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            arr[index] = ch;

            update(1, 0, n - 1, index, ch);

            ans[i] = tree[1].best;
        }

        return ans;
    }

    // Build Segment Tree
    private void build(int node, int start, int end) {

        if (start == end) {
            tree[node] = new Node(arr[start]);
            return;
        }

        int mid = start + (end - start) / 2;

        build(node * 2, start, mid);
        build(node * 2 + 1, mid + 1, end);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Update one character
    private void update(int node, int start, int end, int index, char ch) {

        if (start == end) {
            tree[node] = new Node(ch);
            return;
        }

        int mid = start + (end - start) / 2;

        if (index <= mid) {
            update(node * 2, start, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, end, index, ch);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Merge two nodes
    private Node merge(Node left, Node right) {

        Node result = new Node();

        result.length = left.length + right.length;

        result.leftChar = left.leftChar;
        result.rightChar = right.rightChar;

        // Prefix
        result.prefix = left.prefix;

        if (left.prefix == left.length &&
            left.rightChar == right.leftChar) {
            result.prefix = left.length + right.prefix;
        }

        // Suffix
        result.suffix = right.suffix;

        if (right.suffix == right.length &&
            left.rightChar == right.leftChar) {
            result.suffix = right.length + left.suffix;
        }

        // Best
        result.best = Math.max(left.best, right.best);

        if (left.rightChar == right.leftChar) {
            result.best = Math.max(
                result.best,
                left.suffix + right.prefix
            );
        }

        return result;
    }
}