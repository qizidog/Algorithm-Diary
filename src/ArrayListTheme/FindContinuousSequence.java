package ArrayListTheme;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : qizidog
 * @date : 2021-03-23 14:18
 * @description :
 * 剑指 Offer 57 - II. 和为s的连续正数序列
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 * 示例 1：
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 *
 * 示例 2：
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 **/

public class FindContinuousSequence {
    // 数学公式限定可能结果集数量范围，时间O(n)
    public int[][] findContinuousSequence(int target) {
        LinkedList<int[]> list = new LinkedList<>();
        int n = 2;
        while((1+n)*n <= 2*target){
            // 数学公式推的
            int up = 2*target - n*n +n;
            int down = 2*n;

            if(up % down == 0){  // 满足条件（能够整除）就存起来
                int s = up/down;
                int[] temp = new int[n];
                for(int i=0; i<n; i++){
                    temp[i] = s + i;
                }
                list.addFirst(temp);
            }

            n++;
        }

        return list.toArray(new int[0][]);
    }


    /**
     *  双指针法，时间O(n)
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/solution/mian-shi-ti-57-ii-he-wei-sde-lian-xu-zheng-shu-x-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param target
     * @return
     */
    public int[][] findContinuousSequence2(int target) {
        List<int[]> vec = new ArrayList<int[]>();
        for (int l = 1, r = 2; l < r;) {
            int sum = (l + r) * (r - l + 1) / 2;
            if (sum == target) {
                int[] res = new int[r - l + 1];
                for (int i = l; i <= r; ++i) {
                    res[i - l] = i;
                }
                vec.add(res);
                l++;
            } else if (sum < target) {
                r++;
            } else {
                l++;
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }
}
