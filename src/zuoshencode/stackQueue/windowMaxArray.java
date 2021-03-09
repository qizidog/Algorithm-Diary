package zuoshencode.stackQueue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author : qizidog
 * @date : 2021-03-09 20:20
 * @description :
 * 生成窗口最大值数组
 **/

public class windowMaxArray {

    // 借助 hashmap 和 大顶堆 来实现
    public static int[] getWindowMaxArray(int[] arr, int w){
        int len;
        if (arr==null || (len=arr.length)==0) return null;

        int[] ret = new int[len-w+1];
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b)-> b-a);

        // 第一个窗口单独处理
        for (int i = 0; i < w; i++) {
            int temp = arr[i];
            map.put(temp, map.getOrDefault(temp, 0)+1);
            heap.offer(temp);
        }
        ret[0] = heap.peek();

        for (int p = 1; p <len-w+1; p++) {
            // 移除旧的元素
            int k1 = map.get(arr[p-1]);
            map.put(arr[p-1], k1-1);
            heap.remove(arr[p-1]);

            // 添加新的元素
            int k2 = map.getOrDefault(arr[p+w-1], 0);
            map.put(arr[p+w-1], k2+1);
            heap.offer(arr[p+w-1]);
            ret[p] = heap.peek();
        }
        return ret;
    }

    // 使用双链表实现，时间复杂度O(n)
    public static int[] getWindowMaxArray2(int[] arr, int w) {
        int len;
        if (null==arr || (len=arr.length)==0 || w<=0 || len<w){
            return null;
        }

        int[] ret = new int[len - w + 1];
        int index = 0;
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < len; i++) {
            // 如果队尾元素小于等于当前元素，弹出
            while (!list.isEmpty() && arr[list.peekLast()] <= arr[i]) {
                list.pollLast();
            }
            list.offerLast(i);  // 添加新的元素
            if (list.peekFirst()<i-w+1){  // 如果队首元素下标过期了，丢弃（一次只可能过期一个元素，所以不用循环了）
                list.pollFirst();
            }
            if (i>=w-1){  // 下标大于等于w-1之后才开始记录
                ret[index++] = arr[list.peekFirst()];
            }
        }

        return ret;
    }

        public static void main(String[] args) {
        int[] arr = new int[]{6, 6, 5, 2, 4, 7, 3, 5, 1, 2};
        System.out.println(Arrays.toString(getWindowMaxArray(arr, 3)));
        System.out.println(Arrays.toString(getWindowMaxArray2(arr, 3)));
    }
}
