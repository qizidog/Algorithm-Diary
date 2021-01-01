package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : qizidog
 * @date : 2020-09-25 13:41
 * @description :
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class Permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answers = new ArrayList<List<Integer>>();

        backtracking(answers, nums, new ArrayList<Integer>());
        return answers;
    }

    public void backtracking(List<List<Integer>> answers, int[] nums, List<Integer> ans){
        if(nums.length==0){
            // 注意引用关系，这里创建一个新的数组备份
            answers.add(new ArrayList<Integer>(ans));
            return;
        }

        for(int i=0; i<nums.length; i++){
            ans.add(nums[i]);
            backtracking(answers, getNewIntArray(nums, i), ans);
            ans.remove(ans.size()-1);
        }
    }

    // 参考官方介绍，其实不需要生成一个新的数组，可以用一个boolean[]表示元素是否已经被使用过了
    public int[] getNewIntArray(int[] nums, int id){
        int[] newIntArray = new int[nums.length-1];
        for(int i=0; i<nums.length-1; i++){
            if(i<id)
                newIntArray[i] = nums[i];
            else
                newIntArray[i] = nums[i+1];
        }
        return newIntArray;
    }
}
