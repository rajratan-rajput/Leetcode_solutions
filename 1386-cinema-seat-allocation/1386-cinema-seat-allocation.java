 

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        
        Map<Integer, Integer> reserved = new HashMap<>();

        // Store reserved seats as a bitmask for each row
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            if (col >= 2 && col <= 9) {
                reserved.put(
                    row,
                    reserved.getOrDefault(row, 0) | (1 << col)
                );
            }
        }

        // Masks for the three possible groups
        int leftMask = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
        int middleMask = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);
        int rightMask = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);

        // Rows without any relevant reservation can hold 2 families
        int answer = (n - reserved.size()) * 2;

        for (int mask : reserved.values()) {

            boolean left = (mask & leftMask) == 0;
            boolean middle = (mask & middleMask) == 0;
            boolean right = (mask & rightMask) == 0;

            if (left && right) {
                answer += 2;
            } else if (left || middle || right) {
                answer += 1;
            }
        }

        return answer;
    }
}