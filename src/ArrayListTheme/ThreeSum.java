package ArrayListTheme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : qizidog
 * @date : 2021-04-07 11:27
 * @description :
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * 示例 2：
 * 输入：nums = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[]
 *
 * 提示：
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
 * https://leetcode-cn.com/problems/3sum/
 **/

public class ThreeSum {
    // 说实话，这种题，真他么难写
    // 融合了TwoSum和TwoSum2的综合写法
    public List<List<Integer>> threeSum(int[] nums) {
        int len;
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if(nums==null || (len = nums.length)<3){  // 3个数都凑不够，不用搞了
            return result;
        }

        Arrays.sort(nums);  // 排序

        for(int i = 0; i < len-2; i++){
            if(nums[i]>0) break;  // 第一个数就大于0，那不用搞了
            if(i>0 && nums[i]==nums[i-1]) continue;  // 第一个数和前一个数相同，直接看下一个数

            // 后两个数，往中间收敛，同TwoSum2
            int k = len-1;
            for(int j = i+1; j < k; j++){  // 确保j<k
                while(k>j){
                    if(k<len-1 && nums[k]==nums[k+1]) {  // 已经用过相同的最后一个数了，直接看前一个数
                        k--;
                        continue;
                    }
                    int s = nums[i] + nums[j] + nums[k];
                    if(s==0){
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }else if(s<0){  // 如果和小于0，说明k继续减小也没用了，应该让j增大试试
                        break;
                    }
                    k--;
                }
            }
        }

        return result;
    }


    // 和上面一致的逻辑，改动后的写法实现，更好写也更好理解，强推O(n^2)
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        int len;
        if(nums==null || (len=nums.length)<3) return result;

        Arrays.sort(nums);  // O(nlogn)
        // [-4,-1,-1,0,1,2]

        // 最小的数大于0或者最大的数小于0，不可能成立
        if(nums[0]>0 || nums[len-1]<0) return result;

        for (int i = 0; i < len; i++) {
            if(i>0 && nums[i]==nums[i-1]) {
                continue;  // 用过的target不再考虑了
            }
            int target = -nums[i];
            for(int j=i+1, k=len-1; j<k;){  // 这里的循环写得挺优雅的
                if(j>i+1 && nums[j]==nums[j-1]) {  // 如果用过相同的第二个数，跳过
                    j++;
                    continue;
                }

                int temp = nums[j] + nums[k];
                if(temp==target){
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                }else if(temp<target){
                    j++;
                }else{
                    k--;
                }
            }
        }

        return result;
    }
}
