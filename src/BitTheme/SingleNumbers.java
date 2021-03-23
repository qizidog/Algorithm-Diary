package BitTheme;

/**
 * @author : qizidog
 * @date : 2021-03-23 11:09
 * @description :
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 * 示例 1：
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 *
 * 示例 2：
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 **/

public class SingleNumbers {
    public int[] singleNumbers(int[] nums) {
        if(nums==null || nums.length==0) return null;
        int ab = 0;
        for(int num : nums){
            ab ^= num;
        }
        // 最右侧的1
        int rst = ab & (~ab+1);
        int a = 0;
        // if((num & (~num+1)) == rst){  // 这种写法错误，因为很可能根本没有一个数最右侧的1满足条件
            for(int num : nums){
            if(0 == (rst & num)){  // 把刚好这位是1的异或起来
                a ^= num;
            }
        }
        return new int[]{a, ab^a};
    }
}
