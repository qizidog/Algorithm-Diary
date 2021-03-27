package GreedyAlgorithm;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author : qizidog
 * @date : 2021-03-27 16:38
 * @description :
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *  
 *
 * 提示：
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class MergeArray {
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b)->{
            return a[0]!=b[0]?a[0]-b[0]:a[1]-b[1];
        });

        for(int[] e : intervals){
            heap.offer(e);
        }

        int[] top = heap.poll();
        while(!heap.isEmpty()){
            int[] first = heap.poll();
            if(mergable(top, first)){  // 如果可以合并
                top = getMerge(top, first);
            }else{  // 如果不能合并，加入结果集
                res.add(top);
                top = first;
            }
        }
        res.add(top);
        return res.toArray(new int[0][]);
    }

    public boolean mergable(int[] a, int[] b){
        return a[1]>=b[0];
    }

    public int[] getMerge(int[] a, int[] b){
        return new int[]{a[0], Math.max(a[1], b[1])};
    }
}
