package DynamicProcessing;

import java.util.HashMap;

/**
 * @author : qizidog
 * @date : 2021-03-30 09:20
 * @description :
 * 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 3：
 * 输入：nums = [0]
 * 输出：0
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 *
 * https://leetcode-cn.com/problems/house-robber-ii/
 **/

public class Rob2 {
    private HashMap<String, Integer> map = new HashMap<>();

    public int rob(int[] nums) {
        if(nums==null || nums.length==0) return 0;

        if(nums.length==1) return nums[0];

        int ret1 = nums[0] + process(nums, 2, true);
        int ret2 = process(nums, 1, false);

        return Math.max(ret1, ret2);
    }

    public int process(int[] nums, int i, boolean head){
        if(i>=nums.length) {
            return 0;
        } else if(i==nums.length-1){
            return head?0:nums[i];
        }

        int ret1, ret2;

        String k1 = (i+2)+""+head;
        if(map.containsKey(k1)){
            ret1 = nums[i] + map.get(k1);
        }else{
            ret1 = process(nums, i+2, head);
            map.put(k1, ret1);
            ret1 += nums[i];
        }

        String k2 = (i+1)+""+head;
        if(map.containsKey(k2)){
            ret2 = map.get(k2);
        }else{
            ret2 = process(nums, i+1, head);
            map.put(k2, ret2);
        }
        return Math.max(ret1, ret2);
    }

    public int rob2(int[] nums) {
        if(nums==null || nums.length==0) return 0;

        if(nums.length==1) return nums[0];

        int len = nums.length;
        int ppre = 0;
        int pre = nums[0];
        for (int i = 1; i < len - 1; i++) {
            int temp = pre;
            pre = Math.max(pre, ppre+nums[i]);
            ppre = temp;
        }
        int m1 = pre;

        ppre = 0;
        pre = nums[1];
        for (int i = 2; i < len; i++) {
            int temp = pre;
            pre = Math.max(pre, ppre+nums[i]);
            ppre = temp;
        }
        int m2 = pre;

        return Math.max(m1, m2);
    }


    public static void main(String[] args) {
        Rob2 rob2 = new Rob2();
        int[] arr = new int[]{4,1,2,7,5,3,1};
        System.out.println(rob2.rob(arr));
        System.out.println(rob2.rob2(arr));

    }
}

