package BackTracking;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author : qizidog
 * @date : 2020-12-30 23:10
 * @description :
 * 1046. 最后一块石头的重量
 * 有一堆石头，每块石头的重量都是正整数。
 *
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/last-stone-weight
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class LastStoneWeight {
    public static void main(String[] args) {
        System.out.println(lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
    }

    public static int lastStoneWeight(int[] stones) {
        int[] temp = stones;
        while (temp.length>1){
            temp = pick(temp);
        }
        return temp.length>0?temp[0]:0;
    }

    public static int[] pick(int [] stones){
        // 按规则处理得到新的数组
        int[] top2 = bubble2(stones);
        if(top2[0] == top2[1]){
            return Arrays.copyOfRange(stones,0, stones.length-2);
        }else{
            int[] part1 = Arrays.copyOfRange(stones,0, stones.length-1);
            part1[part1.length-1] = top2[1]-top2[0];
            return part1;
        }
    }

    public static int[] bubble2(int[] arr){
        // 找到两个最大的数
        int length = arr.length;
        for(int i=0; i<2; i++){
            for(int j=0; j<length-i-1; j++){
                int temp;
                if(arr[j]>arr[j+1]){
                    // 交换
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return new int[]{arr[length-2], arr[length-1]};
    }

    // 官方解法
    public int lastStoneWeight2(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }

        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a > b) {
                pq.offer(a - b);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }
}
