package DynamicProcessing;

/**
 * @author : qizidog
 * @date : 2020-10-07 20:38
 * @description :
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 **/

public class MaxSubArray {
    // 暴力破解，时间复杂度O(n^2)
    public int maxSubArray(int[] nums) {
        if(null==nums || nums.length==0) return 0;
        int len = nums.length;
        int maxSum = nums[0];
        for(int i=0; i<len; i++){
            int sum=0;
            for(int j=i; j<len; j++){
                sum+=nums[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }


    // 分治算法，还挺难写的
    public int maxSubArray2(int[] nums) {
        if(null==nums || nums.length==0) return 0;
        int len = nums.length;
        return divideAndConquer(nums, 0, len-1);
    }

    public int divideAndConquer(int[] nums, int left, int right){
        if(left==right){
            return nums[left];
        }else{
            int mid = (left+right)/2;
            int ret1 = divideAndConquer(nums, left, mid);
            int ret2 = divideAndConquer(nums, mid+1, right);

            int midLeftMax = nums[mid]; int midRightMax=nums[mid+1];
            int tempMidLeftMax = 0; int tempMidRightMax = 0;
            for(int i=mid; i>=left; i--){
                tempMidLeftMax+=nums[i];
                if(tempMidLeftMax>midLeftMax) midLeftMax=tempMidLeftMax;
            }
            for(int i=mid+1; i<=right; i++){
                tempMidRightMax+=nums[i];
                if(tempMidRightMax>midRightMax) midRightMax=tempMidRightMax;
            }
            int ret3 = midLeftMax+midRightMax;

            if(ret1>=ret2){
                if(ret1>=ret3) return ret1;
                else return ret3;
            }else{
                if(ret2>=ret3) return ret2;
                else return ret3;
            }
        }
    }


    // 官方解法，动态规划，整数增益，时间复杂度O(n)，效率贼高，怎么就想不到呢
    public int maxSubArray3(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

}
