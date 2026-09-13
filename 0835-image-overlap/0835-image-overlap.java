class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int maxOverlap = 0;

        // Store coordinates of all 1s in both images
        java.util.List<int[]> ones1 = new java.util.ArrayList<>();
        java.util.List<int[]> ones2 = new java.util.ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    ones1.add(new int[]{i, j});
                }

                if (img2[i][j] == 1) {
                    ones2.add(new int[]{i, j});
                }
            }
        }

        // Try every possible translation
        java.util.Map<String, Integer> count = new java.util.HashMap<>();

        for (int[] p1 : ones1) {
            for (int[] p2 : ones2) {

                // Translation needed to move p1 onto p2
                int dx = p2[0] - p1[0];
                int dy = p2[1] - p1[1];

                String key = dx + "," + dy;

                count.put(key, count.getOrDefault(key, 0) + 1);

                maxOverlap = Math.max(maxOverlap, count.get(key));
            }
        }

        return maxOverlap;
    }
}