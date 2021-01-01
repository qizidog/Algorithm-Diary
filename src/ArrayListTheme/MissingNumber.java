package ArrayListTheme;

import java.util.HashSet;

/**
 * @author : qizidog
 * @date : 2020-09-28 11:33
 * @description :
 * 268. 缺失数字
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 * 示例 1：
 *
 * 输入：nums = [3,0,1]
 * 输出：2
 * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：2
 * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 3：
 *
 * 输入：nums = [9,6,4,2,3,5,7,0,1]
 * 输出：8
 * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/missing-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
