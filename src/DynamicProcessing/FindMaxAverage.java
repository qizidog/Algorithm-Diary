package DynamicProcessing;

/**
 * @author : qizidog
 * @date : 2021-02-04 23:01
 * @description :
 * 643. 子数组最大平均数 I
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 *
 * 示例：
 *
 * 输入：[1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-average-subarray-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class FindMaxAverage {
    // 滑动窗口求解，时间O(n)，空间O(1)
    // 每次求和的时候用上次的sum减去上次的第一个元素，再加上本次的最后一个元素，得到本次的求和
    public static double findMaxAverage(int[] nums, int k) {
        if(nums==null || nums.length==0) return 0;

        double maxSum = 0;
        double sum = 0;
        for(int i=0; i<k; i++){
            sum+=nums[i];
        }
        maxSum = sum;
        for(int i=1; i<nums.length-k+1; i++){
            sum = sum-nums[i-1]+nums[i-1+k];
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum/k;
    }

    // 利用前缀和求解，时间O(n)，空间O(n)
    public static double findMaxAverage2(int[] nums, int k) {
        if(nums==null || nums.length==0 || k==0) return 0;

        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i=1; i<nums.length; i++){
            sums[i] = sums[i-1]+nums[i];
        }

        double maxSum = sums[k-1];
        for (int j=k; j<nums.length; j++){
            maxSum = Math.max(maxSum, sums[j]-sums[j-k]);
        }

        return maxSum/k;
    }


    public static int[] generateRandomInts(int maxNum, int maxVal){
        int num = (int) (Math.random() * maxNum + 1);
        int[] ret = new int[num];
        for (int i = 0; i < num; i++) {
            ret[i] = (int) (Math.random() * maxVal + 1);
        }
        return ret;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int[] ints = generateRandomInts(10, 100);
            int k = (int)(Math.random()*ints.length);
            if (findMaxAverage(ints, k)-findMaxAverage2(ints, k)>0.001){
                System.out.println("error");
            }
        }
    }
}
