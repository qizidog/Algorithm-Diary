package ArrayListTheme;

import java.util.*;

/**
 * @author : qizidog
 * @date : 2020-09-29 11:00
 * @description :
 * 90. 子集 II
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 **/

public class Subsets2 {
    /*// 这种方法搞不定了
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();

        for (int i=0; i<=nums.length; i++) {
            backtracking(nums, ans, temp, i, 0);
        }

        return ans;
    }

    public void backtracking(int[] nums, List<List<Integer>> ans, List<Integer> temp,
                             int targetNums, int startNum){
        if (temp.size()==targetNums){
            ans.add(new ArrayList<Integer>(temp));
        }else{
            for (int i=startNum; i<nums.length; i++) {
                if (i == 0 || nums[i-1]!=nums[i]) {
                    temp.add(nums[i]);
                    backtracking(nums, ans, temp, targetNums, startNum + 1);
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }*/


    // 官方解法
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums); //排序
        getAns(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void getAns(int[] nums, int start, ArrayList<Integer> temp, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            //和上个数字相等就跳过
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            getAns(nums, i + 1, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }
}
