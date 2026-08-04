import java.util.*;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> list = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return list;
        }

        int mini = nums[0];
        int maxi = nums[0];

        for (int val : nums) {
            mini = Math.min(mini, val);
            maxi = Math.max(maxi, val);
        }

        Arrays.sort(nums);

        int idx = 0;

        for (int i = mini; i <= maxi; i++) {

            // Skip all duplicates
            while (idx < nums.length && nums[idx] < i) {
                idx++;
            }

            if (idx < nums.length && nums[idx] == i) {
                while (idx < nums.length && nums[idx] == i) {
                    idx++;
                }
            } else {
                list.add(i);
            }
        }

        return list;
    }
}