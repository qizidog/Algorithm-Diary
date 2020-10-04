package DynamicProcessing;

/**
 * @author : qizidog
 * @date : 2020-10-04 12:07
 * @description :
 * 303. 区域和检索 - 数组不可变
 * 会多次调用 sumRange 方法。
 **/

public class NumArray {
    private int[] sums;

    public NumArray(int[] nums) {
        if(nums.length>0){
            sums = new int[nums.length];
            sums[0] = nums[0];
            for(int i=1; i<nums.length; i++){
                sums[i] = sums[i-1]+nums[i];
            }
        }
    }

    public int sumRange(int i, int j) {
        if (i==0) return sums[j];
        return sums[j]-sums[i-1];
    }
}
