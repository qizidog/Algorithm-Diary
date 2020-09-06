package StringTheme;

import java.util.HashMap;

/**
 * @author : qizidog
 * @date : 2020-09-06 10:13
 * @description :
 * 1. 两数之和
 **/

/*
    给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
    你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 */
public class TwoSum {
    // hashmap单次循环求解，时间复杂度O(n)
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i<nums.length; i++){
            if (map.get(target-nums[i])!=null){
                return new int[]{map.get(target-nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    // 暴力双重循环破解，时间复杂度O(n^2)
    public int[] twoSum2(int[] nums, int target) {
        for(int i=0; i<nums.length-1; i++){
            for(int j=i+1; j<nums.length; j++){
                if(target-nums[i]==nums[j]){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

}
