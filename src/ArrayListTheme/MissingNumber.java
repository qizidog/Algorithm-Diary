package ArrayListTheme;

import java.util.HashSet;

/**
 * @author : qizidog
 * @date : 2020-09-28 11:33
 * @description :
 * 268. 缺失数字
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 **/

public class MissingNumber {
    // 我的解法，时间复杂度O(n)
    public int missingNumber(int[] nums) {
        boolean[] cp = new boolean[nums.length+1];

        for(int each:nums){
            cp[each] = true;
        }
        for(int i=0; i<cp.length; i++){
            if(!cp[i]){
                return i;
            }
        }
        return 0;
    }

    // 利用hashset求解，时间复杂度O(n)
    public int missingNumber2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num:nums){
            set.add(num);
        }
        for (int i=0; i<nums.length+1; i++){
            if (!set.contains(i)) return i;
        }
        return 0;
    }

    // 我的新方法，数学式计算
    public int missingNumber3(int[] nums) {
        int sum = 0;
        for (int num:nums) {
            sum+=num;
        }
        return nums.length*(nums.length+1)/2 - sum;
    }
}
