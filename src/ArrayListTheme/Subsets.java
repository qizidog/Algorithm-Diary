package ArrayListTheme;

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


    // 官方回溯解法：难以理解
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets2(int[] nums) {
        dfs2(0, nums);
        return ans;
    }

    public void dfs2(int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        t.add(nums[cur]);
        dfs2(cur + 1, nums);
        t.remove(t.size() - 1);
        dfs2(cur + 1, nums);
    }


    // 官方迭代法：涉及到位运算，需要研究下
    // List<Integer> t = new ArrayList<Integer>();
    // List<List<Integer>> ans = new ArrayList<List<Integer>>();
    public List<List<Integer>> subsets3(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }

}
