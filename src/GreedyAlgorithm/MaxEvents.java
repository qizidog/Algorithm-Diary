package GreedyAlgorithm;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author : qizidog
 * @date : 2021-02-07 00:58
 * @description :
 * 1353. 最多可以参加的会议数目
 * 给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。
 *
 * 你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。注意，一天只能参加一个会议。
 *
 * 请你返回你可以参加的 最大 会议数目。
 *
 * 输入：events = [[1,2],[2,3],[3,4]]
 * 输出：3
 * 解释：你可以参加所有的三个会议。
 * 安排会议的一种方案如上图。
 * 第 1 天参加第一个会议。
 * 第 2 天参加第二个会议。
 * 第 3 天参加第三个会议。
 *
 * 示例 2：
 * 输入：events= [[1,2],[2,3],[3,4],[1,2]]
 * 输出：4
 *
 * 示例 3：
 * 输入：events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
 * 输出：4
 *
 * 示例 4：
 * 输入：events = [[1,100000]]
 * 输出：1
 *
 * 示例 5：
 * 输入：events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
 * 输出：7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-events-that-can-be-attended
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class MaxEvents {
    // 这道题和左神9课——最佳投资方案Code05_IPO思路一致
    // 已经开始了的里面，结束最早的，优先参加
    public static int maxEvents(int[][] events) {
        if (events==null || events.length==0) return 0;

        // 按开始时间排序的小顶堆
        PriorityQueue<int[]> start = new PriorityQueue<>((a, b)->{
            if(a[0]==b[0]) return a[1]-b[1];
            return a[0]-b[0];
        });

        // 按结束时间排序的小顶堆
        PriorityQueue<int[]> end = new PriorityQueue<>((a, b)->{
            if(a[1]==b[1]) return a[0]-b[0];
            return a[1]-b[1];
        });

        // 所有事件先全部按开始时间排序
        for (int[] event : events) {
            start.offer(event);
        }

        int count = 0;
        int today=start.peek()[0];  // 开始日期设为最早开始事件的开始日期，避免一直没有事件开始，浪费时间
        // 只要还有没开始的或者还有没结束的事件，就一直继续
        while (!start.isEmpty() || !end.isEmpty()){

            // 把所有已经开始了的事件丢到end小顶堆中
            while (start.peek()!=null && start.peek()[0]<=today/* && today<=event[1]*/){
                end.offer(start.poll());
            }
            // 把所有过期的事件都丢了
            while(end.peek()!=null && end.peek()[1]<today){
                end.poll();
            }
            // 参加这个没有过期，但即将过期的会议
            if (end.poll() != null) {
                count++;
            }
            today++;
        }
        return count;
    }


    // 将开始时间小顶堆调整为排序，优化元素弹出后堆调整的时间，结束时间小顶堆优化为只记录结束时间
    public int maxEvents2(int[][] events) {
        if (events==null || events.length==0) return 0;

        // 按开始时间排序
        Arrays.sort(events, (a, b)->{
            if(a[0]==b[0]) return a[1]-b[1];
            return a[0]-b[0];
        });

        // 按结束时间排序的小顶堆
        PriorityQueue<Integer> end = new PriorityQueue<>();

        int count = 0;
        int today=events[0][0];  // 开始日期设为最早开始事件的开始日期，避免一直没有事件开始，浪费时间
        int toStart = 0;  // 记录下一个事件的开始日期

        // 只要还有没开始的或者还有没结束的事件，就一直继续
        while (toStart<events.length || !end.isEmpty()) {
            // 把所有已经开始了的事件丢到end小顶堆中
            for (int i = toStart; i < events.length; i++) {
                if (events[i][0] <= today) {
                    end.offer(events[i][1]);
                    toStart = i+1;
                } else {
                    toStart = i;
                    break;
                }
            }
            // 把所有过期的事件都丢了
            while (end.peek() != null && end.peek() < today) {
                end.poll();
            }
            // 参加这个没有过期，但即将过期的会议
            if (end.poll() != null) {
                count++;
            }
            today++;
        }

        return count;
    }


    public static void main(String[] args) {
        // int[][] events = new int[][]{{1,2},{2,3},{3,4}};  // 3
        // int[][] events = new int[][]{{1,2},{2,3},{3,4},{1,2}};  // 4
        int[][] events = new int[][]{{1,4},{4,4},{2,2},{3,4},{1,1}};  // 4
        System.out.println(maxEvents(events));
    }
}
