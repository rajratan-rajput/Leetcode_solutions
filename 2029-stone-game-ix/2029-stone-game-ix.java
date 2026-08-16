class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];

        for (int stone : stones) {
            count[stone % 3]++;
        }

        int c0 = count[0];
        int c1 = count[1];
        int c2 = count[2];

        // If there are no remainder-1 or remainder-2 stones,
        // Alice cannot make the game favorable.
        if (c1 == 0 && c2 == 0) {
            return false;
        }

        // If c0 is even, the zero stones effectively cancel out
        // in pairs, so Alice wins when c1 and c2 are both non-zero.
        if (c0 % 2 == 0) {
            return c1 > 0 && c2 > 0;
        }

        // c0 is odd.
        // Alice can win if one type is sufficiently larger than the other.
        return Math.abs(c1 - c2) > 2;
    }
}