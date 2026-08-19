import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.put(row, map.getOrDefault(row, 0) | (1 << (col - 1)));
        }

        int left = (1 << 1) | (1 << 2) | (1 << 3) | (1 << 4);
        int middle = (1 << 3) | (1 << 4) | (1 << 5) | (1 << 6);
        int right = (1 << 5) | (1 << 6) | (1 << 7) | (1 << 8);

        long answer = 2L * n;

        for (int mask : map.values()) {

            boolean leftFree = (mask & left) == 0;
            boolean middleFree = (mask & middle) == 0;
            boolean rightFree = (mask & right) == 0;

            int groups;

            if (leftFree && rightFree) {
                groups = 2;
            } else if (leftFree || middleFree || rightFree) {
                groups = 1;
            } else {
                groups = 0;
            }

            answer += groups - 2;
        }

        return (int)answer;
    }
}