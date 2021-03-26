package StackTheme;

import java.util.LinkedList;

/**
 * @author : qizidog
 * @date : 2021-03-25 00:37
 * @description :
 * 456. 132 模式
 * 给你一个整数数组 nums ，数组中共有 n 个整数。
 * 132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，
 * 并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 *
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 * 进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 *
 * 示例 2：
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 *
 * 示例 3：
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 104
 * -109 <= nums[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/132-pattern/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class Find132Pattern {
    // 单调栈（底大顶小），O(n)
    public static boolean find132pattern(int[] nums) {
        int len;
        if(nums==null || (len=nums.length)<3) return false;

        // 记录左边的最小值
        int[] min = new int[len];
        min[0] = nums[0];  // 虽然第0个数左边的最小值不是自己，但是这样设置不会影响此题结果
        // for(int i=1; i<len; i++){
        //     min[i] = Math.min(nums[i], min[i-1]);
        // }

        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(0);
        for(int i=1; i<len; i++){
            // 放到里面来求
            min[i] = Math.min(nums[i], min[i-1]);

            while(!stack.isEmpty() && nums[i]>=nums[stack.peek()]){
                stack.pop();
            }
            if(!stack.isEmpty() && nums[i]>min[stack.peek()]) return true;
            stack.push(i);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] ints = {-1, -2, 1, 2, -2, 1};
        System.out.println(find132pattern(ints));
    }
}


