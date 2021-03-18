package ArrayListTheme;

import java.util.PriorityQueue;

/**
 * @author : qizidog
 * @date : 2021-03-17 12:06
 * @description :
 * 剑指 Offer 40. 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 * 示例 1：
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 *
 * 示例 2：
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class GetLeastNumbers {
    public int[] getLeastNumbers(int[] arr, int k) {
        if(arr==null) return null;
        if(k<=0) return new int[0];
        // 大根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b)->{return b-a;});
        for(int e : arr){
            if(heap.size()<k){
                heap.offer(e);
            }else{
                if(heap.peek()>e){
                    heap.poll();
                    heap.offer(e);
                }
            }
        }
        // Integer[] ret = heap.toArray(new int[k]);  // 一个小坑，这样是不行的，int不支持泛型
        int idx = 0;
        int[] ret = new int[k];
        for(int num : heap){
            ret[idx++] = num;
        }
        return ret;
    }
}
