import java.util.*;

class Solution {
    public int missingInteger(int[] nums) {
        
         
        HashSet<Integer> set = new HashSet<>();
        
        for (int val : nums) {
            set.add(val);
        }
        
         
        int prefixSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                prefixSum += nums[i];
            } else {
                break;
            }
        }
        
         
        int search = prefixSum;
        
        while (set.contains(search)) {
            search++;
        }
        
        return search;
    }
}