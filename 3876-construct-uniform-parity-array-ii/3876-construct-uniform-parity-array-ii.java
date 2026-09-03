class Solution {
    public boolean uniformArray(int[] nums1) {

        int min = nums1[0];

        // Find minimum
        for (int num : nums1) {
            min = Math.min(min, num);
        }

        // Minimum is odd:
        // We can make every element odd.
        if (min % 2 != 0) {
            return true;
        }

        // Minimum is even:
        // Every element must already be even.
        for (int num : nums1) {
            if (num % 2 != 0) {
                return false;
            }
        }

        return true;
    }
}