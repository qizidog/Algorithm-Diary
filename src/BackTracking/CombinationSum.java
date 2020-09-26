package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : qizidog
 * @date : 2020-09-26 22:29
 * @description :
 * 39. 组合总和
 **/

/*
 * 给定一个无重复元素的数组 candidates 和一个目标数 target，
 * 找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if(null==candidates || candidates.length==0) return results;
        List<Integer> ret = new ArrayList<Integer>();
        backtracking(results, ret, candidates, target, 0, 0);
        return results;
    }

    public void backtracking(List<List<Integer>> results, List<Integer> ret,
                             int[] candidates, int target, int sum, int id){
        if(sum==target){
            results.add(new ArrayList<Integer>(ret));
        }else if(sum>target){
            return;
        }else{
            for(int i=id; i<candidates.length; i++){
                ret.add(candidates[i]);
                backtracking(results, ret, candidates, target, sum+candidates[i], i);
                ret.remove(ret.size()-1);
            }
        }
    }
}
