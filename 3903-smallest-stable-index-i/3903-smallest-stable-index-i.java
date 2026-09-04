class Solution {

    public int maxFun(int[] nums, int index) {
        int maxVal = nums[0];

        int start = 0;

        while (start <= index) {
            maxVal = Math.max(maxVal, nums[start]);
            start++;
        }

        return maxVal;
    }

    public int minFun(int[] nums, int index) {
        int minVal = nums[index];

        while (index < nums.length) {
            minVal = Math.min(minVal, nums[index]);
            index++;
        }

        return minVal;
    }

    public int firstStableIndex(int[] nums, int k) {

        for (int i = 0; i < nums.length; i++) {

            int first = maxFun(nums, i);
            int second = minFun(nums, i);

            int instability = first - second;

            if (instability <= k) {
                return i;
            }
        }

        return -1;
    }
}