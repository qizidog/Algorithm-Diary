package ArrayListTheme;

/**
 * @author : qizidog
 * @date : 2021-03-23 10:41
 * @description :
 * 剑指 Offer 53 - II. 0～n-1中缺失的数字
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 * 示例 1:
 * 输入: [0,1,3]
 * 输出: 2
 *
 * 示例 2:
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 **/

// 结合着MissingNumber和SearchInSorted来看，我还挺烦这种扣下标的题的
public class MissingNumber2 {
    public int missingNumber(int[] nums) {
        if(nums==null || nums.length==0) return 0;

        int left = 0;
        int right = nums.length-1;
        int mid;
        // 找到第一个下标比自己值小的数
        while(left <= right){
            mid = (left + right)>>1;
            if(nums[mid] > mid){
                right = mid-1;
            } else{
                left = mid+1;
            }
        }
        return left;
    }
}
