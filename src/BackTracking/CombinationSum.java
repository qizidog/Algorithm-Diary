package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : qizidog
 * @date : 2020-09-26 22:29
 * @description :
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class CombinationSum {
    // 因为是求所有的组合，所以直接回溯就好了，如果是求有多少种组合，动态规划也能做，效果还会更好一点
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
