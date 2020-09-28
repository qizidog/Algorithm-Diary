package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : qizidog
 * @date : 2020-09-28 13:45
 * @description :
 * 78. 子集
 * 出现在数组里面，结果又是回溯算法
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 **/

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        ans.add(new ArrayList<Integer>());
        List<Integer> last = new ArrayList<Integer>();
        for(int i=0; i<nums.length; i++){
            last.add(nums[i]);
        }
        ans.add(last);

        for(int i=1; i<nums.length; i++){
            backtracking(nums, i, 0, new ArrayList<Integer>(), ans);
        }
        return ans;
    }

    public void backtracking(int[] nums, int targetNum, int startNum,
                             List<Integer> lst, List<List<Integer>> ans){
        if(targetNum==lst.size()){
            ans.add(new ArrayList(lst));
        }else{
            for(int i=startNum; i<nums.length; i++){
                lst.add(nums[i]);
                backtracking(nums, targetNum, i+1, lst, ans);
                lst.remove(lst.size()-1);
            }
        }
    }
}
